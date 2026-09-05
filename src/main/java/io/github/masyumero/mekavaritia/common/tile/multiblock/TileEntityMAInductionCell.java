package io.github.masyumero.mekavaritia.common.tile.multiblock;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAICTier;
import lombok.Getter;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.capabilities.holder.energy.EnergyContainerHelper;
import mekanism.common.capabilities.holder.energy.IEnergyContainerHolder;
import mekanism.common.integration.energy.EnergyCompatUtils;
import mekanism.common.tile.prefab.TileEntityInternalMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TileEntityMAInductionCell extends TileEntityInternalMultiblock {

    @Getter
    private MachineEnergyContainer<TileEntityMAInductionCell> energyContainer;
    public MAICTier tier;

    public TileEntityMAInductionCell(IBlockProvider blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
        //Never externally expose the energy capability
        addDisabledCapabilities(EnergyCompatUtils.getEnabledEnergyCapabilities());
    }

    @NotNull
    @Override
    protected IEnergyContainerHolder getInitialEnergyContainers(IContentsListener listener) {
        EnergyContainerHelper builder = EnergyContainerHelper.forSide(this::getDirection);
        builder.addContainer(energyContainer = MachineEnergyContainer.internal(this, listener));
        return builder.build();
    }

    @Override
    protected void onUpdateServer() {
        super.onUpdateServer();
        energyContainer.setEnergy(FloatingLong.MAX_VALUE);
    }

    @Override
    protected void presetVariables() {
        super.presetVariables();
        tier = MAAttribute.getTier(getBlockType(), MAICTier.class);
    }
}
