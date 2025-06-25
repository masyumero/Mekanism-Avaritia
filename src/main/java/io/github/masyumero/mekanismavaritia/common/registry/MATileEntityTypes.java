package io.github.masyumero.mekanismavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactoryType;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekanismavaritia.common.tile.factory.*;
import io.github.masyumero.mekanismavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekanismavaritia.common.util.MAEnumUtils;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class MATileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, MAFactoryType, TileEntityTypeRegistryObject<? extends TileEntityMAFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            FACTORIES.put(tier, MAFactoryType.SMELTING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.SMELTING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.SMELTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.CRUSHING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.CRUSHING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.CRUSHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.ENRICHING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.ENRICHING), (pos, state) -> new TileEntityItemStackToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.ENRICHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.COMPRESSING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.COMPRESSING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.COMPRESSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.INJECTING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.INJECTING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.INJECTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.PURIFYING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.PURIFYING), (pos, state) -> new TileEntityItemStackGasToItemStackMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.PURIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.INFUSING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.INFUSING), (pos, state) -> new TileEntityMetallurgicInfuserMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.INFUSING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.COMBINING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.COMBINING), (pos, state) -> new TileEntityCombiningMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.COMBINING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MAFactoryType.SAWING, TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.SAWING), (pos, state) -> new TileEntitySawingMAFactory(MABlocks.getMAFactory(tier, MAFactoryType.SAWING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            if(ModList.get().isLoaded("evolvedmekanism")) {
                FACTORIES.put(tier, MAFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, MAFactoryType.ALLOYING), (pos, state) -> new TileEntityMAAlloyingFactory(MABlocks.getMAFactory(tier, MAFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            }
        }
    }

    public static final TileEntityTypeRegistryObject<TileEntityElectricNeutronCollector> ELECTRIC_NEUTRON_COLLECTOR = TILE_ENTITY_TYPES.register(MABlocks.ELECTRIC_NEUTRON_COLLECTOR, TileEntityElectricNeutronCollector::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);

    public static TileEntityTypeRegistryObject<? extends TileEntityMAFactory<?>> getMAFactoryTile(MAFactoryTier tier, MAFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
