package io.github.masyumero.mekavaritia.mixin.ticex;

import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.common.content.gear.Module;
import moffy.ticex.item.modifiable.ModifiableMekaTool;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

@Mixin(value = ModifiableMekaTool.class, remap = false)
public abstract class MixinModifiableMekaTool extends ModifiableItem {

    public MixinModifiableMekaTool(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        for (Module<?> module : MAModuleUtil.getModules(stack)) {
            if (entityIn instanceof Player) {
                module.tick((Player) entityIn);
            }
        }
    }
}
