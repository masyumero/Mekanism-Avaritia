package io.github.masyumero.mekavaritia.mixin.mekanism;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.content.gear.mekatool.ModuleInfinityAttackAmplificationUnit;
import io.github.masyumero.mekavaritia.common.content.gear.mekatool.ModuleInfinityExcavationEscalationUnit;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.IModule;
import mekanism.api.math.FloatingLong;
import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.gear.IBlastingItem;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.content.gear.Module;
import mekanism.common.content.gear.mekatool.ModuleExcavationEscalationUnit;
import mekanism.common.item.ItemEnergized;
import mekanism.common.item.gear.ItemMekaTool;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import mekanism.common.registries.MekanismModules;
import mekanism.common.util.StorageUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaTool.class)
public abstract class MixinItemMekaTool extends ItemEnergized implements IModuleContainerItem, IBlastingItem, IGenericRadialModeItem {

    public MixinItemMekaTool(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Properties properties) {
        super(chargeRateSupplier, maxEnergySupplier, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        for (Module<?> module : getModules(stack)) {
            if (entity instanceof Player player) {
                module.tick(player);
            }
        }
    }

    @Inject(method = "getDestroySpeed",at = @At("RETURN"), cancellable = true)
    private void getDestroySpeedInject(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        IModule<ModuleExcavationEscalationUnit> excavationEscalationUnit = getModule(stack, MekanismModules.EXCAVATION_ESCALATION_UNIT);
        IModule<ModuleInfinityExcavationEscalationUnit> infinityExcavationEscalationUnit = getModule(stack, MAModules.INFINITY_EXCAVATION_ESCALATION_UNIT);
        if (excavationEscalationUnit != null && excavationEscalationUnit.isEnabled()) {
            cir.setReturnValue(excavationEscalationUnit.getCustomInstance().getEfficiency());
        } else if (infinityExcavationEscalationUnit != null && infinityExcavationEscalationUnit.isEnabled()) {
            cir.setReturnValue(Float.MAX_VALUE);
        } else {
            MekanismConfig.gear.mekaToolBaseEfficiency.get();
        }
    }

    @Inject(method = "hurtEnemy",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaTool;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",shift = At.Shift.AFTER), remap = false)
    private void hurtEnemyInject(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleInfinityAttackAmplificationUnit> atkAmpUnit = getModule(stack, MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT);
        IEnergyContainer energyContainer = StorageUtils.getEnergyContainer(stack, 0);
        if (atkAmpUnit != null && atkAmpUnit.isEnabled()) {
            FloatingLong energyRequired = FloatingLong.create(LoadConfig.GEAR_CONFIG.CosmicUnitUseageEnergy.get());
            if (energyContainer.extract(energyRequired, Action.EXECUTE, AutomationType.MANUAL).greaterOrEqual(energyRequired)) {
                target.hurt(target.damageSources().mobAttack(attacker), target.getMaxHealth() * (float) atkAmpUnit.getInstalledCount());
            }
        }
    }
}
