package io.github.masyumero.mekavaritia.mixin.mekanism.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixEnergyContainer;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import io.github.masyumero.mekavaritia.api.mixin.IMixinMatrixEnergyContainer;
import io.github.masyumero.mekavaritia.api.mixin.IMixinMatrixMultiblockData;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import mekanism.common.content.matrix.MatrixEnergyContainer;
import mekanism.common.content.matrix.MatrixMultiblockData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class MixinTargetMatrixMultiblockData{

    @Mixin(value = MatrixMultiblockData.class, remap = false)
    public static abstract class MixinMatrixMultiblockData implements IMixinMatrixMultiblockData {

        @Shadow
        @Final
        private @NotNull MatrixEnergyContainer energyContainer;

        @Override
        public void mekavaritia$addCell(TileEntityMAInductionCell cell) {
            ((IMixinMatrixEnergyContainer)energyContainer).mekavaritia$addMACell(cell.getBlockPos(), cell);
        }

        @Override
        public void mekavaritia$addProvider(TileEntityMAInductionProvider provider) {
            ((IMixinMatrixEnergyContainer)energyContainer).mekavaritia$addMAProvider(provider.getBlockPos(), provider);
        }
    }

    @Mixin(value = ExtraMatrixMultiblockData.class, remap = false)
    public static abstract class MixinExtraMatrixMultiblockData implements IMixinMatrixMultiblockData {

        @Shadow
        @Final
        private @NotNull ExtraMatrixEnergyContainer energyContainer;

        @Override
        public void mekavaritia$addCell(TileEntityMAInductionCell cell) {
            ((IMixinMatrixEnergyContainer)energyContainer).mekavaritia$addMACell(cell.getBlockPos(), cell);
        }

        @Override
        public void mekavaritia$addProvider(TileEntityMAInductionProvider provider) {
            ((IMixinMatrixEnergyContainer)energyContainer).mekavaritia$addMAProvider(provider.getBlockPos(), provider);
        }
    }
}
