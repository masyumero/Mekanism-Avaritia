package io.github.masyumero.mekavaritia.mixin.avaritia;

import committee.nova.mods.avaritia.common.item.tools.InfinityArmorItem;
import committee.nova.mods.avaritia.init.handler.AbilityHandler;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(value = AbilityHandler.class, remap = false)
public abstract class MixinAbilityHandler{

    @Redirect(method = "updateAbilities", at = @At(value = "INVOKE", target = "Lcommittee/nova/mods/avaritia/util/ToolUtils;isPlayerWearing(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;)Z"))
    private static boolean hasArmorInject(LivingEntity entity, EquipmentSlot slot, Predicate<Item> predicate) {
        return mekanismavaritia$checkArmor(entity, slot);
    }

    @Unique
    private static boolean mekanismavaritia$checkArmor(LivingEntity entity, EquipmentSlot slot) {
        ItemStack item = entity.getItemBySlot(slot);
        if (item.getItem() instanceof ItemMekaSuitArmor) {
            IModule<?> infinityUnit = switch (slot) {
                case HEAD -> MAModuleUtil.getModule(item, MAModules.NEBULIGHT_UNIT);
                case CHEST -> MAModuleUtil.getModule(item, MAModules.CELESTIAL_UNIT);
                case LEGS -> MAModuleUtil.getModule(item, MAModules.STARFEAST_UNIT);
                case FEET -> MAModuleUtil.getModule(item, MAModules.LIGHTSPEED_UNIT);
                default -> throw new IllegalStateException("Unexpected value: " + slot);
            };
            return infinityUnit != null && infinityUnit.isEnabled();
        }
        return item.getItem() instanceof InfinityArmorItem;
    }
}
