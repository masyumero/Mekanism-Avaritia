package io.github.masyumero.mekavaritia.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAUsageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue electricNeutronCollector;

    public MAUsageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Usage Config");
        builder.push("usage");
        electricNeutronCollector = CachedFloatingLongValue.define(this, builder, "Energy per operation tick (Joules).", "electricNeutronCollector", FloatingLong.create(500L));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "usage";
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
