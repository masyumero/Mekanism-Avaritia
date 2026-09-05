package io.github.masyumero.mekavaritia.common.tile.multiblock;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAIPTier;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityMAInductionProvider extends TileEntityInternalMultiblock {

    public MAIPTier tier;

    public TileEntityMAInductionProvider(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = MAAttribute.getTier(getBlockType(), MAIPTier.class);
    }
}
