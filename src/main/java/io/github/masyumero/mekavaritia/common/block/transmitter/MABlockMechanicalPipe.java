package io.github.masyumero.mekavaritia.common.block.transmitter;

import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMAMechanicalPipe;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.PipeTier;

public class MABlockMechanicalPipe extends BlockLargeTransmitter implements ITypeBlock, IHasTileEntity<TileEntityMAMechanicalPipe> {

    @Getter
    private final PipeTier tier;

    public MABlockMechanicalPipe(PipeTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    public BlockType getType() {
        return AttributeTier.getPassthroughType(this.tier);
    }

    public TileEntityTypeRegistryObject<TileEntityMAMechanicalPipe> getTileType() {
        return switch (this.tier) {
            case BASIC -> MATileEntityTypes.PRISMATIC_MECHANICAL_PIPE;
            case ADVANCED -> MATileEntityTypes.FLARE_MECHANICAL_PIPE;
            case ELITE -> MATileEntityTypes.NEURAL_MECHANICAL_PIPE;
            case ULTIMATE -> MATileEntityTypes.ETERNAL_MECHANICAL_PIPE;
        };
    }
}