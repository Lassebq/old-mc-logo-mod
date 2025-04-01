package io.github.lassebq.oldmclogo;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

@Config(name = Mod.MODID)
public class ModAutoConfig implements ConfigData {
    private boolean enabled = true;
    @Tooltip private boolean pixelatedStyle = true;
    @Tooltip private boolean removeJavaEdition = false;

    public boolean getPixelatedStyle() {
        return enabled && pixelatedStyle;
    }
    
    public boolean getRemoveJavaEdition() {
        return enabled && removeJavaEdition;
    }
}
