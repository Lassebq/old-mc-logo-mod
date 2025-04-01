package io.github.lassebq.oldmclogo.mixin;

import static io.github.lassebq.oldmclogo.Mod.MODID;
import io.github.lassebq.oldmclogo.Mod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;

@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {
    private static final Identifier OLD_LOGO_TEXTURE = Identifier.of(MODID, "textures/gui/title/minecraft.png");
    private static final Identifier OLD_EDITION_TEXTURE = Identifier.of(MODID, "textures/gui/title/edition.png");
    @Shadow
    private boolean minceraft;
    @Shadow
    private boolean ignoreAlpha;

    @Inject(method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", at = @At("HEAD"), cancellable = true)
    public void draw(DrawContext context, int screenWidth, float alpha, int y, CallbackInfo ci) {
        if(Mod.config.getPixelatedStyle()) {
            context.setShaderColor(1.0F, 1.0F, 1.0F, this.ignoreAlpha ? 1.0F : alpha);
            int i = screenWidth / 2 - 137;
            if(minceraft) {
                context.drawTexture(OLD_LOGO_TEXTURE, i + 0, 30, 0, 0, 99, 44);
                context.drawTexture(OLD_LOGO_TEXTURE, i + 99, 30, 129, 0, 27, 44);
                context.drawTexture(OLD_LOGO_TEXTURE, i + 99 + 26, 30, 126, 0, 3, 44);
                context.drawTexture(OLD_LOGO_TEXTURE, i + 99 + 26 + 3, 30, 99, 0, 26, 44);
                context.drawTexture(OLD_LOGO_TEXTURE, i + 155, 30, 0, 45, 155, 44);
            } else {
                context.drawTexture(OLD_LOGO_TEXTURE, i + 0, 30, 0, 0, 155, 44);
                context.drawTexture(OLD_LOGO_TEXTURE, i + 155, 30, 0, 45, 155, 44);
            }
            if(!Mod.config.getRemoveJavaEdition()) {
                int k = y + 44 - 7;
                context.drawTexture(OLD_EDITION_TEXTURE, i + 88, k, 0.0F, 0.0F, 98, 14, 128, 16);
                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            ci.cancel();
        }
    }

    @WrapWithCondition(
        method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", at = @At(value = "INVOKE:LAST", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIFFIIII)V")
    )
    private boolean renderJavaEdition(DrawContext context, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        return !Mod.config.getRemoveJavaEdition();
    }
}
