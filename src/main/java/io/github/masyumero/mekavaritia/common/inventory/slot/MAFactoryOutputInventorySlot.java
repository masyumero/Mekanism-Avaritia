package io.github.masyumero.mekavaritia.common.inventory.slot;

import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.container.slot.ContainerSlotType;
import mekanism.common.inventory.slot.BasicInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@NothingNullByDefault
public class MAFactoryOutputInventorySlot extends BasicInventorySlot {

    private final TileEntityMAFactory<?> factory;

    public static MAFactoryOutputInventorySlot at(TileEntityMAFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        return new MAFactoryOutputInventorySlot(factory, listener, x, y);
    }

    private MAFactoryOutputInventorySlot(TileEntityMAFactory<?> factory, @Nullable IContentsListener listener, int x, int y) {
        super(alwaysTrueBi, internalOnly, alwaysTrue, listener, x, y);
        setSlotType(ContainerSlotType.OUTPUT);
        this.factory = factory;
    }

    @Override
    public int getLimit(ItemStack stack) {
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
