package io.github.masyumero.mekavaritia.common.integration.mekmm.block.prefab;

import io.github.masyumero.mekavaritia.common.block.prefab.BlockMAFactoryMachine.BlockMAFactoryMachineModel;
import io.github.masyumero.mekavaritia.common.integration.mekmm.content.blocktype.MAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAMoreMachineFactory;

import mekanism.common.resource.BlockResourceInfo;

public class BlockMAMoreMachineFactory<TILE extends TileEntityMAMoreMachineFactory<?>> extends BlockMAFactoryMachineModel<TILE, MAMoreMachineFactory<TILE>> {

    public BlockMAMoreMachineFactory(MAMoreMachineFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
