package io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.*;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAAdvancedFactoryTileEntityTypes {

    private MAAdvancedFactoryTileEntityTypes() {}

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(MekanismAvaritia.MODID);

    private static final Table<MAFactoryTier, AdvancedFactoryType, TileEntityTypeRegistryObject<? extends TileEntityMAAdvancedFactoryBase<?>>> FACTORIES = HashBasedTable.create();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            FACTORIES.put(tier, AdvancedFactoryType.OXIDIZING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), (pos, state) -> new TileEntityMAOxidizingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.OXIDIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.DISSOLVING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), (pos, state) -> new TileEntityMADissolvingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.DISSOLVING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.WASHING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.WASHING), (pos, state) -> new TileEntityMAWashingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.WASHING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CRYSTALLIZING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), (pos, state) -> new TileEntityMACrystallizingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.CRYSTALLIZING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PRESSURISED_REACTING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), (pos, state) -> new TileEntityMAPressurizedReactingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PRESSURISED_REACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.CENTRIFUGING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), (pos, state) -> new TileEntityMACentrifugingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.CENTRIFUGING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.LIQUIFYING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), (pos, state) -> new TileEntityMALiquifyingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.LIQUIFYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PIGMENT_EXTRACTING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), (pos, state) -> new TileEntityMAPigmentMActingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PIGMENT_EXTRACTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            FACTORIES.put(tier, AdvancedFactoryType.PAINTING, TILE_ENTITY_TYPES.register(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PAINTING), (pos, state) -> new TileEntityMAPaintingFactory(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, AdvancedFactoryType.PAINTING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
        }
    }

    public static TileEntityTypeRegistryObject<? extends TileEntityMAAdvancedFactoryBase<?>> getMAAdvancedFactoryTile(MAFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }

    @SuppressWarnings("unchecked")
    public static TileEntityTypeRegistryObject<? extends TileEntityMAAdvancedFactoryBase<?>>[] getMAAdvancedFactoryTiles() {
        return FACTORIES.values().toArray(new TileEntityTypeRegistryObject[0]);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
