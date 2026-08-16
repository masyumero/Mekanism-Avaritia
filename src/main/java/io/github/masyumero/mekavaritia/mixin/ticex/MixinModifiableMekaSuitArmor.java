package io.github.masyumero.mekavaritia.mixin.ticex;

import io.github.masyumero.mekavaritia.common.content.gear.mekasuit.ModuleInfiniteElytraUnit;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import mekanism.common.lib.attribute.IAttributeRefresher;
import moffy.ticex.item.modifiable.ModifiableMekaSuitArmor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.item.armor.MultilayerArmorItem;

@Mixin(value = ModifiableMekaSuitArmor.class ,remap = false)
public abstract class MixinModifiableMekaSuitArmor extends MultilayerArmorItem implements IAttributeRefresher {

    public MixinModifiableMekaSuitArmor(ModifiableArmorMaterial material, Type slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        IModule<ModuleInfiniteElytraUnit> infiniteElytraUnit = MAModuleUtil.getModule(stack, MAModules.INFINITY_ELYTRA_UNIT);
        return super.canElytraFly(stack, entity) || infiniteElytraUnit != null && infiniteElytraUnit.isEnabled();
    }
}
