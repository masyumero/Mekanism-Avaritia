package io.github.masyumero.mekavaritia.api.mixin;

import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;

public interface IMixinMatrixMultiblockData {

    void mekavaritia$addCell(TileEntityMAInductionCell cell);

    void mekavaritia$addProvider(TileEntityMAInductionProvider provider);
}
