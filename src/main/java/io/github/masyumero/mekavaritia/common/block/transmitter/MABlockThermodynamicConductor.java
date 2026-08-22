package io.github.masyumero.mekavaritia.common.block.transmitter;

import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMAThermodynamicConductor;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockSmallTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.ConductorTier;

public class MABlockThermodynamicConductor extends BlockSmallTransmitter implements ITypeBlock, IHasTileEntity<TileEntityMAThermodynamicConductor> {

    @Getter
    private final ConductorTier tier;

    public MABlockThermodynamicConductor(ConductorTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(tier);
    }

    @Override
    public TileEntityTypeRegistryObject<TileEntityMAThermodynamicConductor> getTileType() {
        return switch (tier) {
            case BASIC -> MATileEntityTypes.PRISMATIC_THERMODYNAMIC_CONDUCTOR;
            case ADVANCED -> MATileEntityTypes.FLARE_THERMODYNAMIC_CONDUCTOR;
            case ELITE -> MATileEntityTypes.NEURAL_THERMODYNAMIC_CONDUCTOR;
            case ULTIMATE -> MATileEntityTypes.ETERNAL_THERMODYNAMIC_CONDUCTOR;
        };
    }
}