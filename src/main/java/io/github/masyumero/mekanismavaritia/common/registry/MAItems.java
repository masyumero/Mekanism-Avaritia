package io.github.masyumero.mekanismavaritia.common.registry;

import committee.nova.mods.avaritia.init.registry.ModRarities;
import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.api.tier.MAAlloyTier;
import io.github.masyumero.mekanismavaritia.common.item.ItemMAAlloy;
import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
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
    public static final ItemRegistryObject<ItemMAAlloy> INFINITE_ALLOY = registerAlloy(MAAlloyTier.INFINITE, ModRarities.LEGEND);
    //Modules
    public static final ItemRegistryObject<ItemModule> MODULE_INFINITY_ENERGY = ITEM.registerModule(MAModules.INFINITY_ENERGY_UNIT);
    public static final ItemRegistryObject<ItemModule> MODULE_COSMIC = ITEM.registerModule(MAModules.COSMIC_UNIT);

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
