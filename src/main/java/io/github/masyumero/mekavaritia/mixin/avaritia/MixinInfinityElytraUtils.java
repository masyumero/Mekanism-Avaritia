package io.github.masyumero.mekavaritia.mixin.avaritia;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import committee.nova.mods.avaritia.util.InfinityElytraUtils;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = InfinityElytraUtils.class, remap = false)
public class MixinInfinityElytraUtils {

    @ModifyReturnValue(method = "hasInfinityElytraEquipped", at = @At("RETURN"))
    private static boolean hasInfinityElytraEquippedModifyReturnValue(boolean original, @Local(argsOnly = true) Player player) {
        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!chestStack.isEmpty()) {
            IModule<?> module = MAModuleUtil.getModule(chestStack, MAModules.INFINITY_ELYTRA_UNIT);
            if (module == null || !module.isEnabled()) {
                return original;
            }
        } else {
            return original;
        }
        return true;
    }
}
