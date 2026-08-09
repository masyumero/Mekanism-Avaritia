package io.github.masyumero.mekavaritia.mixin.mekanism;

import io.github.masyumero.mekavaritia.common.content.gear.mekasuit.ModuleInfiniteElytraUnit;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.item.gear.ItemSpecialArmor;
import mekanism.common.item.interfaces.IJetpackItem;
import mekanism.common.item.interfaces.IModeItem;
import mekanism.common.lib.attribute.IAttributeRefresher;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemMekaSuitArmor.class,remap = false)
public abstract class MixinItemMekaSuitArmor extends ItemSpecialArmor implements IModuleContainerItem, IModeItem, IJetpackItem, IAttributeRefresher, CreativeTabDeferredRegister.ICustomCreativeTabContents {

    protected MixinItemMekaSuitArmor(ArmorMaterial material, Type armorType, Properties properties) {
        super(material, armorType, properties);
    }

    @Inject(method = "canElytraFly",at = @At(value = "INVOKE", target = "Lmekanism/common/item/gear/ItemMekaSuitArmor;getModule(Lnet/minecraft/world/item/ItemStack;Lmekanism/api/providers/IModuleDataProvider;)Lmekanism/api/gear/IModule;",ordinal = 0,shift = At.Shift.AFTER), cancellable = true)
    private void canElytraFlyInject(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleInfiniteElytraUnit> infiniteElytraUnit = getModule(stack, MAModules.INFINITY_ELYTRA_UNIT);
        if(infiniteElytraUnit != null && infiniteElytraUnit.isEnabled()) {
            cir.setReturnValue(true);
        }
    }
}
