package io.github.masyumero.mekanismavaritia.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAGearConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedLongValue CosmicUnitUseageEnergy;

    public MAGearConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Gear Config").push("Unit Energy Use");
        CosmicUnitUseageEnergy = CachedLongValue.wrap(this, builder.defineInRange("CosmicUnitsUseageEnergy", Integer.MAX_VALUE, 1, Long.MAX_VALUE));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "gear";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.SERVER;
    }
}
