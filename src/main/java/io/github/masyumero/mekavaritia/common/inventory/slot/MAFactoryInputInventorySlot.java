package io.github.masyumero.mekavaritia.common.inventory.slot;

import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import mekanism.api.IContentsListener;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.slot.InputInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MAFactoryInputInventorySlot extends InputInventorySlot {

    private final TileEntityMAFactory<?> factory;

    public static MAFactoryInputInventorySlot create(TileEntityMAFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IContentsListener listener, int x, int y) {
        return create(factory, process, outputSlot, null, listener, x, y);
    }

    public static MAFactoryInputInventorySlot create(TileEntityMAFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(factory, "Factory cannot be null");
        Objects.requireNonNull(outputSlot, "Primary output slot cannot be null");
        return new MAFactoryInputInventorySlot(factory, process, outputSlot, secondaryOutputSlot, listener, x, y);
    }

    private MAFactoryInputInventorySlot(TileEntityMAFactory<?> factory, int process, IInventorySlot outputSlot, @Nullable IInventorySlot secondaryOutputSlot, @Nullable IContentsListener listener, int x, int y) {
        super(stack -> factory.isValidInputItem(stack) && factory.inputProducesOutput(process, stack, outputSlot, secondaryOutputSlot, false), factory::isValidInputItem, listener, x, y);
        this.factory = factory;
    }

    public void setStackUnchecked(@NotNull ItemStack stack) {
        super.setStackUnchecked(stack);
    }

    @Override
    public int getLimit(@NotNull ItemStack stack) {
        if (factory != null) {
            return switch (factory.tier) {
                case PRISMATIC -> super.getLimit(stack) * 10;
                case FLARE -> super.getLimit(stack) * 20;
                case NEURAL -> super.getLimit(stack) * 30;
                case ETERNAL -> super.getLimit(stack) * 128;
            };
        }
        return super.getLimit(stack);
    }
}
