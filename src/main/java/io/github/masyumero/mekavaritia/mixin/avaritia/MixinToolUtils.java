package io.github.masyumero.mekavaritia.mixin.avaritia;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import committee.nova.mods.avaritia.util.ToolUtils;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ToolUtils.class, remap = false)
public class MixinToolUtils {

    @ModifyReturnValue(method = "isPlayerWearing", at = @At("RETURN"))
    private static boolean isPlayerWearingInjectReturn(boolean original, @Local(argsOnly = true) EquipmentSlot slot, @Local(name = "stack") ItemStack stack) {
        return mekanism_avaritia$checkMekaSuit(original, slot, stack);
    }

    @ModifyReturnValue(method = "isInfinite", at = @At(value = "RETURN", ordinal = 0))
    private static boolean isInfiniteModifyReturnValue(boolean original, @Local(name = "slot") EquipmentSlot slot, @Local(name = "stack") ItemStack stack) {
        return mekanism_avaritia$checkMekaSuit(original, slot, stack);
    }

    @Unique
    private static boolean mekanism_avaritia$checkMekaSuit(boolean original, EquipmentSlot slot,ItemStack stack) {
        IModule<?> infinityUnit = switch (slot) {
            case HEAD -> MAModuleUtil.getModule(stack, MAModules.NEBULIGHT_UNIT);
            case CHEST -> MAModuleUtil.getModule(stack, MAModules.CELESTIAL_UNIT);
            case LEGS -> MAModuleUtil.getModule(stack, MAModules.STARFEAST_UNIT);
            case FEET -> MAModuleUtil.getModule(stack, MAModules.LIGHTSPEED_UNIT);
            default -> null;
        };
        if (stack.isEmpty() || infinityUnit == null || !infinityUnit.isEnabled() || !(stack.getItem() instanceof ItemMekaSuitArmor)) {
            return original;
        }
        return true;
    }

    @ModifyReturnValue(method = "isWearingInfinityHelmet", at = @At("RETURN"))
    private static boolean isWearingInfinityHelmetModifyReturnValue(boolean original, @Local(name = "helmet") ItemStack stack) {
        IModule<?> infinityUnit = MAModuleUtil.getModule(stack, MAModules.NEBULIGHT_UNIT);
        return original || (!stack.isEmpty() && infinityUnit != null && infinityUnit.isEnabled() || stack.getItem() instanceof ItemMekaSuitArmor);
    }

    @ModifyReturnValue(method = "isWearingInfinityChestplate", at = @At("RETURN"))
    private static boolean isWearingInfinityChestplateModifyReturnValue(boolean original, @Local(name = "chestplate") ItemStack stack) {
        IModule<?> infinityUnit = MAModuleUtil.getModule(stack, MAModules.CELESTIAL_UNIT);
        return original || (!stack.isEmpty() && infinityUnit != null && infinityUnit.isEnabled() || stack.getItem() instanceof ItemMekaSuitArmor);
    }

    @ModifyReturnValue(method = "isWearingInfinityPants", at = @At("RETURN"))
    private static boolean isWearingInfinityPantsModifyReturnValue(boolean original, @Local(name = "leggings") ItemStack stack) {
        IModule<?> infinityUnit = MAModuleUtil.getModule(stack, MAModules.STARFEAST_UNIT);
        return original || (!stack.isEmpty() && infinityUnit != null && infinityUnit.isEnabled() || stack.getItem() instanceof ItemMekaSuitArmor);
    }

    @ModifyReturnValue(method = "isWearingInfinityBoots", at = @At("RETURN"))
    private static boolean isWearingInfinityBootsModifyReturnValue(boolean original, @Local(name = "boots") ItemStack stack) {
        IModule<?> infinityUnit = MAModuleUtil.getModule(stack, MAModules.LIGHTSPEED_UNIT);
        return original || (!stack.isEmpty() && infinityUnit != null && infinityUnit.isEnabled() || stack.getItem() instanceof ItemMekaSuitArmor);
    }
}
