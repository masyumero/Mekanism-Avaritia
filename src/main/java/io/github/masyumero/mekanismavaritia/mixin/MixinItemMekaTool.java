package io.github.masyumero.mekanismavaritia.mixin;

import committee.nova.mods.avaritia.init.config.ModConfig;
import committee.nova.mods.avaritia.util.ToolUtils;
import io.github.masyumero.mekanismavaritia.common.config.LoadConfig;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekasuit.ModuleInfintyEnergyUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekatool.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.IModule;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.content.gear.IBlastingItem;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.ItemEnergized;
import mekanism.common.item.gear.ItemMekaTool;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import mekanism.common.util.StorageUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaTool.class, remap = false)
public abstract class MixinItemMekaTool extends ItemEnergized implements IModuleContainerItem, IBlastingItem, IGenericRadialModeItem {

    public MixinItemMekaTool(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Properties properties) {
        super(chargeRateSupplier, maxEnergySupplier, properties);
    }

    @Inject(method = "hurtEnemy",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaTool;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",shift = At.Shift.AFTER))
//    @Inject(method = "m_7579_",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaTool;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",shift = At.Shift.AFTER))
    private void InjecthurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleCosmicUnit> cosmicUnit = getModule(stack, MAModules.COSMIC_UNIT);
        IModule<ModuleInfintyEnergyUnit> infintyEnergyUnit = getModule(stack, MAModules.INFINITY_ENERGY_UNIT);
        IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
        if (cosmicUnit != null && cosmicUnit.isEnabled()) {
            FloatingLong energyRequired = FloatingLong.create(LoadConfig.GEAR_CONFIG.CosmicUnitUseageEnergy.get());
            if (infintyEnergyUnit != null && infintyEnergyUnit.isEnabled()) {
                target.setHealth(0);
                energyContainer.setEnergy(FloatingLong.MAX_VALUE);
            } else if (energyContainer.extract(energyRequired, Action.EXECUTE, AutomationType.MANUAL).greaterOrEqual(energyRequired)) {
                target.setHealth(0);
            }
        }
    }

    @Inject(method = "use",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaTool;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",shift = At.Shift.AFTER))
//    @Inject(method = "m_7203_",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaTool;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",shift = At.Shift.AFTER))
    private void InjectUse(Level world, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        var heldItem = player.getItemInHand(hand);
        IModule<ModuleCosmicUnit> cosmicUnit = getModule(heldItem, MAModules.COSMIC_UNIT);
        if (cosmicUnit != null && cosmicUnit.isEnabled()) {
            ToolUtils.aoeAttack(player, ModConfig.swordAttackRange.get(), ModConfig.swordRangeDamage.get(), ModConfig.isSwordAttackAnimal.get(), ModConfig.isSwordAttackLightning.get());
            player.getCooldowns().addCooldown(heldItem.getItem(), 20 / cosmicUnit.getInstalledCount());
            world.playSound(player, player.getOnPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 5.0f);
        }
    }
}
