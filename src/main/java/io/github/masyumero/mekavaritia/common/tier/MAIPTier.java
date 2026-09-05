package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import lombok.Getter;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

public enum MAIPTier implements IMATier {
    PRISMATIC(MATier.PRISMATIC, FloatingLong.createConst(8_388_608_000L)),
    FLARE(MATier.FLARE, FloatingLong.createConst(67_108_864_000L)),
    NEURAL(MATier.NEURAL, FloatingLong.createConst(536_870_912_000L)),
    ETERNAL(MATier.ETERNAL, FloatingLong.MAX_VALUE);

    @Getter
    private final FloatingLong baseOutput;
    private final MATier tier;
    @Nullable
    private CachedFloatingLongValue outputReference;

    MAIPTier(MATier tier, FloatingLong out) {
        this.baseOutput = out;
        this.tier = tier;
    }

    @Override
    public MATier getMATier() {
        return tier;
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getBaseOutput() : outputReference.getOrDefault();
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the MAIPTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue outputReference) {
        this.outputReference = outputReference;
    }
}
