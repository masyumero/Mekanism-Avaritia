package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.api.tier.MATier;

import mekanism.common.config.value.CachedIntValue;

public enum MABTier implements IMATier {
    PRISMATIC(MATier.PRISMATIC, 4_096_000),
    FLARE(MATier.FLARE, 8_192_000),
    NEURAL(MATier.NEURAL, 1_048_576_000),
    ETERNAL(MATier.ETERNAL, Integer.MAX_VALUE);

    private final int maStorage;
    private final MATier maTier;
    private CachedIntValue storageReference;

    MABTier(MATier maTier, int s) {
        this.maStorage = s;
        this.maTier = maTier;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }

    public int getStorage() {
        return storageReference == null ? getMAStorage() : storageReference.getOrDefault();
    }

    private int getMAStorage() {
        return maStorage;
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the BinTier a reference to the actual config value object
     */
    public void setConfigReference(CachedIntValue storageReference) {
        this.storageReference = storageReference;
    }
}
