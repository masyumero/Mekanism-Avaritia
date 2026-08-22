package io.github.masyumero.mekavaritia.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAStorageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue electricNeutronCollector;

    public MAStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Storage Config");
        builder.push("storage");
        electricNeutronCollector = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "electricNeutronCollector", FloatingLong.create(160000L));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "storage";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.COMMON;
    }
}
