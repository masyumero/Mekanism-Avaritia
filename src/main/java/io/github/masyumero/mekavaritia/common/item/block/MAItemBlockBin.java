package io.github.masyumero.mekavaritia.common.item.block;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.block.basic.MABlockBin;
import io.github.masyumero.mekavaritia.common.inventory.MABinMekanismInventory;
import io.github.masyumero.mekavaritia.common.inventory.slot.MABinInventorySlot;
import io.github.masyumero.mekavaritia.common.tier.MABTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.util.text.TextUtils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MAItemBlockBin extends MAItemBlockTooltip<MABlockBin> implements IItemSustainedInventory {

    public MAItemBlockBin(MABlockBin block) {
        super(block, new Item.Properties().stacksTo(1));
    }

    @Override
    public MABTier getMATier() {
        return MAAttribute.getTier(getBlock(), MABTier.class);
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        MABinMekanismInventory inventory = MABinMekanismInventory.create(stack);
        MABTier tier = getMATier();
        if (inventory != null && tier != null) {
            MABinInventorySlot slot = inventory.getBinSlot();
            if (slot.isEmpty()) {
                tooltip.add(MekanismLang.EMPTY.translateColored(EnumColor.DARK_RED));
            } else {
                tooltip.add(MekanismLang.STORING.translateColored(EnumColor.BRIGHT_GREEN, EnumColor.GRAY, slot.getStack()));
                tooltip.add(MekanismLang.ITEM_AMOUNT.translateColored(EnumColor.PURPLE, EnumColor.GRAY, TextUtils.format(slot.getCount())));
            }
            if (slot.isLocked()) {
                tooltip.add(MekanismLang.LOCKED.translateColored(EnumColor.AQUA, EnumColor.GRAY, slot.getLockStack()));
            }
            tooltip.add(MekanismLang.CAPACITY_ITEMS.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(tier.getStorage())));
        }
    }

    @Override
    public boolean canContentsDrop(ItemStack stack) {
        return true;
    }
}