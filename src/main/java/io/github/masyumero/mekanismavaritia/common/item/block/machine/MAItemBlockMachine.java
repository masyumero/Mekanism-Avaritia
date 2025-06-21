package io.github.masyumero.mekanismavaritia.common.item.block.machine;

import io.github.masyumero.mekanismavaritia.common.item.block.MAItemBlockTooltip;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.item.interfaces.IItemSustainedInventory;

public class MAItemBlockMachine extends MAItemBlockTooltip<BlockTile<?, ?>> implements IItemSustainedInventory {

    public MAItemBlockMachine(BlockTile<?, ?> block) {
        super(block);
    }
}
