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
    public final CachedBooleanValue eternalFluidTankLikeCreativeFluidTank;
    public final CachedBooleanValue eternalBinLikeCreativeBinTank;
    public final CachedBooleanValue eternalEnergyCubeLikeCreativeEnergyCubeTank;

    public MAStorageConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Mekanism Avaritia Storage Config");
        electricNeutronCollector = CachedFloatingLongValue.define(this, builder, "Base energy storage (Joules).", "electricNeutronCollector", FloatingLong.create(160000L));
        eternalChemicalTankLikeCreativeChemicalTank = CachedBooleanValue.wrap(this, builder.comment("Make the behavior of the eternal chemicalTank the same as that of the creative chemicalTank.").define("eternalChemicalTankLikeCreativeChemicalTank", false));
        eternalFluidTankLikeCreativeFluidTank = CachedBooleanValue.wrap(this, builder.comment("Make the behavior of the eternal fluidTank the same as that of the creative fluidTank.").define("eternalFluidTankLikeCreativeFluidTank", false));
        eternalBinLikeCreativeBinTank = CachedBooleanValue.wrap(this, builder.comment("Make the behavior of the eternal bin the same as that of the creative bin.").define("eternalBinLikeCreativeBin", false));
        eternalEnergyCubeLikeCreativeEnergyCubeTank = CachedBooleanValue.wrap(this, builder.comment("Make the behavior of the eternal energyCube the same as that of the creative energyCube.").define("eternalEnergyCubeLikeCreativeEnergyCube", false));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "MekanismAvaritia-Storage";
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
