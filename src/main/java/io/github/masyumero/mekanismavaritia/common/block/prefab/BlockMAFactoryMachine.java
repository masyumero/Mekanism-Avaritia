package io.github.masyumero.mekanismavaritia.common.block.prefab;

import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntityMAFactory;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.resource.BlockResourceInfo;
import mekanism.common.tile.base.TileEntityMekanism;

import java.util.function.UnaryOperator;

public class BlockMAFactoryMachine<TILE extends TileEntityMekanism, MACHINE extends MAMachine.MAFactoryMachine<TILE>> extends BlockTile<TILE, MACHINE> {

    public BlockMAFactoryMachine(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
        super(machineType, propertiesModifier);
    }

    public static class BlockMAFactoryMachineModel<TILE extends TileEntityMekanism, MACHINE extends MAMachine.MAFactoryMachine<TILE>> extends BlockMAFactoryMachine<TILE, MACHINE> {

        public BlockMAFactoryMachineModel(MACHINE machineType, UnaryOperator<Properties> propertiesModifier) {
            super(machineType, propertiesModifier);
        }
    }

    public static class BlockMAFactory<TILE extends TileEntityMAFactory<?>> extends BlockMAFactoryMachineModel<TILE, MAFactory<TILE>> {

        public BlockMAFactory(MAFactory<TILE> factoryType) {
            super(factoryType, properties -> properties.mapColor(BlockResourceInfo.STEEL.getMapColor()));
        }
    }

}
