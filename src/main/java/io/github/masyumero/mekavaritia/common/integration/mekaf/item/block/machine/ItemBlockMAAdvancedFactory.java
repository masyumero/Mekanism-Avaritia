package io.github.masyumero.mekavaritia.common.integration.mekaf.item.block.machine;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.integration.mekaf.block.prefab.BlockMAAdvancedFactory;
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

public class ItemBlockMAAdvancedFactory extends MAItemBlockMachine {

    public ItemBlockMAAdvancedFactory(BlockMAAdvancedFactory<?> block) {
        super(block);
    }

    @Override
    public MAFactoryTier getMATier() {
        return MAAttribute.getTier(getBlock(), MAFactoryTier.class);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        Attribute.ifPresent(getBlock(), AttributeAdvancedFactoryType.class, attribute -> tooltip.add(MekanismLang.FACTORY_TYPE.translateColored(EnumColor.INDIGO, EnumColor.GRAY,
                attribute.getAdvancedFactoryType())));
        super.addTypeDetails(stack, world, tooltip, flag);
    }
}
