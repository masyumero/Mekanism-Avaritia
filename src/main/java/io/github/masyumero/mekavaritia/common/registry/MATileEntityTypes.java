package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tile.factory.*;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import io.github.masyumero.mekavaritia.common.tile.transmitter.*;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;

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
            if(MAAddons.EVOLVEDMEKANISM.isLoaded()) {
                FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.register(MABlocks.getMAFactory(tier, EMFactoryType.ALLOYING), (pos, state) -> new TileEntityMAAlloyingFactory(MABlocks.getMAFactory(tier, EMFactoryType.ALLOYING), pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient));
            }
        }
    }
    
    private static <BE extends TileEntityTransmitter> TileEntityTypeRegistryObject<BE> registerTransmitter(BlockRegistryObject<?, ?> block, BlockEntityType.BlockEntitySupplier<? extends BE> factory) {
        // Note: There is no data fixer type as forge does not currently have a way exposing data fixers to mods yet
        return TILE_ENTITY_TYPES.<BE>builder(block, factory).serverTicker(TileEntityTransmitter::tickServer).build();
    }

    public static final TileEntityTypeRegistryObject<TileEntityElectricNeutronCollector> ELECTRIC_NEUTRON_COLLECTOR = TILE_ENTITY_TYPES.register(MABlocks.ELECTRIC_NEUTRON_COLLECTOR, TileEntityElectricNeutronCollector::new, TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Cells
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionCell> PRISMATIC_INDUCTION_CELL = TILE_ENTITY_TYPES.register(MABlocks.PRISMATIC_INDUCTION_CELL, (pos, state) -> new TileEntityMAInductionCell(MABlocks.PRISMATIC_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionCell> FLARE_INDUCTION_CELL = TILE_ENTITY_TYPES.register(MABlocks.FLARE_INDUCTION_CELL, (pos, state) -> new TileEntityMAInductionCell(MABlocks.FLARE_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionCell> NEURAL_INDUCTION_CELL = TILE_ENTITY_TYPES.register(MABlocks.NEURAL_INDUCTION_CELL, (pos, state) -> new TileEntityMAInductionCell(MABlocks.NEURAL_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionCell> ETERNAL_INDUCTION_CELL = TILE_ENTITY_TYPES.register(MABlocks.ETERNAL_INDUCTION_CELL, (pos, state) -> new TileEntityMAInductionCell(MABlocks.ETERNAL_INDUCTION_CELL, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // Induction Providers
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionProvider> PRISMATIC_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(MABlocks.PRISMATIC_INDUCTION_PROVIDER, (pos, state) -> new TileEntityMAInductionProvider(MABlocks.PRISMATIC_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionProvider> FLARE_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(MABlocks.FLARE_INDUCTION_PROVIDER, (pos, state) -> new TileEntityMAInductionProvider(MABlocks.FLARE_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionProvider> NEURAL_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(MABlocks.NEURAL_INDUCTION_PROVIDER, (pos, state) -> new TileEntityMAInductionProvider(MABlocks.NEURAL_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    public static final TileEntityTypeRegistryObject<TileEntityMAInductionProvider> ETERNAL_INDUCTION_PROVIDER = TILE_ENTITY_TYPES.register(MABlocks.ETERNAL_INDUCTION_PROVIDER, (pos, state) -> new TileEntityMAInductionProvider(MABlocks.ETERNAL_INDUCTION_PROVIDER, pos, state), TileEntityMekanism::tickServer, TileEntityMekanism::tickClient);
    // universal cables
    public static final TileEntityTypeRegistryObject<TileEntityMAUniversalCable> PRISMATIC_UNIVERSAL_CABLE = registerTransmitter(MABlocks.PRISMATIC_UNIVERSAL_CABLE, (pos, state) -> new TileEntityMAUniversalCable(MABlocks.PRISMATIC_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAUniversalCable> FLARE_UNIVERSAL_CABLE = registerTransmitter(MABlocks.FLARE_UNIVERSAL_CABLE, (pos, state) -> new TileEntityMAUniversalCable(MABlocks.FLARE_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAUniversalCable> NEURAL_UNIVERSAL_CABLE = registerTransmitter(MABlocks.NEURAL_UNIVERSAL_CABLE, (pos, state) -> new TileEntityMAUniversalCable(MABlocks.NEURAL_UNIVERSAL_CABLE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAUniversalCable> ETERNAL_UNIVERSAL_CABLE = registerTransmitter(MABlocks.ETERNAL_UNIVERSAL_CABLE, (pos, state) -> new TileEntityMAUniversalCable(MABlocks.ETERNAL_UNIVERSAL_CABLE, pos, state));
    // mechanical pipes
    public static final TileEntityTypeRegistryObject<TileEntityMAMechanicalPipe> PRISMATIC_MECHANICAL_PIPE = registerTransmitter(MABlocks.PRISMATIC_MECHANICAL_PIPE, (pos, state) -> new TileEntityMAMechanicalPipe(MABlocks.PRISMATIC_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAMechanicalPipe> FLARE_MECHANICAL_PIPE = registerTransmitter(MABlocks.FLARE_MECHANICAL_PIPE, (pos, state) -> new TileEntityMAMechanicalPipe(MABlocks.FLARE_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAMechanicalPipe> NEURAL_MECHANICAL_PIPE = registerTransmitter(MABlocks.NEURAL_MECHANICAL_PIPE, (pos, state) -> new TileEntityMAMechanicalPipe(MABlocks.NEURAL_MECHANICAL_PIPE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAMechanicalPipe> ETERNAL_MECHANICAL_PIPE = registerTransmitter(MABlocks.ETERNAL_MECHANICAL_PIPE, (pos, state) -> new TileEntityMAMechanicalPipe(MABlocks.ETERNAL_MECHANICAL_PIPE, pos, state));
    // pressurized tubes
    public static final TileEntityTypeRegistryObject<TileEntityMAPressurizedTube> PRISMATIC_PRESSURIZED_TUBE = registerTransmitter(MABlocks.PRISMATIC_PRESSURIZED_TUBE, (pos, state) -> new TileEntityMAPressurizedTube(MABlocks.PRISMATIC_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAPressurizedTube> FLARE_PRESSURIZED_TUBE = registerTransmitter(MABlocks.FLARE_PRESSURIZED_TUBE, (pos, state) -> new TileEntityMAPressurizedTube(MABlocks.FLARE_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAPressurizedTube> NEURAL_PRESSURIZED_TUBE = registerTransmitter(MABlocks.NEURAL_PRESSURIZED_TUBE, (pos, state) -> new TileEntityMAPressurizedTube(MABlocks.NEURAL_PRESSURIZED_TUBE, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAPressurizedTube> ETERNAL_PRESSURIZED_TUBE = registerTransmitter(MABlocks.ETERNAL_PRESSURIZED_TUBE, (pos, state) -> new TileEntityMAPressurizedTube(MABlocks.ETERNAL_PRESSURIZED_TUBE, pos, state));
    // logistic transporters
    public static final TileEntityTypeRegistryObject<TileEntityMALogisticalTransporter> PRISMATIC_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityMALogisticalTransporter(MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityMALogisticalTransporterBase::tickClient).serverTicker(TileEntityMATransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityMALogisticalTransporter> FLARE_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(MABlocks.FLARE_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityMALogisticalTransporter(MABlocks.FLARE_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityMALogisticalTransporterBase::tickClient).serverTicker(TileEntityMATransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityMALogisticalTransporter> NEURAL_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(MABlocks.NEURAL_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityMALogisticalTransporter(MABlocks.NEURAL_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityMALogisticalTransporterBase::tickClient).serverTicker(TileEntityMATransmitter::extraTickServer).build();
    public static final TileEntityTypeRegistryObject<TileEntityMALogisticalTransporter> ETERNAL_LOGISTICAL_TRANSPORTER = TILE_ENTITY_TYPES.builder(MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER, (pos, state) -> new TileEntityMALogisticalTransporter(MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER, pos, state)).clientTicker(TileEntityMALogisticalTransporterBase::tickClient).serverTicker(TileEntityMATransmitter::extraTickServer).build();
    // thermodynamic conductors
    public static final TileEntityTypeRegistryObject<TileEntityMAThermodynamicConductor> PRISMATIC_THERMODYNAMIC_CONDUCTOR = registerTransmitter(MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityMAThermodynamicConductor(MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAThermodynamicConductor> FLARE_THERMODYNAMIC_CONDUCTOR = registerTransmitter(MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityMAThermodynamicConductor(MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAThermodynamicConductor> NEURAL_THERMODYNAMIC_CONDUCTOR = registerTransmitter(MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityMAThermodynamicConductor(MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR, pos, state));
    public static final TileEntityTypeRegistryObject<TileEntityMAThermodynamicConductor> ETERNAL_THERMODYNAMIC_CONDUCTOR = registerTransmitter(MABlocks.ETERNAL_THERMODYNAMIC_CONDUCTOR, (pos, state) -> new TileEntityMAThermodynamicConductor(MABlocks.ETERNAL_THERMODYNAMIC_CONDUCTOR, pos, state));
    
    public static TileEntityTypeRegistryObject<? extends TileEntityMAFactory<?>> getMAFactoryTile(MAFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    public static void register(IEventBus eventBus) {
        TILE_ENTITY_TYPES.register(eventBus);
    }
}
