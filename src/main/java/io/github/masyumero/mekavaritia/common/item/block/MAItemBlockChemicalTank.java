package io.github.masyumero.mekavaritia.common.item.block;

import java.util.List;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.capabilities.chemical.item.MAChemicalTankContentsHandler;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekavaritia.common.tier.MACTTier;
import io.github.masyumero.mekavaritia.common.tile.MATileEntityChemicalTank;

import mekanism.api.functions.ConstantPredicates;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.util.ChemicalUtil;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.TextUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MAItemBlockChemicalTank extends MAItemBlockTooltip<BlockTile.BlockTileModel<MATileEntityChemicalTank, MAMachine<MATileEntityChemicalTank>>> implements IItemSustainedInventory {

    public MAItemBlockChemicalTank(BlockTile.BlockTileModel<MATileEntityChemicalTank, MAMachine<MATileEntityChemicalTank>> block) {
        super(block);
    }

    @Override
    public MACTTier getMATier() {
        return MAAttribute.getTier(getBlock(), MACTTier.class);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        MACTTier tier = getMATier();
        StorageUtils.addStoredSubstance(stack, tooltip, false);
        tooltip.add(MekanismLang.CAPACITY_MB.translateColored(EnumColor.INDIGO, EnumColor.GRAY, TextUtils.format(tier.getStorage())));
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        // No bar for empty containers as bars are drawn on top of stack count number
        return ChemicalUtil.hasGas(stack) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.INFUSION_HANDLER) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.PIGMENT_HANDLER) ||
                ChemicalUtil.hasChemical(stack, ConstantPredicates.alwaysTrue(), Capabilities.SLURRY_HANDLER);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ChemicalUtil.getRGBDurabilityForDisplay(stack);
    }

    @Override
    protected void gatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt) {
        super.gatherCapabilities(capabilities, stack, nbt);
        capabilities.add(MAChemicalTankContentsHandler.create(getMATier()));
    }
}