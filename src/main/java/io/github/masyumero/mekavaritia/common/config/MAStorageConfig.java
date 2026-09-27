package io.github.masyumero.mekavaritia.common.config;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAStorageConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue electricNeutronCollector;
    public final CachedBooleanValue eternalChemicalTankLikeCreativeChemicalTank;

    public MAStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Storage Config");
        electricNeutronCollector = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "electricNeutronCollector", FloatingLong.create(160000L));
        eternalChemicalTankLikeCreativeChemicalTank = CachedBooleanValue.wrap(this, builder.comment("Make the behavior of the eternal chemicalTank the same as that of the creative chemicalTank.").define("eternalChemicalTankLikeCreativeChemicalTank", false));
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
