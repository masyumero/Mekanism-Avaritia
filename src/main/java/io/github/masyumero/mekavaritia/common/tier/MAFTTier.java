package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.api.tier.MATier;

import mekanism.common.config.value.CachedIntValue;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum MAFTTier implements IMATier, StringRepresentable {
    PRISMATIC(MATier.PRISMATIC, 4_096_000, 2_048_000),
    FLARE(MATier.FLARE, 8_192_000, 4_096_000),
    NEURAL(MATier.NEURAL, 2_097_152_000, 1_048_576_000),
    ETERNAL(MATier.ETERNAL, Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final int maStorage;
    private final int maOutput;
    private final MATier maTier;
    private CachedIntValue storageReference;
    private CachedIntValue outputReference;

    MAFTTier(MATier maTier, int s, int o) {
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

    public int getStorage() {
        return storageReference == null ? getMAStorage() : storageReference.getOrDefault();
    }

    public int getOutput() {
        return outputReference == null ? getMAOutput() : outputReference.getOrDefault();
    }

    private int getMAStorage() {
        return maStorage;
    }

    private int getMAOutput() {
        return maOutput;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the GasTankTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference, CachedIntValue outputReference) {
        this.storageReference = storageReference;
        this.outputReference = outputReference;
    }
}
