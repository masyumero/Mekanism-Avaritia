package io.github.masyumero.mekavaritia.api.mixin;

import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import net.minecraft.core.BlockPos;

public interface IMixinMatrixEnergyContainer {

    void mekavaritia$addMACell(BlockPos pos, TileEntityMAInductionCell cell);

    void mekavaritia$addMAProvider(BlockPos pos, TileEntityMAInductionProvider provider);

    void mekavaritia$removeInternal(BlockPos pos);
}
