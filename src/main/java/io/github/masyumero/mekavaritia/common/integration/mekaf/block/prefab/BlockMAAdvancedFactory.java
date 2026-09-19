package io.github.masyumero.mekavaritia.common.integration.mekaf.block.prefab;

import io.github.masyumero.mekavaritia.common.block.prefab.BlockMAFactoryMachine;
import io.github.masyumero.mekavaritia.common.integration.mekaf.content.blocktype.MAAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;

import mekanism.common.resource.BlockResourceInfo;

public class BlockMAAdvancedFactory<TILE extends TileEntityMAAdvancedFactoryBase<?>> extends BlockMAFactoryMachine.BlockMAFactoryMachineModel<TILE, MAAdvancedFactory<TILE>> {

    public BlockMAAdvancedFactory(MAAdvancedFactory<TILE> factoryType) {
        super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
    }
}
