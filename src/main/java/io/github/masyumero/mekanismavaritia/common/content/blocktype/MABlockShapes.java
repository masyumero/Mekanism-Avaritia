package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import mekanism.common.content.blocktype.BlockShapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class MABlockShapes {
    public static VoxelShape[] getShape(MAFactoryTier tier, MAFactoryType type) {
        return switch (type) {
            case ALLOYING, SMELTING -> BlockShapes.SMELTING_FACTORY;
            case ENRICHING -> BlockShapes.ENRICHING_FACTORY;
            case CRUSHING -> BlockShapes.CRUSHING_FACTORY;
            case COMPRESSING -> BlockShapes.COMPRESSING_FACTORY;
            case COMBINING -> BlockShapes.COMBINING_FACTORY;
            case PURIFYING -> BlockShapes.PURIFYING_FACTORY;
            case INJECTING -> BlockShapes.INJECTING_FACTORY;
            case INFUSING -> BlockShapes.INFUSING_FACTORY;
            case SAWING -> BlockShapes.SAWING_FACTORY;
        };
    }
}
