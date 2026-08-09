package io.github.masyumero.mekavaritia.datagen.common.loot.table;


import io.github.masyumero.mekavaritia.common.registry.MABlocks;

public class MekanismAvaritiaBlockLootProvider extends BaseBlockLootTables {

    @Override
    protected void generate() {
        dropSelfWithContents(MABlocks.BLOCK.getAllBlocks());
        //dropSelfWithContents(MAAdvancedFactoryBlocks.BLOCKS.getAllBlocks());
        //dropSelfWithContents(MAMoreMachineBlocks.BLOCKS.getAllBlocks());
    }
}
