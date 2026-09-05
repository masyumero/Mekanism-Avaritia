package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import lombok.Getter;
import mekanism.api.math.FloatingLong;
import mekanism.common.config.value.CachedFloatingLongValue;
import org.jetbrains.annotations.Nullable;

public enum MAICTier implements IMATier {
    PRISMATIC(MATier.PRISMATIC, FloatingLong.createConst(262_144_000_000_000L)),
    FLARE(MATier.FLARE, FloatingLong.createConst(2_097_152_000_000_000L)),
    NEURAL(MATier.NEURAL, FloatingLong.createConst(16_777_216_000_000_000L)),
    ETERNAL(MATier.ETERNAL, FloatingLong.MAX_VALUE);

    @Getter
    private final FloatingLong baseMaxEnergy;
    private final MATier tier;
    @Nullable
    private CachedFloatingLongValue storageReference;

    MAICTier(MATier tier, FloatingLong out) {
        this.baseMaxEnergy = out;
        this.tier = tier;
    }

    @Override
    public MATier getMATier() {
        return tier;
    }

    public FloatingLong getMaxEnergy() {
        return storageReference == null ? getBaseMaxEnergy() : storageReference.getOrDefault();
    }

    /**
     * ONLY CALL THIS FROM TierConfig. It is used to give the MAICTier a reference to the actual config value object
     */
    public void setConfigReference(CachedFloatingLongValue storageReference) {
        this.storageReference = storageReference;
    }
}
