package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekatool.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekasuit.ModuleInfintyEnergyUnit;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.world.item.Rarity;

@SuppressWarnings({"Convert2MethodRef"})
public class MAModules {

    private MAModules() {
    }

    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(MekanismAvaritia.MODID);

    public static final ModuleRegistryObject<ModuleInfintyEnergyUnit> INFINITY_ENERGY_UNIT = MODULES.register("infinity_energy_unit", ModuleInfintyEnergyUnit::new,
            () -> MAItems.MODULE_INFINITY_ENERGY.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE).noDisable());
    public static final ModuleRegistryObject<ModuleCosmicUnit> COSMIC_UNIT = MODULES.register("cosmic_unit", ModuleCosmicUnit::new,
            () -> MAItems.MODULE_COSMIC.get(), builder -> builder.maxStackSize(64).rarity(Rarity.RARE));
}
