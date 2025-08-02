package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.VoxelShapeUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class MABlockShapes {

    private static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return Block.box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public static final VoxelShape[] ELECTRIC_NEUTRON_COLLECTOR = new VoxelShape[EnumUtils.HORIZONTAL_DIRECTIONS.length];

    static {
        VoxelShapeUtils.setShape(VoxelShapeUtils.rotate(VoxelShapeUtils.combine(
                box(12, 0, 14,16, 4, 16),
                box(4, 0, 14,12, 4, 16),
                box(4, 4, 14,12, 12, 14),
                box(0, 0, 14,4, 4, 16),
                box(0, 0, 14,4, 4, 16),
                box(0, 12, 14,16, 16, 16),
                box(0, 0, 0,16, 7, 14),
                box(13, 7, 0,16, 9, 14),
                box(3, 7, 1,13, 9, 15),
                box(4, 4, 16,12, 12, 16),
                box(4, 4, 14,12, 12, 16),
                box(3, 7, 0,13, 9, 1),
                box(0, 7, 0,3, 9, 14),
                box(0, 9, 0,16, 16, 14)
        ), Rotation.NONE), ELECTRIC_NEUTRON_COLLECTOR);
    }

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
