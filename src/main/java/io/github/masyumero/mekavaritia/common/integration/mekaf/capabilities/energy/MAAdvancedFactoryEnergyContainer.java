package io.github.masyumero.mekavaritia.common.integration.mekaf.capabilities.energy;

import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
import mekanism.api.AutomationType;
import mekanism.api.IContentsListener;
import mekanism.api.math.FloatingLong;
import mekanism.common.block.attribute.AttributeEnergy;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class MAAdvancedFactoryEnergyContainer extends MachineEnergyContainer<TileEntityMAAdvancedFactoryBase<?>> {

    public static MAAdvancedFactoryEnergyContainer input(TileEntityMAAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        AttributeEnergy electricBlock = validateBlock(tile);
        return new MAAdvancedFactoryEnergyContainer(electricBlock.getStorage(), electricBlock.getUsage(), notExternal, alwaysTrue, tile, listener);
    }

    private MAAdvancedFactoryEnergyContainer(FloatingLong maxEnergy, FloatingLong energyPerTick, Predicate<AutomationType> canExtract, Predicate<AutomationType> canInsert, TileEntityMAAdvancedFactoryBase<?> tile, @Nullable IContentsListener listener) {
        super(maxEnergy, energyPerTick, canExtract, canInsert, tile, listener);
    }

    @NotNull
    public FloatingLong getBaseEnergyPerTick() {
        return super.getBaseEnergyPerTick().add(this.tile.getRecipeEnergyRequired());
    }
}
