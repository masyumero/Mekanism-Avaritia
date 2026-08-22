package io.github.masyumero.mekavaritia.common.block.transmitter;

import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMAPressurizedTube;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.TubeTier;

public class MABlockPressurizedTube extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityMAPressurizedTube> {

    @Getter
    private final TubeTier tier;

    public MABlockPressurizedTube(TubeTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityMAPressurizedTube> getTileType() {
        return switch (tier) {
            case BASIC -> MATileEntityTypes.PRISMATIC_PRESSURIZED_TUBE;
            case ADVANCED -> MATileEntityTypes.FLARE_PRESSURIZED_TUBE;
            case ELITE -> MATileEntityTypes.NEURAL_PRESSURIZED_TUBE;
            case ULTIMATE -> MATileEntityTypes.ETERNAL_PRESSURIZED_TUBE;
        };
    }
}