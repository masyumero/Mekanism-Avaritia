package io.github.masyumero.mekanismavaritia.common.item.block.machine;

import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeFactoryType;
import io.github.masyumero.mekanismavaritia.common.block.prefab.BlockMAFactoryMachine;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockMAFactory extends MAItemBlockMachine {

    public ItemBlockMAFactory(BlockMAFactoryMachine.BlockMAFactory<?> block) {
        super(block);
    }

    @Override
    public MAFactoryTier getMATier() {
        return MAAttribute.getTier(getBlock(), MAFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        //Should always be present but validate it just in case
        Attribute.ifPresent(getBlock(), MAAttributeFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}