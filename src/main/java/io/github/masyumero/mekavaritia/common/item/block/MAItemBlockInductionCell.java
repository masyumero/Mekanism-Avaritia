package io.github.masyumero.mekavaritia.common.item.block;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAICTier;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MAItemBlockInductionCell extends MAItemBlockTooltip<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>> {

    public MAItemBlockInductionCell(BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>> block) {
        super(block, new Properties());
    }

    @Override
    @NotNull
    public MAICTier getMATier() {
        return Objects.requireNonNull(MAAttribute.getTier(getBlock(), MAICTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        MAICTier tier = getMATier();
        tooltip.add(MekanismLang.CAPACITY.translateColored(tier.getMATier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getMaxEnergy())));
        tooltip.add(MekanismLang.STORED_ENERGY.translateColored(EnumColor.BRIGHT_GREEN, EnumColor.GRAY, EnergyDisplay.of(StorageUtils.getStoredEnergyFromNBT(stack),
                tier.getMaxEnergy())));
    }
}
