package io.github.masyumero.mekavaritia.common.config;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import mekanism.api.heat.HeatAPI;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAConfig extends BaseMekanismConfig {
    
    private final ForgeConfigSpec configSpec;
    public final CachedFloatingLongValue prismaticUniversalCableCapacity;
    public final CachedFloatingLongValue flareUniversalCableCapacity;
    public final CachedFloatingLongValue neuralUniversalCableCapacity;
    public final CachedFloatingLongValue eternalUniversalCableCapacity;

    public final CachedFloatingLongValue prismaticMechanicalPipePullAmount;
    public final CachedFloatingLongValue flareMechanicalPipePullAmount;
    public final CachedFloatingLongValue neuralMechanicalPipePullAmount;
    public final CachedFloatingLongValue eternalMechanicalPipePullAmount;

    public final CachedFloatingLongValue prismaticMechanicalPipeCapacity;
    public final CachedFloatingLongValue flareMechanicalPipeCapacity;
    public final CachedFloatingLongValue neuralMechanicalPipeCapacity;
    public final CachedFloatingLongValue eternalMechanicalPipeCapacity;

    public final CachedFloatingLongValue prismaticThermodynamicConductorConduction;
    public final CachedFloatingLongValue flareThermodynamicConductorConduction;
    public final CachedFloatingLongValue neuralThermodynamicConductorConduction;
    public final CachedFloatingLongValue eternalThermodynamicConductorConduction;

    public final CachedFloatingLongValue prismaticThermodynamicConductornCapacity;
    public final CachedFloatingLongValue flareThermodynamicConductornCapacity;
    public final CachedFloatingLongValue neuralThermodynamicConductornCapacity;
    public final CachedFloatingLongValue eternalThermodynamicConductornCapacity;

    public final CachedFloatingLongValue prismaticThermodynamicConductornInsulation;
    public final CachedFloatingLongValue flareThermodynamicConductornInsulation;
    public final CachedFloatingLongValue neuralThermodynamicConductornInsulation;
    public final CachedFloatingLongValue eternalThermodynamicConductornInsulation;

    public final CachedFloatingLongValue prismaticLogisticalTransporterSpeed;
    public final CachedFloatingLongValue flareLogisticalTransporterSpeed;
    public final CachedFloatingLongValue neuralLogisticalTransporterSpeed;
    public final CachedFloatingLongValue eternalLogisticalTransporterSpeed;

    public final CachedFloatingLongValue prismaticLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue flareLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue neuralLogisticalTransporterPullAmount;
    public final CachedFloatingLongValue eternalLogisticalTransporterPullAmount;

    public final CachedFloatingLongValue prismaticPressurizedTubePullAmount;
    public final CachedFloatingLongValue flarePressurizedTubePullAmount;
    public final CachedFloatingLongValue neuralPressurizedTubePullAmount;
    public final CachedFloatingLongValue eternalPressurizedTubePullAmount;

    public final CachedFloatingLongValue prismaticPressurizedTubeCapacity;
    public final CachedFloatingLongValue flarePressurizedTubeCapacity;
    public final CachedFloatingLongValue neuralPressurizedTubeCapacity;
    public final CachedFloatingLongValue eternalPressurizedTubeCapacity;

    public MAConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Config").push(MekanismAvaritia.MODID);

        final String noteUC = "Internal buffer in Joules of each 'TIER' universal cable.(long)";
        builder.comment("Universal Cables").push("universal cables");
        prismaticUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"prismaticUniversalCable", FloatingLong.createConst(262_144_000L));
        flareUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"flareUniversalCable", FloatingLong.createConst(2_097_152_000L));
        neuralUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "neuralUniversalCable", FloatingLong.createConst(16_777_216_000L));
        eternalUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "eternalUniversalCable", FloatingLong.createConst(134_217_728_000L));
        builder.pop();

        final String noteMP = "Capacity of 'TIER' mechanical pipes in mB.(long)";
        final String noteMP2 = "Pump rate of 'TIER' mechanical pipes in mB/t.(int)";
        builder.comment("Mechanical Pipes").push("mechanical pipes");
        prismaticMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "prismaticMechanicalPipePullAmount", FloatingLong.createConst(1_024_000));
        flareMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"flareMechanicalPipePullAmount", FloatingLong.createConst(8_192_000));
        neuralMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"neuralMechanicalPipePullAmount", FloatingLong.createConst(65_536_000));
        eternalMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"eternalMechanicalPipePullAmount", FloatingLong.createConst(524_288_000));

        prismaticMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "prismaticMechanicalPipeCapacity", FloatingLong.createConst(4_096_000));
        flareMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "flareMechanicalPipeCapacity", FloatingLong.createConst(32_768_000));
        neuralMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "neuralMechanicalPipeCapacity", FloatingLong.createConst(262_144_000));
        eternalMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "eternalMechanicalPipeCapacity", FloatingLong.createConst(2_097_152_000));
        builder.pop();

        final String noteTC = "Conduction value of 'TIER' thermodynamic conductors.(long)";
        final String noteTC2 = "Heat capacity of 'TIER' thermodynamic conductors.(long)";
        final String noteTC3 = "Insulation value of 'TIER' thermodynamic conductor(long).";
        builder.comment("Thermodynamic Conductors").push("thermodynamic conductors");
        prismaticThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "prismaticThermodynamicConductorConduction", FloatingLong.createConst(15L));
        flareThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "flareThermodynamicConductorConduction", FloatingLong.createConst(20L));
        neuralThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "neuralThermodynamicConductorConduction", FloatingLong.createConst(25L));
        eternalThermodynamicConductorConduction = CachedFloatingLongValue.define(this, builder, noteTC, "eternalThermodynamicConductorConduction", FloatingLong.createConst(30L));

        prismaticThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "prismaticThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        flareThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "flareThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        neuralThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "neuralThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));
        eternalThermodynamicConductornCapacity = CachedFloatingLongValue.define(this, builder, noteTC2, "eternalThermodynamicConductornCapacity", FloatingLong.createConst(HeatAPI.DEFAULT_HEAT_CAPACITY));

        prismaticThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "prismaticThermodynamicConductornInsulation", FloatingLong.createConst(600000L));
        flareThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "flareThermodynamicConductornInsulation", FloatingLong.createConst(900000L));
        neuralThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "neuralThermodynamicConductornInsulation", FloatingLong.createConst(2000000L));
        eternalThermodynamicConductornInsulation = CachedFloatingLongValue.define(this, builder, noteTC3, "eternalThermodynamicConductornInsulation", FloatingLong.createConst(8000000L));
        builder.pop();

        final String noteLT = "Five times the travel speed in m/s of 'TIER' logistical transporter.(int)";
        final String noteLT2 = "Item throughput rate of 'TIER' logistical transporters in items/half second.(int)";
        builder.comment("Logistical Transporters").push("logistical transporters");
        prismaticLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "prismaticLogisticalTransporterSpeed", FloatingLong.createConst(60));
        flareLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "flareLogisticalTransporterSpeed", FloatingLong.createConst(65));
        neuralLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "neuralLogisticalTransporterSpeed", FloatingLong.createConst(85));
        eternalLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "eternalLogisticalTransporterSpeed", FloatingLong.createConst(125));

        prismaticLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "prismaticLogisticalTransporterPullAmount", FloatingLong.createConst(192));
        flareLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "flareLogisticalTransporterPullAmount", FloatingLong.createConst(384));
        neuralLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "neuralLogisticalTransporterPullAmount", FloatingLong.createConst(768));
        eternalLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "eternalLogisticalTransporterPullAmount", FloatingLong.createConst(1536));
        builder.pop();

        final String notePT = "Capacity of 'TIER' pressurized tubes in mB.(long)";
        final String notePT2 = "Pump rate of 'TIER' pressurized tubes in mB/t.(long)";
        builder.comment("Pressurized Tubes").push("pressurized tubes");
        prismaticPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "prismaticPressurizedTubePullAmount", FloatingLong.createConst(8_192_000));
        flarePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "flarePressurizedTubePullAmount", FloatingLong.createConst(65_536_000));
        neuralPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "neuralPressurizedTubePullAmount", FloatingLong.createConst(524_288_000));
        eternalPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "eternalPressurizedTubePullAmount", FloatingLong.createConst(4_194_304_000L));

        prismaticPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "prismaticPressurizedTubeCapacity", FloatingLong.createConst(32_768_000));
        flarePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "flarePressurizedTubeCapacity", FloatingLong.createConst(262_144_000));
        neuralPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "neuralPressurizedTubeCapacity", FloatingLong.createConst(2_097_152_000));
        eternalPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "eternalPressurizedTubeCapacity", FloatingLong.createConst(16_777_216_000L));
        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "MekanismAvaritia";
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
