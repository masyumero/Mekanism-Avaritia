package io.github.masyumero.mekavaritia.common.integration.mekaf.inventory.slot;

import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.TileEntityMAPressurizedReactingFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.*;
import mekanism.api.IContentsListener;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.chemical.merged.MergedChemicalTank;
import mekanism.api.chemical.pigment.IPigmentTank;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MAAdvancedFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityMAAdvancedFactoryBase<?> factory;

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAItemToPigmentFactory<?> factory, int process, IPigmentTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Pigment output tank cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAItemToPigmentFactory<?> factory, int process, IPigmentTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAItemToGasFactory<?> factory, int process, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Gas output tank cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAItemToGasFactory<?> factory, int process, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAItemToMergedFactory<?> factory, int process, MergedChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Chemical output tank cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAItemToMergedFactory<?> factory, int process, MergedChemicalTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAItemToFluidFactory<?> factory, int process, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputTank, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAItemToFluidFactory<?> factory, int process, IExtendedFluidTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputSlot, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAItemToItemAdvancedFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.inputProducesOutput(process, stack, outputSlot, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public static MAAdvancedFactoryInputInventorySlot create(TileEntityMAPressurizedReactingFactory factory, int process, IInventorySlot outputSlot, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputTank, "Fluid output tank cannot be null");
        return new MAAdvancedFactoryInputInventorySlot(factory, process, outputSlot, outputTank, listener, x, y);
    }

    private MAAdvancedFactoryInputInventorySlot(TileEntityMAPressurizedReactingFactory factory, int process, IInventorySlot outputSlot, IGasTank outputTank, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isItemValidForSlot(stack) && factory.inputProducesOutput(process, stack, outputSlot, outputTank, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public void setStackUnchecked(@NotNull ItemStack stack) {
        super.setStackUnchecked(stack);
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}