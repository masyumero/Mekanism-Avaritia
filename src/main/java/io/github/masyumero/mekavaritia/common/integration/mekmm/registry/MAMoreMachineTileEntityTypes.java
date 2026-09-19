package io.github.masyumero.mekavaritia.common.integration.mekmm.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.*;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAMoreMachineTileEntityTypes {

    private MAMoreMachineTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, MoreMachineFactoryType, TileEntityTypeRegistryObject<? extends TileEntityMAMoreMachineFactory<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            FACTORIES.put(tier, MoreMachineFactoryType.RECYCLING, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), (pos, state) -> new TileEntityMARecyclingFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.RECYCLING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.PLANTING, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), (pos, state) -> new TileEntityMAPlantingFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.PLANTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_STAMPING, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), (pos, state) -> new TileEntityMAStampingFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_STAMPING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_LATHING, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), (pos, state) -> new TileEntityMAItemStackToItemStackMoreMachineFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_LATHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.CNC_ROLLING_MILL, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), (pos, state) -> new TileEntityMAItemStackToItemStackMoreMachineFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.CNC_ROLLING_MILL), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, MoreMachineFactoryType.REPLICATING, TILE_ENTITY_TYPES.register(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), (pos, state) -> new TileEntityMAReplicatingFactory(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, MoreMachineFactoryType.REPLICATING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityMAMoreMachineFactory<?>> getMAMoreMachineFactoryTile(MAFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityMAMoreMachineFactory<?>>[] getMAMoreMachineFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
