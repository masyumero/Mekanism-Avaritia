package io.github.masyumero.mekavaritia.common.capabilities.energy;

import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

@NothingNullByDefault
public class ENCMEnergyContainer extends MachineEnergyContainer<TileEntityElectricNeutronCollector> {
    public static ENCMEnergyContainer input(TileEntityElectricNeutronCollector tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new ENCMEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, alwaysTrue, tile, listener);
    }

    private ENCMEnergyContainer(FloatingLong maxEnergy, FloatingLong energyPerTick, Predicate<@NotNull AutomationType> canExtract,
                                Predicate<@NotNull AutomationType> canInsert, TileEntityElectricNeutronCollector tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    @Override
    public FloatingLong getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick().add(tile.getRecipeEnergyRequired());
    }
}
