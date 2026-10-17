package io.github.masyumero.mekavaritia.common.capabilities.energy;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.tier.MAECTier;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.BasicEnergyContainer;

import net.minecraft.MethodsReturnNonnullByDefault;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@NothingNullByDefault
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class MAEnergyCubeEnergyContainer extends BasicEnergyContainer {

    private final boolean isCreative;
    private final FloatingLong rate;

    public static MAEnergyCubeEnergyContainer create(MAECTier tier, @Nullable IContentsListener listener) {
        Objects.requireNonNull(tier, "Energy cube tier cannot be null");
        return new MAEnergyCubeEnergyContainer(tier, listener);
    }

    private MAEnergyCubeEnergyContainer(MAECTier tier, @Nullable IContentsListener listener) {
        super(tier.getMaxEnergy(), alwaysTrue, alwaysTrue, listener);
        Objects.requireNonNull(tier);
        isCreative = LoadConfig.STORAGE_CONFIG.eternalEnergyCubeLikeCreativeEnergyCubeTank.get() && tier != MAECTier.ETERNAL;
        this.rate = tier.getOutput();
    }

    @Override
    protected FloatingLong getRate(@Nullable AutomationType automationType) {
        //Only limit the internal rate to change the speed at which this can be filled from an item
        return automationType == AutomationType.INTERNAL ? rate : super.getRate(automationType);
    }

    @Override
    public FloatingLong insert(FloatingLong amount, Action action, AutomationType automationType) {
        //Note: Unlike other creative items, the creative energy cube does not allow changing it to always full
        return super.insert(amount, action.combine(!isCreative), automationType);
    }

    @Override
    public FloatingLong extract(FloatingLong amount, Action action, AutomationType automationType) {
        return super.extract(amount, action.combine(!isCreative), automationType);
    }
}