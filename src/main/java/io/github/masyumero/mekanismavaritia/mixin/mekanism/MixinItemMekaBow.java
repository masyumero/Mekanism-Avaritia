package io.github.masyumero.mekanismavaritia.mixin.mekanism;

import committee.nova.mods.avaritia.common.entity.arrow.HeavenArrowEntity;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleCelestialShotUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleInfinityDamageUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import meranha.mekaweapons.MekaWeaponsUtils;
import meranha.mekaweapons.items.ItemMekaBow;
import meranha.mekaweapons.items.MekaArrowEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaBow.class,remap = false)
public abstract class MixinItemMekaBow extends BowItem implements IModuleContainerItem, IGenericRadialModeItem {

    public MixinItemMekaBow(Properties p_40660_) {
        super(p_40660_);
    }

    @Inject(method = "customArrow",at = @At("RETURN"),cancellable = true)
    private void customArrowInject(AbstractArrow arrow, CallbackInfoReturnable<AbstractArrow> cir) {
        if (arrow.getOwner() instanceof Player player) {
            IModule<ModuleCelestialShotUnit> cosmicUnit = getModule(player.getMainHandItem(), MAModules.CELESTIAL_SHOT_UNIT);
            if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                cir.setReturnValue(new HeavenArrowEntity(arrow.getOwner()));
            } else {
                cir.setReturnValue(new MekaArrowEntity(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ(), new ItemStack(Items.ARROW), player.getMainHandItem()));
            }
        }
    }

    @Redirect(method = "getAttributeModifiers",at = @At(value = "INVOKE", target = "Lmeranha/mekaweapons/MekaWeaponsUtils;getTotalDamage(Lnet/minecraft/world/item/ItemStack;)J"))
    private long getAttributeModifiersRedirect(ItemStack weapon) {
        IModule<ModuleInfinityDamageUnit> infinityDamageUnit = getModule(weapon, MAModules.INFINITY_DAMAGE_UNIT);
        if (infinityDamageUnit != null && infinityDamageUnit.isEnabled()) {
            return Long.MAX_VALUE;
        }
        return MekaWeaponsUtils.getTotalDamage(weapon);
    }
}
