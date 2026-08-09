package io.github.masyumero.mekavaritia.common.registry;

import committee.nova.mods.avaritia.init.registry.ModRarities;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import io.github.masyumero.mekavaritia.common.item.ItemMAAlloy;
import io.github.masyumero.mekavaritia.common.resource.MAMiscResource;
import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAItems {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(MekanismAvaritia.MODID);

    //circuit
    public static final ItemRegistryObject<Item> PRISMATIC_CONTROL_CIRCUIT = registerCircuit("prismatic", ModRarities.RARE);
    public static final ItemRegistryObject<Item> FLARE_CONTROL_CIRCUIT = registerCircuit("flare", ModRarities.EPIC);
    public static final ItemRegistryObject<Item> NEURAL_CONTROL_CIRCUIT = registerCircuit("neural", ModRarities.COSMIC);
    public static final ItemRegistryObject<Item> ETERNAL_CONTROL_CIRCUIT = registerCircuit("eternal", ModRarities.LEGEND);
    //alloy
    public static final ItemRegistryObject<ItemMAAlloy> CRYSTALLINE_ALLOY = registerAlloy(MAAlloyTier.CRYSTALLINE, ModRarities.RARE);
    public static final ItemRegistryObject<ItemMAAlloy> BLAZING_ALLOY = registerAlloy(MAAlloyTier.BLAZING, ModRarities.EPIC);
    public static final ItemRegistryObject<ItemMAAlloy> NEUTRON_ALLOY = registerAlloy(MAAlloyTier.NEUTRON, ModRarities.COSMIC);
    public static final ItemRegistryObject<ItemMAAlloy> INFINITY_ALLOY = registerAlloy(MAAlloyTier.INFINITY, ModRarities.LEGEND);
    //Modules
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_ENERGY = ITEM.registerModule(MAModules.INFINITY_ENERGY_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_ELYTRA = ITEM.registerModule(MAModules.INFINITY_ELYTRA_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_EXCAVATION_ESCALATION = ITEM.registerModule(MAModules.INFINITY_EXCAVATION_ESCALATION_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_CELESTIAL = ITEM.registerModule(MAModules.CELESTIAL_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_NEBULIGHT = ITEM.registerModule(MAModules.NEBULIGHT_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_STARFEAST = ITEM.registerModule(MAModules.STARFEAST_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_LIGHTSPEED = ITEM.registerModule(MAModules.LIGHTSPEED_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_ATTACK_AMPLIFICATION= ITEM.registerModule(MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_COSMIC = ITEM.registerModule(MAModules.COSMIC_UNIT);
    //Modules: Weapons
    public static final ItemRegistryObject<ItemModule> MODULE_COSMIC_STRIKE = ITEM.registerModule(MAModules.COSMIC_STRIKE_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_CELESTIAL_SHOT = ITEM.registerModule(MAModules.CELESTIAL_SHOT_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_DAMAGE = ITEM.registerModule(MAModules.INFINITY_DAMAGE_UNIT);
    //Dusts
    public static final ItemRegistryObject<Item> CRYSTALLINE_DUST = registerResource(ResourceType.DUST, MAMiscResource.CRYSTALLINE, ModRarities.RARE);
    public static final ItemRegistryObject<Item> BLAZING_DUST = registerResource(ResourceType.DUST, MAMiscResource.BLAZING, ModRarities.EPIC);
    public static final ItemRegistryObject<Item> NEUTRON_DUST = registerResource(ResourceType.DUST, MAMiscResource.NEUTRON, ModRarities.COSMIC);
    public static final ItemRegistryObject<Item> INFINITY_DUST = registerResource(ResourceType.DUST, MAMiscResource.INFINITY, ModRarities.LEGEND);
    //Enriched
    public static final ItemRegistryObject<Item> ENRICHED_CRYSTALLINE = registerResource(ResourceType.ENRICHED, MAMiscResource.CRYSTALLINE, ModRarities.RARE);
    public static final ItemRegistryObject<Item> ENRICHED_BLAZING = registerResource(ResourceType.ENRICHED, MAMiscResource.BLAZING, ModRarities.EPIC);
    public static final ItemRegistryObject<Item> ENRICHED_NEUTRON = registerResource(ResourceType.ENRICHED, MAMiscResource.NEUTRON, ModRarities.COSMIC);
    public static final ItemRegistryObject<Item> ENRICHED_INFINITY = registerResource(ResourceType.ENRICHED, MAMiscResource.INFINITY, ModRarities.LEGEND);

    private static ItemRegistryObject<Item> registerResource(ResourceType type, IResource resource, Rarity rarity) {
        return ITEM.register(type.getRegistryPrefix() + "_" + resource.getRegistrySuffix(), properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<Item> registerCircuit(String name, Rarity rarity) {
        return ITEM.register(name + "_control_circuit", properties -> new Item(properties.rarity(rarity)));
    }

    private static ItemRegistryObject<ItemMAAlloy> registerAlloy(MAAlloyTier tier, Rarity rarity) {
        return ITEM.register("alloy_" + tier.getName(), properties -> new ItemMAAlloy(tier, properties.rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
