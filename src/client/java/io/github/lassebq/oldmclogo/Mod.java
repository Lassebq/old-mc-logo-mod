package io.github.lassebq.oldmclogo;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class Mod implements ClientModInitializer {

    public static final String MODID = "oldmclogo";

    public static ModAutoConfig config;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(ModAutoConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();
    }

}
