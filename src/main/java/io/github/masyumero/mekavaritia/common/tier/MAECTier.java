package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.api.tier.MATier;

import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum MAECTier implements IMATier, StringRepresentable {
    PRISMATIC(MATier.PRISMATIC, FloatingLong.create(4_096_000_000L), FloatingLong.create(2_048_000_000L)),
    FLARE(MATier.FLARE, FloatingLong.create(32_768_000_000L), FloatingLong.create(16_384_000_000L)),
    NEURAL(MATier.NEURAL, FloatingLong.create(2_097_152_000_000L), FloatingLong.create(1_048_576_000_000L)),
    ETERNAL(MATier.ETERNAL, FloatingLong.create(Long.MAX_VALUE), FloatingLong.create(Long.MAX_VALUE));

    private final FloatingLong maMaxEnergy;
    private final FloatingLong maOutput;
    private final MATier maTier;
    private CachedFloatingLongValue storageReference;
    private CachedFloatingLongValue outputReference;

    MAECTier(MATier maTier, FloatingLong max, FloatingLong o) {
        this.maMaxEnergy = max;
        this.maOutput = o;
        this.maTier = maTier;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getMAStorage() : storageReference.getOrDefault();
    }

    public FloatingLong getOutput() {
        return outputReference == null ? getMAOutput() : outputReference.getOrDefault();
    }

    public FloatingLong getMAStorage() {
        return maMaxEnergy;
    }

    public FloatingLong getMAOutput() {
        return maOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the GasTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference, CachedFloatingLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
