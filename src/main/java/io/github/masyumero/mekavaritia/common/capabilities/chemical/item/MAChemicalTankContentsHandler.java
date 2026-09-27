package io.github.masyumero.mekavaritia.common.capabilities.chemical.item;

import io.github.masyumero.mekavaritia.common.tier.MACTTier;
import mekanism.api.NBTConstants;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.common.capabilities.DynamicHandler;
import mekanism.common.capabilities.chemical.dynamic.DynamicChemicalHandler;
import mekanism.common.capabilities.merged.MergedTankContentsHandler;

import java.util.Objects;

public class MAChemicalTankContentsHandler extends MergedTankContentsHandler<MergedChemicalTank> {

    public static MAChemicalTankContentsHandler create(MACTTier tier) {
        Objects.requireNonNull(tier, "Chemical tank tier cannot be null");
        return new MAChemicalTankContentsHandler(tier);
    }

    private MAChemicalTankContentsHandler(MACTTier tier) {
        mergedTank = MergedChemicalTank.create(
                new MAChemicalTankRateLimitChemicalTank.GasTankRateLimitChemicalTank(tier, gasHandler = new DynamicChemicalHandler.DynamicGasHandler(side -> gasTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        () -> onContentsChanged(NBTConstants.GAS_TANKS, gasTanks))),
                new MAChemicalTankRateLimitChemicalTank.InfusionTankRateLimitChemicalTank(tier, infusionHandler = new DynamicChemicalHandler.DynamicInfusionHandler(side -> infusionTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.INFUSION_TANKS, infusionTanks))),
                new MAChemicalTankRateLimitChemicalTank.PigmentTankRateLimitChemicalTank(tier, pigmentHandler = new DynamicChemicalHandler.DynamicPigmentHandler(side -> pigmentTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.PIGMENT_TANKS, pigmentTanks))),
                new MAChemicalTankRateLimitChemicalTank.SlurryTankRateLimitChemicalTank(tier, slurryHandler = new DynamicChemicalHandler.DynamicSlurryHandler(side -> slurryTanks, DynamicHandler.InteractPredicate.ALWAYS_TRUE,
                        DynamicHandler.InteractPredicate.ALWAYS_TRUE, () -> onContentsChanged(NBTConstants.SLURRY_TANKS, slurryTanks))));
    }
}