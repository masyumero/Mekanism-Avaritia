package io.github.masyumero.mekavaritia.mixin.mekanism;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.masyumero.mekavaritia.common.content.gear.mekaweapons.ModuleInfinityDamageUnit;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import meranha.mekaweapons.MekaWeaponsUtils;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MekaWeaponsUtils.class, remap = false)
public class MixinMekaWeaponsUtils {

    @ModifyReturnValue(method = "getTotalDamage", at = @At(value = "RETURN", ordinal = 1))
    private static long totalDamageModify(long original, @Local(argsOnly = true) ItemStack itemStack) {
        IModule<ModuleInfinityDamageUnit> unit = MAModuleUtil.getModule(itemStack, MAModules.INFINITY_DAMAGE_UNIT);
        if (unit != null && unit.isEnabled()) {
            return Long.MAX_VALUE;
        }
        return original;
    }
}
