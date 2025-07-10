package io.github.masyumero.mekanismavaritia.common.registry;

import committee.nova.mods.avaritia.init.registry.ModRarities;
import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleInfinityDamageUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.shared.ModuleCosmicUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.shared.ModuleInfintyEnergyUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleCelestialShotUnit;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekaweapons.ModuleCosmicStrikeUnit;
import mekanism.api.gear.ModuleData.ExclusiveFlag;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.world.item.Rarity;

@SuppressWarnings({"Convert2MethodRef"})
public class MAModules {

    private MAModules() {
    }

    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(MekanismAvaritia.MODID);

    public static final ModuleRegistryObject<ModuleInfintyEnergyUnit> INFINITY_ENERGY_UNIT = MODULES.register("infinity_energy_unit", ModuleInfintyEnergyUnit::new,
            () -> MAItems.MODULE_INFINITY_ENERGY.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleCosmicUnit> COSMIC_UNIT = MODULES.register("cosmic_unit", ModuleCosmicUnit::new,
            () -> MAItems.MODULE_COSMIC.get(), builder -> builder.maxStackSize(8).rarity(Rarity.RARE).exclusive(ExclusiveFlag.INTERACT_ANY));
    //Weapons
    public static final ModuleRegistryObject<ModuleCosmicStrikeUnit> COSMIC_STRIKE_UNIT = MODULES.register("cosmic_strike_unit", ModuleCosmicStrikeUnit::new,
            () -> MAItems.MODULE_COSMIC_STRIKE.get(), builder -> builder.maxStackSize(8).rarity(ModRarities.COSMIC));
    public static final ModuleRegistryObject<ModuleCelestialShotUnit> CELESTIAL_SHOT_UNIT = MODULES.register("celestial_shot_unit", ModuleCelestialShotUnit::new,
            () -> MAItems.MODULE_CELESTIAL_SHOT.get(), builder -> builder.maxStackSize(1).rarity(ModRarities.COSMIC));
    public static final ModuleRegistryObject<ModuleInfinityDamageUnit> INFINITY_DAMAGE_UNIT = MODULES.register("infinity_damage_shot_unit", ModuleInfinityDamageUnit::new,
            () -> MAItems.MODULE_INFINITY_DAMAGE.get(), builder -> builder.maxStackSize(1).rarity(ModRarities.COSMIC));
}
