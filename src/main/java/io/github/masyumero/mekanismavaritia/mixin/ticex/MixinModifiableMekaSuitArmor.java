package io.github.masyumero.mekanismavaritia.mixin.ticex;

import io.github.masyumero.mekanismavaritia.common.content.gear.mekasuit.ModuleInfiniteElytraUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import mekanism.api.gear.IModule;
import mekanism.common.content.gear.IModuleContainerItem;
import mekanism.common.item.interfaces.IJetpackItem;
import mekanism.common.item.interfaces.IModeItem;
import mekanism.common.lib.attribute.IAttributeRefresher;
import moffy.ticex.item.modifiable.IModifiableMekItem;
import moffy.ticex.item.modifiable.ModifiableMekaSuitArmor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.item.armor.MultilayerArmorItem;

@Mixin(value = ModifiableMekaSuitArmor.class ,remap = false)
public abstract class MixinModifiableMekaSuitArmor extends MultilayerArmorItem implements IModifiableMekItem, IModuleContainerItem, IModeItem, IJetpackItem, IAttributeRefresher {

    public MixinModifiableMekaSuitArmor(ModifiableArmorMaterial material, Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Inject(method = "canElytraFly",at = @At(value = "INVOKE", target = "Lmoffy/ticex/item/modifiable/ModifiableMekaSuitArmor;getType()Lnet/minecraft/world/item/ArmorItem$Type;",ordinal = 0,shift = At.Shift.AFTER), cancellable = true)
    private void canElytraFlyInject(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        IModule<ModuleInfiniteElytraUnit> infiniteElytraUnit = getModule(stack, MAModules.INFINITY_ELYTRA_UNIT);
        if(infiniteElytraUnit != null && infiniteElytraUnit.isEnabled()) {
            cir.setReturnValue(true);
        }
    }
}
