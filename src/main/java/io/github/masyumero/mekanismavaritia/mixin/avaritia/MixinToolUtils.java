package io.github.masyumero.mekanismavaritia.mixin.avaritia;

import committee.nova.mods.avaritia.common.item.tools.InfinityArmorItem;
import committee.nova.mods.avaritia.util.ToolUtils;
import io.github.masyumero.mekanismavaritia.common.content.gear.shared.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import io.github.masyumero.mekanismavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ToolUtils.class,remap = false)
public class MixinToolUtils {

    @Inject(method = "isInfinite",at = @At(value = "RETURN"), cancellable = true)
    private static void isInfiniteInject(LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) {
                continue;
            }
            ItemStack stack = player.getItemBySlot(slot);
            if (stack.isEmpty() || !(stack.getItem() instanceof InfinityArmorItem)) {
                cir.setReturnValue(false);
            }
            if (stack.isEmpty() || !(stack.getItem() instanceof ItemMekaSuitArmor)) {
                IModule<ModuleCosmicUnit> cosmicUnit = MAModuleUtil.getModule(stack, MAModules.COSMIC_UNIT);
                if(cosmicUnit == null || !cosmicUnit.isEnabled()) {
                    cir.setReturnValue(false);
                }
            }
        }
        cir.setReturnValue(true);
    }
}
