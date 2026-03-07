package io.github.masyumero.mekanismavaritia.mixin.mekanism;

import committee.nova.mods.avaritia.api.iface.ISwitchable;
import committee.nova.mods.avaritia.init.config.ModConfig;
import committee.nova.mods.avaritia.util.ToolUtils;
import io.github.masyumero.mekanismavaritia.common.config.LoadConfig;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekatool.ModuleInfinityAttackAmplificationUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleCosmicStrikeUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.IModule;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.content.gear.Module;
import mekanism.common.item.ItemEnergized;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import mekanism.common.util.StorageUtils;
import meranha.mekaweapons.items.ItemMekaTana;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaTana.class)
public abstract class MixinItemMekaTana extends ItemEnergized implements IModuleContainerItem, IGenericRadialModeItem, ISwitchable {

    public MixinItemMekaTana(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Properties properties) {
        super(chargeRateSupplier, maxEnergySupplier, properties);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        for (Module<?> module : getModules(p_41404_)) {
            if (p_41406_ instanceof Player) {
                module.tick((Player) p_41406_);
            }
        }
    }

    @Inject(method = "hurtEnemy",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isCreative()Z",shift = At.Shift.AFTER))
    private void hurtEnemyInject(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleInfinityAttackAmplificationUnit> atkAmpUnit = getModule(stack, MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT);
        IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
        FloatingLong energyRequired = FloatingLong.create(LoadConfig.GEAR_CONFIG.CosmicUnitUseageEnergy.get());
        if (atkAmpUnit != null && atkAmpUnit.isEnabled()) {
            if (energyContainer.extract(energyRequired, Action.EXECUTE, AutomationType.MANUAL).greaterOrEqual(energyRequired)) {
                target.hurt(target.damageSources().mobAttack(attacker), target.getMaxHealth() * (float) atkAmpUnit.getInstalledCount());
            }
        }
    }

    @Inject(method = "use",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isClientSide()Z",shift = At.Shift.AFTER))
    private void useInject(Level world, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        var heldItem = player.getItemInHand(hand);
        IModule<ModuleCosmicStrikeUnit> cosmicStrikeUnit = getModule(heldItem, MAModules.COSMIC_STRIKE_UNIT);
        if (cosmicStrikeUnit != null && cosmicStrikeUnit.isEnabled()) {
            if (player.isShiftKeyDown()) {
                switchMode(world, player, hand, "infinity_sword_kill");
                cir.setReturnValue(InteractionResultHolder.success(heldItem));
            }
            int cosmicUnitCount = cosmicStrikeUnit.getInstalledCount();
            if (isActive(heldItem, "infinity_sword_kill")) {
                ToolUtils.aoeAttack(player, cosmicUnitCount * 128, cosmicUnitCount * 10000, true, ModConfig.isSwordAttackLightning.get());
            } else {
                ToolUtils.aoeAttack(player, cosmicUnitCount * 128, cosmicUnitCount * 10000, false, ModConfig.isSwordAttackLightning.get());
            }
            player.getCooldowns().addCooldown(heldItem.getItem(), 20 / cosmicUnitCount);
            world.playSound(player, player.getOnPos(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0f, 5.0f);
        }
    }
}
