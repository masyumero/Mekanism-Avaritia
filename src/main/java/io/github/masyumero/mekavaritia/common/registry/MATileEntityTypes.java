package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tile.factory.*;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class MATileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityMAFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            FACTORIES.put(tier, FactoryType.SMELTING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.SMELTING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.CRUSHING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.CRUSHING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.ENRICHING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.ENRICHING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.COMPRESSING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INJECTING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.INJECTING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.PURIFYING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.PURIFYING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, FactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.INFUSING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.INFUSING), (pos, state) -> new TileEntityMetallurgicInfuserMAFactory(MABlocks.getMAFactory(tier, FactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.COMBINING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.COMBINING), (pos, state) -> new TileEntityCombiningMAFactory(MABlocks.getMAFactory(tier, FactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, FactoryType.SAWING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, FactoryType.SAWING), (pos, state) -> new TileEntitySawingMAFactory(MABlocks.getMAFactory(tier, FactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            if(ModList.get().isLoaded("evolvedmekanism")) {
                FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, EMFactoryType.ALLOYING), (pos, state) -> new TileEntityMAAlloyingFactory(MABlocks.getMAFactory(tier, EMFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            }
        }
    }

    public static final TileEntityTypeRegistryObject<TileEntityElectricNeutronCollector> ELECTRIC_NEUTRON_COLLECTOR = TILE_ENTITY_TYPES.register(MABlocks.ELECTRIC_NEUTRON_COLLECTOR, TileEntityElectricNeutronCollector::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static TileEntityTypeRegistryObject<? extends TileEntityMAFactory<?>> getMAFactoryTile(MAFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
