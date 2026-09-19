package io.github.masyumero.mekavaritia.common.integration.mekaf.inventory.slot;

import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class MAAdvancedFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityMAAdvancedFactoryBase<?> factory;

    public static MAAdvancedFactoryOutputInventorySlot at(TileEntityMAAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new MAAdvancedFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private MAAdvancedFactoryOutputInventorySlot(TileEntityMAAdvancedFactoryBase<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        this.setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
        try {
            return Math.multiplyExact(super.getLimit(stack), 8 << factory.tier.ordinal());
        } catch (ArithmeticException ignored) {
            return Integer.MAX_VALUE;
        }
    }
}