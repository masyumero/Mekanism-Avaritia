package io.github.masyumero.mekavaritia.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MAMoreCapacityConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedBooleanValue moreCapacityMode;
    public final CachedLongValue prismaticCompressing;
    public final CachedLongValue flareCompressing;
    public final CachedLongValue neuralCompressing;
    public final CachedLongValue eternalCompressing;

    public final CachedLongValue prismaticInjecting;
    public final CachedLongValue flareInjecting;
    public final CachedLongValue neuralInjecting;
    public final CachedLongValue eternalInjecting;

    public final CachedLongValue prismaticPurifying;
    public final CachedLongValue flarePurifying;
    public final CachedLongValue neuralPurifying;
    public final CachedLongValue eternalPurifying;

    public final CachedLongValue prismaticInfusingFactory;
    public final CachedLongValue flareInfusingFactory;
    public final CachedLongValue neuralInfusingFactory;
    public final CachedLongValue eternalInfusingFactory;

    public final CachedLongValue prismaticCentrifugingInput;
    public final CachedLongValue flareCentrifugingInput;
    public final CachedLongValue neuralCentrifugingInput;
    public final CachedLongValue eternalCentrifugingInput;
    public final CachedLongValue prismaticCentrifugingOutput;
    public final CachedLongValue flareCentrifugingOutput;
    public final CachedLongValue neuralCentrifugingOutput;
    public final CachedLongValue eternalCentrifugingOutput;
    
    public final CachedLongValue prismaticWashingInput;
    public final CachedLongValue flareWashingInput;
    public final CachedLongValue neuralWashingInput;
    public final CachedLongValue eternalWashingInput;
    public final CachedIntValue prismaticWashingFluidInput;
    public final CachedIntValue flareWashingFluidInput;
    public final CachedIntValue neuralWashingFluidInput;
    public final CachedIntValue eternalWashingFluidInput;
    public final CachedLongValue prismaticWashingOutput;
    public final CachedLongValue flareWashingOutput;
    public final CachedLongValue neuralWashingOutput;
    public final CachedLongValue eternalWashingOutput;
    
    public final CachedLongValue prismaticDissolvingInput;
    public final CachedLongValue flareDissolvingInput;
    public final CachedLongValue neuralDissolvingInput;
    public final CachedLongValue eternalDissolvingInput;
    public final CachedLongValue prismaticDissolvingOutput;
    public final CachedLongValue flareDissolvingOutput;
    public final CachedLongValue neuralDissolvingOutput;
    public final CachedLongValue eternalDissolvingOutput;
    
    public final CachedLongValue prismaticOxidizing;
    public final CachedLongValue flareOxidizing;
    public final CachedLongValue neuralOxidizing;
    public final CachedLongValue eternalOxidizing;

    public final CachedLongValue prismaticCrystallizing;
    public final CachedLongValue flareCrystallizing;
    public final CachedLongValue neuralCrystallizing;
    public final CachedLongValue eternalCrystallizing;
    
    public final CachedLongValue prismaticPigmentExtracting;
    public final CachedLongValue flarePigmentExtracting;
    public final CachedLongValue neuralPigmentExtracting;
    public final CachedLongValue eternalPigmentExtracting;
    
    public final CachedIntValue prismaticLiquifying;
    public final CachedIntValue flareLiquifying;
    public final CachedIntValue neuralLiquifying;
    public final CachedIntValue eternalLiquifying;
    
    public final CachedLongValue prismaticPainting;
    public final CachedLongValue flarePainting;
    public final CachedLongValue neuralPainting;
    public final CachedLongValue eternalPainting;
    
    public final CachedLongValue prismaticPlanting;
    public final CachedLongValue flarePlanting;
    public final CachedLongValue neuralPlanting;
    public final CachedLongValue eternalPlanting;
    
    public final CachedLongValue prismaticPRCInput;
    public final CachedLongValue flarePRCInput;
    public final CachedLongValue neuralPRCInput;
    public final CachedLongValue eternalPRCInput;
    public final CachedIntValue prismaticPRCFluidInput;
    public final CachedIntValue flarePRCFluidInput;
    public final CachedIntValue neuralPRCFluidInput;
    public final CachedIntValue eternalPRCFluidInput;
    public final CachedLongValue prismaticPRCOutput;
    public final CachedLongValue flarePRCOutput;
    public final CachedLongValue neuralPRCOutput;
    public final CachedLongValue eternalPRCOutput;
    
    public final CachedLongValue prismaticReplicating;
    public final CachedLongValue flareReplicating;
    public final CachedLongValue neuralReplicating;
    public final CachedLongValue eternalReplicating;
    
    public MAMoreCapacityConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Mekanism Avaritia More Capacity Config");
        moreCapacityMode = CachedBooleanValue.wrap(this, builder.comment("If this is true, these configurations will be applied.").define("MoreCapacityMode", false));
        builder.push("CompressingFactory");
        prismaticCompressing  = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        flareCompressing       = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        neuralCompressing          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        eternalCompressing  = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("eternalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("InjectingFactory");
        prismaticInjecting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        flareInjecting         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        neuralInjecting            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        eternalInjecting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("eternalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("PurifyingFactory");
        prismaticPurifying    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 3024000, Vanilla: 30240").defineInRange("AbsoluteOverclockedChemicalTankCapacity",3024000,1,Long.MAX_VALUE));
        flarePurifying         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 4116000, Vanilla: 41160").defineInRange("SupremeQuantumChemicalTankCapacity",4116000,1,Long.MAX_VALUE));
        neuralPurifying            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 5376000, Vanilla: 53760").defineInRange("CosmicDenseChemicalTankCapacity",5376000,1,Long.MAX_VALUE));
        eternalPurifying    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 6804000, Vanilla: 68040").defineInRange("eternalChemicalTankCapacity",6804000,1,Long.MAX_VALUE));
        builder.pop().push("InfusingFactory");
        prismaticInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("AbsoluteOverclockedInfusingFactory",144000,1,Long.MAX_VALUE));
        flareInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("SupremeQuantumInfusingFactory",196000,1,Long.MAX_VALUE));
        neuralInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("CosmicDenseInfusingFactory",256000,1,Long.MAX_VALUE));
        eternalInfusingFactory = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB).").defineInRange("eternalInfusingFactory",324000,1,Long.MAX_VALUE));
        builder.pop().push("CentrifugingFactory");
        prismaticCentrifugingInput    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareCentrifugingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralCentrifugingInput            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalCentrifugingInput    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        prismaticCentrifugingOutput   = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareCentrifugingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralCentrifugingOutput           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalCentrifugingOutput   = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("WashingFactory");
        prismaticWashingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareWashingInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralWashingInput                 = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalWashingInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        prismaticWashingFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        flareWashingFluidInput         = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        neuralWashingFluidInput            = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        eternalWashingFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        prismaticWashingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareWashingOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralWashingOutput                = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalWashingOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("DissolvingFactory");
        prismaticDissolvingInput      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareDissolvingInput           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralDissolvingInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalDissolvingInput      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        prismaticDissolvingOutput     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareDissolvingOutput          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralDissolvingOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalDissolvingOutput     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("OxidizingFactory");
        prismaticOxidizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareOxidizing         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralOxidizing            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalOxidizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("CrystallizingFactory");
        prismaticCrystallizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareCrystallizing         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralCrystallizing            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalCrystallizing    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("PigmentExtractingFactory");
        prismaticPigmentExtracting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flarePigmentExtracting         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralPigmentExtracting            = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalPigmentExtracting    = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("LiquifyingFactory");
        prismaticLiquifying   = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        flareLiquifying        = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        neuralLiquifying           = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        eternalLiquifying   = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        builder.pop().push("PaintingFactory");
        prismaticPainting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("absoluteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        flarePainting          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 21000000, Vanilla: 210000").defineInRange("supremeChemicalTankCapacity", 21000000, 1, Long.MAX_VALUE));
        neuralPainting             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 24000000, Vanilla: 240000").defineInRange("cosmicChemicalTankCapacity", 24000000, 1, Long.MAX_VALUE));
        eternalPainting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 27000000, Vanilla: 270000").defineInRange("infiniteChemicalTankCapacity", 27000000, 1, Long.MAX_VALUE));
        builder.pop().push("PlantingFactory");
        prismaticPlanting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flarePlanting          = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralPlanting             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalPlanting     = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("PRCFactory");
        prismaticPRCInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flarePRCInput              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralPRCInput                 = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalPRCInput         = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        prismaticPRCFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteInputFluidTankCapacity", 12000000, 1, Integer.MAX_VALUE));
        flarePRCFluidInput         = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeInputFluidTankCapacity", 14000000, 1, Integer.MAX_VALUE));
        neuralPRCFluidInput            = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicInputFluidTankCapacity", 16000000, 1, Integer.MAX_VALUE));
        eternalPRCFluidInput    = CachedIntValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteInputFluidTankCapacity", 18000000, 1, Integer.MAX_VALUE));
        prismaticPRCOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteOutputChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flarePRCOutput             = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeOutputChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralPRCOutput                = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicOutputChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalPRCOutput        = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteOutputChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        builder.pop().push("ReplicatingFactory");
        prismaticReplicating      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 12000000, Vanilla: 120000").defineInRange("absoluteChemicalTankCapacity", 12000000, 1, Long.MAX_VALUE));
        flareReplicating           = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 14000000, Vanilla: 140000").defineInRange("supremeChemicalTankCapacity", 14000000, 1, Long.MAX_VALUE));
        neuralReplicating              = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 16000000, Vanilla: 160000").defineInRange("cosmicChemicalTankCapacity", 16000000, 1, Long.MAX_VALUE));
        eternalReplicating      = CachedLongValue.wrap(this, builder.comment("Chemical tank capacity (mB). Default: 18000000, Vanilla: 180000").defineInRange("infiniteChemicalTankCapacity", 18000000, 1, Long.MAX_VALUE));
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "MekanismAvaritia-More-Capacity";
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
