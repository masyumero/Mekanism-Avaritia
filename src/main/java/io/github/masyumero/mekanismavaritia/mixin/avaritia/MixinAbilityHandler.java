package io.github.masyumero.mekanismavaritia.mixin.avaritia;

import committee.nova.mods.avaritia.common.item.tools.InfinityArmorItem;
import committee.nova.mods.avaritia.init.handler.AbilityHandler;
import io.github.masyumero.mekanismavaritia.common.content.gear.shared.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import io.github.masyumero.mekanismavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(value = AbilityHandler.class,remap = false)
public abstract class MixinAbilityHandler{

    @Redirect(method = "updateAbilities",at = @At(value = "INVOKE", target = "Lcommittee/nova/mods/avaritia/util/ToolUtils;isPlayerWearing(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;)Z",ordinal = 0))
    private static boolean hasHelmetInject(LivingEntity entity, EquipmentSlot slot, Predicate<Item> predicate) {
        boolean modifyBoolean = false;
        if (entity instanceof Player player) {
            ItemStack slotItemStack = player.getItemBySlot(slot);
            if (slotItemStack.getItem() instanceof ItemMekaSuitArmor) {
                IModule<ModuleCosmicUnit> cosmicUnit = MAModuleUtil.getModule(slotItemStack, MAModules.COSMIC_UNIT);
                if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                    modifyBoolean = true;
                }
            } else if (slotItemStack.getItem() instanceof InfinityArmorItem) {
                modifyBoolean = true;
            }
        }
        return modifyBoolean;
    }

    @Redirect(method = "updateAbilities",at = @At(value = "INVOKE", target = "Lcommittee/nova/mods/avaritia/util/ToolUtils;isPlayerWearing(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;)Z",ordinal = 1))
    private static boolean hasChestInject(LivingEntity entity, EquipmentSlot slot, Predicate<Item> predicate) {
        boolean modifyBoolean = false;
        if (entity instanceof Player player) {
            ItemStack slotItemStack = player.getItemBySlot(slot);
            if (slotItemStack.getItem() instanceof ItemMekaSuitArmor) {
                IModule<ModuleCosmicUnit> cosmicUnit = MAModuleUtil.getModule(slotItemStack, MAModules.COSMIC_UNIT);
                if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                    modifyBoolean = true;
                }
            } else if (slotItemStack.getItem() instanceof InfinityArmorItem) {
                modifyBoolean = true;
            }
        }
        return modifyBoolean;
    }

    @Redirect(method = "updateAbilities",at = @At(value = "INVOKE", target = "Lcommittee/nova/mods/avaritia/util/ToolUtils;isPlayerWearing(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;)Z",ordinal = 2))
    private static boolean hasLeggingsInject(LivingEntity entity, EquipmentSlot slot, Predicate<Item> predicate) {
        boolean modifyBoolean = false;
        if (entity instanceof Player player) {
            ItemStack slotItemStack = player.getItemBySlot(slot);
            if (slotItemStack.getItem() instanceof ItemMekaSuitArmor) {
                IModule<ModuleCosmicUnit> cosmicUnit = MAModuleUtil.getModule(slotItemStack, MAModules.COSMIC_UNIT);
                if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                    modifyBoolean = true;
                }
            } else if (slotItemStack.getItem() instanceof InfinityArmorItem) {
                modifyBoolean = true;
            }
        }
        return modifyBoolean;
    }

    @Redirect(method = "updateAbilities",at = @At(value = "INVOKE", target = "Lcommittee/nova/mods/avaritia/util/ToolUtils;isPlayerWearing(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/Predicate;)Z",ordinal = 3))
    private static boolean hasBootsInject(LivingEntity entity, EquipmentSlot slot, Predicate<Item> predicate) {
        boolean modifyBoolean = false;
        if (entity instanceof Player player) {
            ItemStack slotItemStack = player.getItemBySlot(slot);
            if (slotItemStack.getItem() instanceof ItemMekaSuitArmor) {
                IModule<ModuleCosmicUnit> cosmicUnit = MAModuleUtil.getModule(slotItemStack, MAModules.COSMIC_UNIT);
                if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                    modifyBoolean = true;
                }
            } else if (slotItemStack.getItem() instanceof InfinityArmorItem) {
                modifyBoolean = true;
            }
        }
        return modifyBoolean;
    }
}
