package io.github.masyumero.mekavaritia.common.tile.transmitter;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.content.network.transmitter.MALogisticalTransporter;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;

import mekanism.api.providers.IBlockProvider;
import mekanism.client.model.data.TransmitterModelData;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.block.states.TransmitterType;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.NotNull;

public class TileEntityMALogisticalTransporter extends TileEntityMALogisticalTransporterBase {

    public TileEntityMALogisticalTransporter(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    protected LogisticalTransporterBase createTransmitter(IBlockProvider blockProvider) {
        return new MALogisticalTransporter(blockProvider, this);
    }

    @Override
    public MALogisticalTransporter getTransmitter() {
        return (MALogisticalTransporter) super.getTransmitter();
    }

    @Override
    public TransmitterType getTransmitterType() {
        return TransmitterType.LOGISTICAL_TRANSPORTER;
    }

    @Override
    protected void updateModelData(TransmitterModelData modelData) {
        super.updateModelData(modelData);
        modelData.setHasColor(getTransmitter().getColor() != null);
    }

    @NotNull
    @Override
    protected BlockState upgradeResult(@NotNull BlockState current, @NotNull MATier tier) {
        return BlockStateHelper.copyStateData(current, switch (tier) {
            case PRISMATIC -> MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER;
            case FLARE -> MABlocks.FLARE_LOGISTICAL_TRANSPORTER;
            case NEURAL -> MABlocks.NEURAL_LOGISTICAL_TRANSPORTER;
            case ETERNAL -> MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER;
        });
    }
}