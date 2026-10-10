package io.github.masyumero.mekavaritia.common.inventory;

import io.github.masyumero.mekavaritia.common.inventory.slot.MABinInventorySlot;
import io.github.masyumero.mekavaritia.common.item.block.MAItemBlockBin;
import mekanism.api.inventory.IInventorySlot;
import mekanism.common.inventory.ItemStackMekanismInventory;

import net.minecraft.world.item.ItemStack;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Getter
public class MABinMekanismInventory extends ItemStackMekanismInventory {

    private MABinInventorySlot binSlot;

    private MABinMekanismInventory(@NotNull ItemStack stack) {
        super(stack);
    }

    @NotNull
    @Override
    protected List<IInventorySlot> getInitialInventory() {
        binSlot = MABinInventorySlot.create(this, ((MAItemBlockBin) stack.getItem()).getMATier());
        return Collections.singletonList(binSlot);
    }

    @Nullable
    public static MABinMekanismInventory create(@NotNull ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof MAItemBlockBin) {
            return new MABinMekanismInventory(stack);
        }
        return null;
    }
}