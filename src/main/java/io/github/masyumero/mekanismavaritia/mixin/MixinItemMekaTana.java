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
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.ItemEnergized;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import mekanism.common.util.StorageUtils;
import meranha.mekaweapons.items.ItemMekaTana;
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

@Mixin(value = ItemMekaTana.class)
public abstract class MixinItemMekaTana extends ItemEnergized implements IModuleContainerItem, IGenericRadialModeItem {

    public MixinItemMekaTana(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Properties properties) {
        super(chargeRateSupplier, maxEnergySupplier, properties);
    }

    @Inject(method = "hurtEnemy",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z",shift = At.Shift.AFTER))
    private void InjecthurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleCosmicUnit> cosmicUnit = getModule(stack, MAModules.COSMIC_UNIT);
        IModule<ModuleInfintyEnergyUnit> infintyEnergyUnit = getModule(stack, MAModules.INFINITY_ENERGY_UNIT);
        IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
        FloatingLong energyRequired = FloatingLong.create(LoadConfig.GEAR_CONFIG.CosmicUnitUseageEnergy.get());
        if (cosmicUnit != null && cosmicUnit.isEnabled()) {
            if (infintyEnergyUnit != null && infintyEnergyUnit.isEnabled()) {
                target.hurt(target.damageSources().mobAttack(attacker), target.getMaxHealth() * (float) cosmicUnit.getInstalledCount());
                energyContainer.setEnergy(FloatingLong.MAX_VALUE);
            } else if (energyContainer.extract(energyRequired, Action.EXECUTE, AutomationType.MANUAL).greaterOrEqual(energyRequired)) {
                target.hurt(target.damageSources().mobAttack(attacker), target.getMaxHealth() * (float) cosmicUnit.getInstalledCount());
            }
        }
    }

    @Inject(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isClientSide()Z",shift = At.Shift.AFTER))
    private void InjectUse(Level world, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        var heldItem = player.getItemInHand(hand);
        IModule<ModuleCosmicUnit> cosmicUnit = getModule(heldItem, MAModules.COSMIC_UNIT);
        if (cosmicUnit != null && cosmicUnit.isEnabled()) {
            int cosmicUnitCount = cosmicUnit.getInstalledCount();
            ToolUtils.aoeAttack(player, cosmicUnitCount * 128, cosmicUnitCount * 10000, ModConfig.isSwordAttackAnimal.get(), ModConfig.isSwordAttackLightning.get());
            player.getCooldowns().addCooldown(heldItem.getItem(), 20 / cosmicUnitCount);
            world.playSound(player, player.getOnPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 5.0f);
        }
    }
}
