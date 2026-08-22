package io.github.masyumero.mekavaritia.common.block.transmitter;

import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMALogisticalTransporter;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMALogisticalTransporterBase;

import lombok.Getter;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.interfaces.IHasTileEntity;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.block.transmitter.BlockLargeTransmitter;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tier.TransporterTier;


public class MABlockLogisticalTransporter extends BlockLargeTransmitter implements ITypeBlock, IHasTileEntity<TileEntityMALogisticalTransporterBase> {

    @Getter
    private final TransporterTier tier;

    public MABlockLogisticalTransporter(TransporterTier tier) {
        super(properties -> properties.mapColor(tier.getBaseTier().getMapColor()));
        this.tier = tier;
    }

    @Override
    public BlockType getType() {
        return AttributeTier.getPassthroughType(this.tier);
    }

    public TileEntityTypeRegistryObject<TileEntityMALogisticalTransporter> getTileType() {
        return switch (this.tier) {
            case BASIC -> MATileEntityTypes.PRISMATIC_LOGISTICAL_TRANSPORTER;
            case ADVANCED -> MATileEntityTypes.FLARE_LOGISTICAL_TRANSPORTER;
            case ELITE -> MATileEntityTypes.NEURAL_LOGISTICAL_TRANSPORTER;
            case ULTIMATE -> MATileEntityTypes.ETERNAL_LOGISTICAL_TRANSPORTER;
        };
    }
}