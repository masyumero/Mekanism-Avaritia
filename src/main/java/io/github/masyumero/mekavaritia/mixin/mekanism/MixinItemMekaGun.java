package io.github.masyumero.mekavaritia.mixin.mekanism;

import mekanism.api.math.FloatingLongSupplier;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.content.gear.Module;
import mekanism.common.item.ItemEnergized;
import mekanism.common.lib.radial.IGenericRadialModeItem;
import meranha.mekaweapons.items.ItemMekaGun;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ItemMekaGun.class, remap = false)
public abstract class MixinItemMekaGun extends ItemEnergized implements IModuleContainerItem, IGenericRadialModeItem {

    public MixinItemMekaGun(FloatingLongSupplier chargeRateSupplier, FloatingLongSupplier maxEnergySupplier, Properties properties) {
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
}
