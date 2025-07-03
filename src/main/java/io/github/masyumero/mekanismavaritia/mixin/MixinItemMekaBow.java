package io.github.masyumero.mekanismavaritia.mixin;

import committee.nova.mods.avaritia.common.entity.arrow.HeavenArrowEntity;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekatool.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import meranha.mekaweapons.items.ItemMekaBow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ItemMekaBow.class,remap = false)
public abstract class MixinItemMekaBow extends BowItem implements IModuleContainerItem, IGenericRadialModeItem {

    public MixinItemMekaBow(Properties p_40660_) {
        super(p_40660_);
    }

    @Redirect(method = "releaseUsing",at = @At(value = "INVOKE", target = "Lmeranha/mekaweapons/items/ItemMekaBow;customArrow(Lnet/minecraft/world/entity/projectile/AbstractArrow;)Lnet/minecraft/world/entity/projectile/AbstractArrow;"))
    private AbstractArrow releaseUsingRedirect(ItemMekaBow instance, AbstractArrow abstractArrow){
        AbstractArrow arrow = abstractArrow;
        if (abstractArrow.getOwner() instanceof Player player) {
            IModule<ModuleCosmicUnit> cosmicUnit = getModule(player.getMainHandItem(), MAModules.COSMIC_UNIT);
            if (cosmicUnit != null && cosmicUnit.isEnabled()) {
                arrow = new HeavenArrowEntity(abstractArrow.getOwner());
            } else {
                ItemStack potentialAmmo = player.getProjectile(player.getMainHandItem());
                ArrowItem arrowitem = (ArrowItem) (potentialAmmo.getItem() instanceof ArrowItem ? potentialAmmo.getItem() : Items.ARROW);
                arrow = customArrow(arrowitem.createArrow(abstractArrow.level(), potentialAmmo, player));
            }
        }
        return arrow;
    }
}
