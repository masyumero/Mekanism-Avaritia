package io.github.masyumero.mekavaritia.common.config;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import mekanism.api.heat.HeatAPI;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedDoubleValue;
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

    public final CachedDoubleValue prismaticThermodynamicConductorConduction;
    public final CachedDoubleValue flareThermodynamicConductorConduction;
    public final CachedDoubleValue neuralThermodynamicConductorConduction;
    public final CachedDoubleValue eternalThermodynamicConductorConduction;

    public final CachedDoubleValue prismaticThermodynamicConductornCapacity;
    public final CachedDoubleValue flareThermodynamicConductornCapacity;
    public final CachedDoubleValue neuralThermodynamicConductornCapacity;
    public final CachedDoubleValue eternalThermodynamicConductornCapacity;

    public final CachedDoubleValue prismaticThermodynamicConductornInsulation;
    public final CachedDoubleValue flareThermodynamicConductornInsulation;
    public final CachedDoubleValue neuralThermodynamicConductornInsulation;
    public final CachedDoubleValue eternalThermodynamicConductornInsulation;

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
        prismaticUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"prismaticUniversalCable", FloatingLong.createConst(131_072_000L));
        flareUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC,"flareUniversalCable", FloatingLong.createConst(1_048_576_000L));
        neuralUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "neuralUniversalCable", FloatingLong.createConst(8_388_608_000L));
        eternalUniversalCableCapacity = CachedFloatingLongValue.define(this, builder, noteUC, "eternalUniversalCable", FloatingLong.MAX_VALUE);
        builder.pop();

        final String noteMP = "Capacity of 'TIER' mechanical pipes in mB.(long)";
        final String noteMP2 = "Pump rate of 'TIER' mechanical pipes in mB/t.(int)";
        builder.comment("Mechanical Pipes").push("mechanical pipes");
        prismaticMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2, "prismaticMechanicalPipePullAmount", FloatingLong.createConst(512_000L));
        flareMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"flareMechanicalPipePullAmount", FloatingLong.createConst(4_096_000L));
        neuralMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"neuralMechanicalPipePullAmount", FloatingLong.createConst(32_768_000L));
        eternalMechanicalPipePullAmount = CachedFloatingLongValue.define(this, builder, noteMP2,"eternalMechanicalPipePullAmount", FloatingLong.createConst(Integer.MAX_VALUE));

        prismaticMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "prismaticMechanicalPipeCapacity", FloatingLong.createConst(2_048_000L));
        flareMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "flareMechanicalPipeCapacity", FloatingLong.createConst(16_384_000L));
        neuralMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "neuralMechanicalPipeCapacity", FloatingLong.createConst(131_072_000L));
        eternalMechanicalPipeCapacity = CachedFloatingLongValue.define(this, builder, noteMP, "eternalMechanicalPipeCapacity", FloatingLong.MAX_VALUE);
        builder.pop();

        final String noteTC = "Conduction value of 'TIER' thermodynamic conductors.(double)";
        final String noteTC2 = "Heat capacity of 'TIER' thermodynamic conductors.(double)";
        final String noteTC3 = "Insulation value of 'TIER' thermodynamic conductor(double).";
        builder.comment("Thermodynamic Conductors").push("thermodynamic conductors");
        prismaticThermodynamicConductorConduction = CachedDoubleValue.wrap(this, builder.comment(noteTC).defineInRange("prismaticThermodynamicConductorConduction", 10.0, 0.0, Double.MAX_VALUE));
        flareThermodynamicConductorConduction = CachedDoubleValue.wrap(this, builder.comment(noteTC).defineInRange("flareThermodynamicConductorConduction", 15.0, 0.0, Double.MAX_VALUE));
        neuralThermodynamicConductorConduction = CachedDoubleValue.wrap(this, builder.comment(noteTC).defineInRange("neuralThermodynamicConductorConduction", 30.0, 0.0, Double.MAX_VALUE));
        eternalThermodynamicConductorConduction = CachedDoubleValue.wrap(this, builder.comment(noteTC).defineInRange("eternalThermodynamicConductorConduction", 100.0, 0.0, Double.MAX_VALUE));

        prismaticThermodynamicConductornCapacity = CachedDoubleValue.wrap(this, builder.comment(noteTC2).defineInRange("prismaticThermodynamicConductornCapacity", HeatAPI.DEFAULT_HEAT_CAPACITY, 0.0, Double.MAX_VALUE));
        flareThermodynamicConductornCapacity = CachedDoubleValue.wrap(this, builder.comment(noteTC2).defineInRange("flareThermodynamicConductornCapacity", HeatAPI.DEFAULT_HEAT_CAPACITY, 0.0, Double.MAX_VALUE));
        neuralThermodynamicConductornCapacity = CachedDoubleValue.wrap(this, builder.comment(noteTC2).defineInRange("neuralThermodynamicConductornCapacity", HeatAPI.DEFAULT_HEAT_CAPACITY, 0.0, Double.MAX_VALUE));
        eternalThermodynamicConductornCapacity = CachedDoubleValue.wrap(this, builder.comment(noteTC2).defineInRange("eternalThermodynamicConductornCapacity", HeatAPI.DEFAULT_HEAT_CAPACITY, 0.0, Double.MAX_VALUE));

        prismaticThermodynamicConductornInsulation = CachedDoubleValue.wrap(this, builder.comment(noteTC3).defineInRange("prismaticThermodynamicConductornInsulation", 800000.0, 0.0, Double.MAX_VALUE));
        flareThermodynamicConductornInsulation = CachedDoubleValue.wrap(this, builder.comment(noteTC3).defineInRange("flareThermodynamicConductornInsulation",1600000.0, 0.0, Double.MAX_VALUE));
        neuralThermodynamicConductornInsulation = CachedDoubleValue.wrap(this, builder.comment(noteTC3).defineInRange("neuralThermodynamicConductornInsulation", 6400000.0, 0.0, Double.MAX_VALUE));
        eternalThermodynamicConductornInsulation = CachedDoubleValue.wrap(this, builder.comment(noteTC3).defineInRange("eternalThermodynamicConductornInsulation", Double.MAX_VALUE, 0.0, Double.MAX_VALUE));
        builder.pop();

        final String noteLT = "Five times the travel speed in m/s of 'TIER' logistical transporter.(int)";
        final String noteLT2 = "Item throughput rate of 'TIER' logistical transporters in items/half second.(int)";
        builder.comment("Logistical Transporters").push("logistical transporters");
        prismaticLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "prismaticLogisticalTransporterSpeed", FloatingLong.createConst(75));
        flareLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "flareLogisticalTransporterSpeed", FloatingLong.createConst(125));
        neuralLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "neuralLogisticalTransporterSpeed", FloatingLong.createConst(200));
        eternalLogisticalTransporterSpeed = CachedFloatingLongValue.define(this, builder, noteLT, "eternalLogisticalTransporterSpeed", FloatingLong.createConst(Integer.MAX_VALUE));

        prismaticLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "prismaticLogisticalTransporterPullAmount", FloatingLong.createConst(256));
        flareLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "flareLogisticalTransporterPullAmount", FloatingLong.createConst(512));
        neuralLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "neuralLogisticalTransporterPullAmount", FloatingLong.createConst(1024));
        eternalLogisticalTransporterPullAmount = CachedFloatingLongValue.define(this, builder, noteLT2, "eternalLogisticalTransporterPullAmount", FloatingLong.createConst(Integer.MAX_VALUE));
        builder.pop();

        final String notePT = "Capacity of 'TIER' pressurized tubes in mB.(long)";
        final String notePT2 = "Pump rate of 'TIER' pressurized tubes in mB/t.(long)";
        builder.comment("Pressurized Tubes").push("pressurized tubes");
        prismaticPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "prismaticPressurizedTubePullAmount", FloatingLong.createConst(4_096_000L));
        flarePressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "flarePressurizedTubePullAmount", FloatingLong.createConst(32_768_000L));
        neuralPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "neuralPressurizedTubePullAmount", FloatingLong.createConst(262_144_000L));
        eternalPressurizedTubePullAmount = CachedFloatingLongValue.define(this, builder, notePT2, "eternalPressurizedTubePullAmount", FloatingLong.MAX_VALUE);

        prismaticPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "prismaticPressurizedTubeCapacity", FloatingLong.createConst(16_384_000L));
        flarePressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "flarePressurizedTubeCapacity", FloatingLong.createConst(131_072_000L));
        neuralPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "neuralPressurizedTubeCapacity", FloatingLong.createConst(1_048_576_000L));
        eternalPressurizedTubeCapacity = CachedFloatingLongValue.define(this, builder, notePT, "eternalPressurizedTubeCapacity", FloatingLong.MAX_VALUE);
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
