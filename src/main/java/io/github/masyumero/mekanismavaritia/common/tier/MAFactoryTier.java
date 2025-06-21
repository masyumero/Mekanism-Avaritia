package io.github.masyumero.mekanismavaritia.common.tier;

import io.github.masyumero.mekanismavaritia.api.tier.IMATier;
import io.github.masyumero.mekanismavaritia.api.tier.MATier;

public enum MAFactoryTier implements IMATier {
    PRISMATIC(MATier.PRISMATIC,10, 53, 34),
    FLARE(MATier.FLARE,20, 243,129),
    NEURAL(MATier.NEURAL,30,433,224),
    ETERNAL(MATier.ETERNAL,30,433,224);

    public final int processes;
    public final int imageWidth;
    public final int inventoryLabelX;
    private final MATier maTier;

    MAFactoryTier(MATier tier, int process, int guiImageWidth, int guiInventoryLabelX) {
        processes = process;
        maTier = tier;
        imageWidth = guiImageWidth;
        inventoryLabelX = guiInventoryLabelX;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }
}
