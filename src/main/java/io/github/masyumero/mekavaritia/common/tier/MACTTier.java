package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.api.tier.MATier;

import mekanism.common.config.value.CachedLongValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum MACTTier implements IMATier, StringRepresentable {
    PRISMATIC(MATier.PRISMATIC, 32_768_000L, 131_072_000L),
    FLARE(MATier.FLARE, 131_072_000L, 1_048_576_000L),
    NEURAL(MATier.NEURAL, 524_288_000L, 4_194_304_000L),
    ETERNAL(MATier.ETERNAL, Long.MAX_VALUE, Long.MAX_VALUE);

    private final long maStorage;
    private final long maOutput;
    private final MATier maTier;
    private CachedLongValue storageReference;
    private CachedLongValue outputReference;

    MACTTier(MATier maTier, long s, long o) {
        this.maStorage = s;
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

    public long getStorage() {
        return storageReference == null ? getMAStorage() : storageReference.getOrDefault();
    }

    public long getOutput() {
        return outputReference == null ? getMAOutput() : outputReference.getOrDefault();
    }

    public long getMAStorage() {
        return maStorage;
    }

    public long getMAOutput() {
        return maOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the GasTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedLongValue storageReference, CachedLongValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
