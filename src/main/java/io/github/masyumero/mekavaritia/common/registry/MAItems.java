package io.github.masyumero.mekavaritia.common.registry;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.item.ItemAlloyCrystalline;
import io.github.masyumero.mekavaritia.common.item.ItemMAAlloy;
import io.github.masyumero.mekavaritia.common.item.MATieredItem;
import io.github.masyumero.mekavaritia.common.resource.MAMiscResource;
import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.resource.IResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.function.Function;

public class MAItems {
    public static final ItemDeferredRegister ITEM = new ItemDeferredRegister(MekanismAvaritia.MODID);

    //circuit
    public static final ItemRegistryObject<MATieredItem> PRISMATIC_CONTROL_CIRCUIT = registerCircuit(MATier.PRISMATIC);
    public static final ItemRegistryObject<MATieredItem> FLARE_CONTROL_CIRCUIT = registerCircuit(MATier.FLARE);
    public static final ItemRegistryObject<MATieredItem> NEURAL_CONTROL_CIRCUIT = registerCircuit(MATier.NEURAL);
    public static final ItemRegistryObject<MATieredItem> ETERNAL_CONTROL_CIRCUIT = registerCircuit(MATier.ETERNAL);
    //alloy
    public static final ItemRegistryObject<ItemAlloyCrystalline> CRYSTALLINE_ALLOY = registerAlloy(MAAlloyTier.CRYSTALLINE, tier -> new ItemAlloyCrystalline());
    public static final ItemRegistryObject<ItemMAAlloy> BLAZING_ALLOY = registerAlloy(MAAlloyTier.BLAZING, ItemMAAlloy::new);
    public static final ItemRegistryObject<ItemMAAlloy> NEUTRON_ALLOY = registerAlloy(MAAlloyTier.NEUTRON, ItemMAAlloy::new);
    public static final ItemRegistryObject<ItemMAAlloy> INFINITY_ALLOY = registerAlloy(MAAlloyTier.INFINITY, ItemMAAlloy::new);
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
    public static final ItemRegistryObject<MATieredItem> CRYSTALLINE_DUST = registerResource(ResourceType.DUST, MAMiscResource.CRYSTALLINE, MATier.PRISMATIC);
    public static final ItemRegistryObject<MATieredItem> BLAZING_DUST = registerResource(ResourceType.DUST, MAMiscResource.BLAZING, MATier.FLARE);
    public static final ItemRegistryObject<MATieredItem> NEUTRON_DUST = registerResource(ResourceType.DUST, MAMiscResource.NEUTRON, MATier.NEURAL);
    public static final ItemRegistryObject<MATieredItem> INFINITY_DUST = registerResource(ResourceType.DUST, MAMiscResource.INFINITY, MATier.ETERNAL);
    //Enriched
    public static final ItemRegistryObject<MATieredItem> ENRICHED_CRYSTALLINE = registerResource(ResourceType.ENRICHED, MAMiscResource.CRYSTALLINE, MATier.PRISMATIC);
    public static final ItemRegistryObject<MATieredItem> ENRICHED_BLAZING = registerResource(ResourceType.ENRICHED, MAMiscResource.BLAZING, MATier.FLARE);
    public static final ItemRegistryObject<MATieredItem> ENRICHED_NEUTRON = registerResource(ResourceType.ENRICHED, MAMiscResource.NEUTRON, MATier.NEURAL);
    public static final ItemRegistryObject<MATieredItem> ENRICHED_INFINITY = registerResource(ResourceType.ENRICHED, MAMiscResource.INFINITY, MATier.ETERNAL);

    private static ItemRegistryObject<MATieredItem> registerResource(ResourceType type, IResource resource, MATier tier) {
        return ITEM.register(type.getRegistryPrefix() + "_" + resource.getRegistrySuffix(), properties -> new MATieredItem(tier, properties));
    }

    private static ItemRegistryObject<MATieredItem> registerCircuit(MATier tier) {
        return ITEM.register(tier.getLowerName() + "_control_circuit", properties -> new MATieredItem(tier, properties));
    }

    private static <I extends Item> ItemRegistryObject<I> registerAlloy(MAAlloyTier tier, Function<MAAlloyTier, I> alloyItem) {
        return ITEM.register("alloy_" + tier.getName(), () -> alloyItem.apply(tier));
    }

    public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
    }
}
