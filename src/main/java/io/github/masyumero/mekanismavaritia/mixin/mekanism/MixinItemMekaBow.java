package io.github.masyumero.mekanismavaritia.mixin.mekanism;

import committee.nova.mods.avaritia.common.entity.arrow.HeavenArrowEntity;
import io.github.masyumero.mekanismavaritia.common.content.gear.shared.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.lib.radial.IGenericRadialModeItem;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaBow.class,remap = false)
public abstract class MixinItemMekaBow extends BowItem implements IModuleContainerItem, IGenericRadialModeItem {

    public MixinItemMekaBow(Properties p_40660_) {
        super(p_40660_);
    }

    @Inject(method = "customArrow",at = @At("RETURN"),cancellable = true)
    private void customArrowInject(AbstractArrow arrow, CallbackInfoReturnable<AbstractArrow> cir) {
        if (arrow.getOwner() instanceof Player player) {
            IModule<ModuleCosmicUnit> cosmicUnit = getModule(player.getMainHandItem(), MAModules.COSMIC_UNIT);
            if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                cir.setReturnValue(new HeavenArrowEntity(arrow.getOwner()));
            } else {
                cir.setReturnValue(new MekaArrowEntity(arrow.level(), arrow.getX(), arrow.getY(), arrow.getZ(), new ItemStack(Items.ARROW), player.getMainHandItem()));
            }
        }
    }
}
