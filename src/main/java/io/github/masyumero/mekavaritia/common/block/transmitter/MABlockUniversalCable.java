package io.github.masyumero.mekavaritia.common.block.transmitter;

import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMAUniversalCable;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.CableTier;

public class MABlockUniversalCable extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityMAUniversalCable> {

    @Getter
    private final CableTier tier;

    public MABlockUniversalCable(CableTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityMAUniversalCable> getTileType() {
        return switch (tier) {
            case BASIC -> MATileEntityTypes.PRISMATIC_UNIVERSAL_CABLE;
            case ADVANCED -> MATileEntityTypes.FLARE_UNIVERSAL_CABLE;
            case ELITE -> MATileEntityTypes.NEURAL_UNIVERSAL_CABLE;
            case ULTIMATE -> MATileEntityTypes.ETERNAL_UNIVERSAL_CABLE;
        };
    }
}