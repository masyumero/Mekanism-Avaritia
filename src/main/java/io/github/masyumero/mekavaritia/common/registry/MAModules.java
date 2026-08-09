package io.github.masyumero.mekavaritia.common.registry;

import committee.nova.mods.avaritia.init.registry.ModRarities;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.content.gear.mekasuit.*;
import io.github.masyumero.mekavaritia.common.content.gear.mekatool.ModuleInfinityAttackAmplificationUnit;
import io.github.masyumero.mekavaritia.common.content.gear.mekatool.ModuleInfinityExcavationEscalationUnit;
import io.github.masyumero.mekavaritia.common.content.gear.mekaweapons.ModuleInfinityDamageUnit;
import io.github.masyumero.mekavaritia.common.content.gear.shared.ModuleCosmicUnit;
import io.github.masyumero.mekavaritia.common.content.gear.shared.ModuleInfinityEnergyUnit;
import io.github.masyumero.mekavaritia.common.content.gear.mekaweapons.ModuleCelestialShotUnit;
import io.github.masyumero.mekavaritia.common.content.gear.mekaweapons.ModuleCosmicStrikeUnit;
import mekanism.api.gear.ModuleData.ExclusiveFlag;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

@SuppressWarnings({"Convert2MethodRef"})
public class MAModules {

    private MAModules() {
    }

    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(MekanismAvaritia.MODID);

    public static final ModuleRegistryObject<ModuleInfinityEnergyUnit> INFINITY_ENERGY_UNIT = MODULES.register("infinity_energy_unit", ModuleInfinityEnergyUnit::new,
            () -> MAItems.MODULE_INFINITY_ENERGY.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleCosmicUnit> COSMIC_UNIT = MODULES.register("cosmic_unit", ModuleCosmicUnit::new,
            () -> MAItems.MODULE_COSMIC.get(), builder -> builder.maxStackSize(8).rarity(Rarity.RARE).exclusive(ExclusiveFlag.INTERACT_ANY));
    public static final ModuleRegistryObject<ModuleInfiniteElytraUnit> INFINITY_ELYTRA_UNIT = MODULES.register("infinity_elytra_unit", ModuleInfiniteElytraUnit::new,
            () -> MAItems.MODULE_INFINITY_ELYTRA.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE).exclusive(ExclusiveFlag.OVERRIDE_JUMP));
    public static final ModuleRegistryObject<ModuleInfinityExcavationEscalationUnit> INFINITY_EXCAVATION_ESCALATION_UNIT = MODULES.register("infinity_excavation_escalation_unit", ModuleInfinityExcavationEscalationUnit::new,
            () -> MAItems.MODULE_INFINITY_EXCAVATION_ESCALATION.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleCelestialUnit> CELESTIAL_UNIT = MODULES.register("celestial_unit", ModuleCelestialUnit::new,
            () -> MAItems.MODULE_CELESTIAL.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE).exclusive(ExclusiveFlag.OVERRIDE_JUMP));
    public static final ModuleRegistryObject<ModuleNebulightUnit> NEBULIGHT_UNIT = MODULES.register("nebulight_unit", ModuleNebulightUnit::new,
            () -> MAItems.MODULE_NEBULIGHT.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleStarfeastUnit> STARFEAST_UNIT = MODULES.register("starfeast_unit", ModuleStarfeastUnit::new,
            () -> MAItems.MODULE_STARFEAST.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleLightspeedUnit> LIGHTSPEED_UNIT = MODULES.register("lightspeed_unit", ModuleLightspeedUnit::new,
            () -> MAItems.MODULE_LIGHTSPEED.get(), builder -> builder.maxStackSize(1).rarity(Rarity.RARE));
    public static final ModuleRegistryObject<ModuleInfinityAttackAmplificationUnit> INFINITY_ATTACK_AMPLIFICATION_UNIT = MODULES.register("infinity_attack_amplification_unit", ModuleInfinityAttackAmplificationUnit::new,
            () -> MAItems.MODULE_INFINITY_ATTACK_AMPLIFICATION.get(), builder -> builder.maxStackSize(16).rarity(Rarity.RARE));

    //Weapons
    public static final ModuleRegistryObject<ModuleCosmicStrikeUnit> COSMIC_STRIKE_UNIT = MODULES.register("cosmic_strike_unit", ModuleCosmicStrikeUnit::new,
            () -> MAItems.MODULE_COSMIC_STRIKE.get(), builder -> builder.maxStackSize(8).rarity(ModRarities.COSMIC));
    public static final ModuleRegistryObject<ModuleCelestialShotUnit> CELESTIAL_SHOT_UNIT = MODULES.register("celestial_shot_unit", ModuleCelestialShotUnit::new,
            () -> MAItems.MODULE_CELESTIAL_SHOT.get(), builder -> builder.maxStackSize(1).rarity(ModRarities.COSMIC));
    public static final ModuleRegistryObject<ModuleInfinityDamageUnit> INFINITY_DAMAGE_UNIT = MODULES.register("infinity_damage_unit", ModuleInfinityDamageUnit::new,
            () -> MAItems.MODULE_INFINITY_DAMAGE.get(), builder -> builder.maxStackSize(1).rarity(ModRarities.COSMIC));

    public static void register(IEventBus eventBus) {
        MODULES.register(eventBus);
    }
}
