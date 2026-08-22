package io.github.masyumero.mekavaritia.common.content.network.transmitter;

import io.github.masyumero.mekavaritia.common.tier.transmitter.MAPTier;
import mekanism.api.Action;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.content.network.transmitter.MechanicalPipe;
import mekanism.common.lib.transmitter.ConnectionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.common.upgrade.transmitter.MechanicalPipeUpgradeData;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;

import net.minecraft.core.Direction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class MAMechanicalPipe extends MechanicalPipe implements IMAUpgradeableTransmitter<MechanicalPipeUpgradeData> {

    public MAMechanicalPipe(IBlockProvider blockProvider, TileEntityTransmitter tile) {
        super(blockProvider, tile);
    }

    @Override
    public void pullFromAcceptors() {
        Set<Direction> connections = this.getConnections(ConnectionType.PULL);
        if (!connections.isEmpty()) {

            for (IFluidHandler connectedAcceptor : this.getAcceptorCache().getConnectedAcceptors(connections)) {
                FluidStack bufferWithFallback = this.getBufferWithFallback();
                FluidStack received;
                if (bufferWithFallback.isEmpty()) {
                    received = connectedAcceptor.drain(this.getAvailablePull(), FluidAction.SIMULATE);
                } else {
                    received = connectedAcceptor.drain(new FluidStack(bufferWithFallback, this.getAvailablePull()), FluidAction.SIMULATE);
                }

                if (!received.isEmpty() && this.takeFluid(received, Action.SIMULATE).isEmpty()) {
                    this.takeFluid(connectedAcceptor.drain(received, FluidAction.EXECUTE), Action.EXECUTE);
                }
            }
        }
    }

    private int getAvailablePull() {
        return this.hasTransmitterNetwork() ? Math.min(MAPTier.getPipePullAmount(this.tier), this.getTransmitterNetwork().fluidTank.getNeeded()) : Math.min(MAPTier.getPipePullAmount(this.tier), this.buffer.getNeeded());
    }

    @Override
    public long getCapacity() {
        return MAPTier.getPipeCapacity(this.tier);
    }

    @Nullable
    @Override
    public MechanicalPipeUpgradeData getUpgradeData() {
        return super.getUpgradeData();
    }

    @Override
    public boolean dataTypeMatches(@NotNull TransmitterUpgradeData data) {
        return super.dataTypeMatches(data);
    }

    @Override
    public void parseUpgradeData(@NotNull MechanicalPipeUpgradeData data) {
        super.parseUpgradeData(data);
    }
}