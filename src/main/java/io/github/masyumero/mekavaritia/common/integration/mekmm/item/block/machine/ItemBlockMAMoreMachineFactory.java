package io.github.masyumero.mekavaritia.common.integration.mekmm.item.block.machine;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.integration.mekmm.block.prefab.BlockMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.item.block.machine.MAItemBlockMachine;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemBlockMAMoreMachineFactory extends MAItemBlockMachine {

    public ItemBlockMAMoreMachineFactory(BlockMAMoreMachineFactory<?> block) {
        super(block);
    }

    @Override
    public IMATier getMATier() {
        return MAAttribute.getTier(getBlock(), MAFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Attribute.ifPresent(getBlock(), AttributeMoreMachineFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getMoreMachineFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}