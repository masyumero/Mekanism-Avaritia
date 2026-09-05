package io.github.masyumero.mekavaritia.common.item.block;

import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAIPTier;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.util.text.EnergyDisplay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MAItemBlockInductionProvider extends MAItemBlockTooltip<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>> {

    public MAItemBlockInductionProvider(BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>> block) {
        super(block, new Properties());
    }

    @Override
    @NotNull
    public MAIPTier getMATier() {
        return Objects.requireNonNull(MAAttribute.getTier(getBlock(), MAIPTier.class));
    }

    @Override
    protected void addStats(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        MAIPTier tier = getMATier();
        tooltip.add(MekanismLang.INDUCTION_PORT_OUTPUT_RATE.translateColored(tier.getMATier().getColor(), EnumColor.GRAY, EnergyDisplay.of(tier.getOutput())));
    }
}
