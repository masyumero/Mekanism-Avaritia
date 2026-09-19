package io.github.masyumero.mekavaritia.common.tier;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.api.tier.MATier;

public enum MAFactoryTier implements IMATier {
    PRISMATIC(MATier.PRISMATIC, 10),
    FLARE(MATier.FLARE, 20),
    NEURAL(MATier.NEURAL, 30),
    ETERNAL(MATier.ETERNAL, 30);

    public final int processes;
    private final MATier maTier;

    MAFactoryTier(MATier tier, int process) {
        processes = process;
        maTier = tier;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }

    public int getInventoryLabelX() {
        return switch (this) {
            case PRISMATIC -> (int)(3.5 * processes);
            case FLARE -> (int)(6.5 * processes);
            case NEURAL, ETERNAL -> (int)(7.5 * processes);
        };
    }

    public int getInventoryXOffset() {
        return getInventoryLabelX();
    }

    public int getImageWidth() {
        return 19 * (processes - 7) - 3;
    }
}
