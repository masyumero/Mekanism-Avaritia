package io.github.masyumero.mekavaritia.api.mixin.impl;

import io.github.masyumero.mekavaritia.common.registry.MABlockTypes;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

import java.util.List;

public class ImplMixinMatrixValidator {

    public static boolean mekavaritia$validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos, Level world, List<TileEntityMAInductionCell> cells, List<TileEntityMAInductionProvider> providers) {
        if (BlockType.is(state.getBlock(), MABlockTypes.PRISMATIC_INDUCTION_CELL, MABlockTypes.FLARE_INDUCTION_CELL,
                MABlockTypes.NEURAL_INDUCTION_CELL, MABlockTypes.ETERNAL_INDUCTION_CELL, MABlockTypes.PRISMATIC_INDUCTION_PROVIDER,
                MABlockTypes.FLARE_INDUCTION_PROVIDER, MABlockTypes.NEURAL_INDUCTION_PROVIDER, MABlockTypes.ETERNAL_INDUCTION_PROVIDER)) {//Compare blocks against the type before bothering to look up the tile
            BlockEntity tile = WorldUtils.getTileEntity(world, chunkMap, pos);
            if (tile instanceof TileEntityMAInductionCell cell) {
                cells.add(cell);
                return true;
            } else if (tile instanceof TileEntityMAInductionProvider provider) {
                providers.add(provider);
                return true;
            }
            //Else something went wrong
        }
        return false;
    }
}
