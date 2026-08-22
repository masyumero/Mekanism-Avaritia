package io.github.masyumero.mekavaritia.common.item.block.transmitter;

import io.github.masyumero.mekavaritia.common.block.transmitter.MABlockThermodynamicConductor;
import io.github.masyumero.mekavaritia.common.tier.transmitter.MATCTier;

import io.github.masyumero.mekavaritia.common.util.MATransporterUtils;
import mekanism.api.text.EnumColor;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.key.MekKeyHandler;
import mekanism.client.key.MekanismKeyHandler;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.item.block.ItemBlockMekanism;
import mekanism.common.tier.ConductorTier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MAItemBlockThermodynamicConductor extends ItemBlockMekanism<MABlockThermodynamicConductor> {

    public MAItemBlockThermodynamicConductor(MABlockThermodynamicConductor block) {
        super(block, new Properties());
    }

    @NotNull
    @Override
    public ConductorTier getTier() {
        return Objects.requireNonNull(Attribute.getTier(getBlock(), ConductorTier.class));
    }

    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        return TextComponentUtil.build(TextColor.fromRgb(MATransporterUtils.getColorFromBaseTier(getTier().getBaseTier())), super.getName(stack));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        if (MekKeyHandler.isKeyPressed(MekanismKeyHandler.detailsKey)) {
            tooltip.add(MekanismLang.CAPABLE_OF_TRANSFERRING.translateColored(EnumColor.DARK_GRAY));
            tooltip.add(MekanismLang.HEAT.translateColored(EnumColor.PURPLE, MekanismLang.MEKANISM));
        } else {
            tooltip.add(MekanismLang.CONDUCTION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, MATCTier.getConduction(this.getTier())));
            tooltip.add(MekanismLang.INSULATION.translateColored(EnumColor.INDIGO, EnumColor.GRAY, MATCTier.getConductionInsulation(this.getTier())));
            tooltip.add(MekanismLang.HEAT_CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, MATCTier.getHeatCapacity(this.getTier())));
            tooltip.add(MekanismLang.HOLD_FOR_DETAILS.translateColored(EnumColor.GRAY, EnumColor.INDIGO, MekanismKeyHandler.detailsKey.getTranslatedKeyMessage()));
        }
    }
}