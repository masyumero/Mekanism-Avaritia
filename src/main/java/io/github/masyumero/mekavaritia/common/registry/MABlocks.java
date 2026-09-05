package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.tier.IMATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.block.prefab.BlockMAFactoryMachine;
import io.github.masyumero.mekavaritia.common.block.transmitter.*;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekavaritia.common.item.block.MAItemBlockInductionCell;
import io.github.masyumero.mekavaritia.common.item.block.MAItemBlockInductionProvider;
import io.github.masyumero.mekavaritia.common.item.block.machine.ItemBlockMAFactory;
import io.github.masyumero.mekavaritia.common.item.block.transmitter.*;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.block.prefab.BlockTile;
import mekanism.common.content.blocktype.BlockType;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.item.block.machine.ItemBlockMachine;
import mekanism.common.registration.impl.BlockDeferredRegister;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.*;
import mekanism.common.util.EnumUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class MABlocks {

    public static final BlockDeferredRegister BLOCK = new BlockDeferredRegister(MekanismAvaritia.MODID);

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(String tierName, String suffix, Supplier<? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        return BLOCK.register(tierName + suffix, blockSupplier, itemCreator);
    }

    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(BlockType type, String registerName, Function<MapColor, ? extends BLOCK> blockSupplier, Function<BLOCK, ITEM> itemCreator) {
        IMATier tier = Objects.requireNonNull(type.get(MAAttributeTier.class)).tier();
        return BLOCK.register(registerName, () -> blockSupplier.apply(tier.getMATier().getMapColor()), itemCreator);
    }

    private static final Table<MAFactoryTier, FactoryType, BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>> FACTORIES = HashBasedTable.create();

    static {
        // factories
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, registerFactory(MABlockTypes.getMAFactory(tier, type)));
            }
        }
    }

    public static final BlockRegistryObject<BlockTile.BlockTileModel<TileEntityElectricNeutronCollector, MAMachine<TileEntityElectricNeutronCollector>>, ItemBlockMachine> ELECTRIC_NEUTRON_COLLECTOR = BLOCK.register("electric_neutron_collector", () -> new BlockTile.BlockTileModel<>(MABlockTypes.ELECTRIC_NEUTRON_COLLECTOR, properties -> properties.mapColor(MapColor.METAL)), ItemBlockMachine::new);
    // Induction Cells
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>, MAItemBlockInductionCell> PRISMATIC_INDUCTION_CELL = registerInductionCell("prismatic", MABlockTypes.PRISMATIC_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>, MAItemBlockInductionCell> FLARE_INDUCTION_CELL = registerInductionCell("flare", MABlockTypes.FLARE_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>, MAItemBlockInductionCell> NEURAL_INDUCTION_CELL = registerInductionCell("neural", MABlockTypes.NEURAL_INDUCTION_CELL);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>, MAItemBlockInductionCell> ETERNAL_INDUCTION_CELL = registerInductionCell("eternal", MABlockTypes.ETERNAL_INDUCTION_CELL);
    // Induction Provider
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>, MAItemBlockInductionProvider> PRISMATIC_INDUCTION_PROVIDER = registerInductionProvider("prismatic", MABlockTypes.PRISMATIC_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>, MAItemBlockInductionProvider> FLARE_INDUCTION_PROVIDER = registerInductionProvider("flare", MABlockTypes.FLARE_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>, MAItemBlockInductionProvider> NEURAL_INDUCTION_PROVIDER = registerInductionProvider("neural", MABlockTypes.NEURAL_INDUCTION_PROVIDER);
    public static final BlockRegistryObject<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>, MAItemBlockInductionProvider> ETERNAL_INDUCTION_PROVIDER = registerInductionProvider("eternal", MABlockTypes.ETERNAL_INDUCTION_PROVIDER);
    // Universal Cables
    public static final BlockRegistryObject<MABlockUniversalCable, MAItemBlockUniversalCable> PRISMATIC_UNIVERSAL_CABLE = registerUniversalCable("prismatic", CableTier.BASIC);
    public static final BlockRegistryObject<MABlockUniversalCable, MAItemBlockUniversalCable> FLARE_UNIVERSAL_CABLE = registerUniversalCable("flare", CableTier.ADVANCED);
    public static final BlockRegistryObject<MABlockUniversalCable, MAItemBlockUniversalCable> NEURAL_UNIVERSAL_CABLE = registerUniversalCable("neural", CableTier.ELITE);
    public static final BlockRegistryObject<MABlockUniversalCable, MAItemBlockUniversalCable> ETERNAL_UNIVERSAL_CABLE = registerUniversalCable("eternal", CableTier.ULTIMATE);
    // Mechanical Pipes
    public static final BlockRegistryObject<MABlockMechanicalPipe, MAItemBlockMechanicalPipe> PRISMATIC_MECHANICAL_PIPE = registerMechanicalPipe("prismatic", PipeTier.BASIC);
    public static final BlockRegistryObject<MABlockMechanicalPipe, MAItemBlockMechanicalPipe> FLARE_MECHANICAL_PIPE = registerMechanicalPipe("flare", PipeTier.ADVANCED);
    public static final BlockRegistryObject<MABlockMechanicalPipe, MAItemBlockMechanicalPipe> NEURAL_MECHANICAL_PIPE = registerMechanicalPipe("neural", PipeTier.ELITE);
    public static final BlockRegistryObject<MABlockMechanicalPipe, MAItemBlockMechanicalPipe> ETERNAL_MECHANICAL_PIPE = registerMechanicalPipe("eternal", PipeTier.ULTIMATE);
    // Pressurized Tubes
    public static final BlockRegistryObject<MABlockPressurizedTube, MAItemBlockPressurizedTube> PRISMATIC_PRESSURIZED_TUBE = registerPressurizedTube("prismatic", TubeTier.BASIC);
    public static final BlockRegistryObject<MABlockPressurizedTube, MAItemBlockPressurizedTube> FLARE_PRESSURIZED_TUBE = registerPressurizedTube("flare", TubeTier.ADVANCED);
    public static final BlockRegistryObject<MABlockPressurizedTube, MAItemBlockPressurizedTube> NEURAL_PRESSURIZED_TUBE = registerPressurizedTube("neural", TubeTier.ELITE);
    public static final BlockRegistryObject<MABlockPressurizedTube, MAItemBlockPressurizedTube> ETERNAL_PRESSURIZED_TUBE = registerPressurizedTube("eternal", TubeTier.ULTIMATE);
    // Logistical Transporters
    public static final BlockRegistryObject<MABlockLogisticalTransporter, MAItemBlockLogisticalTransporter> PRISMATIC_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("prismatic", TransporterTier.BASIC);
    public static final BlockRegistryObject<MABlockLogisticalTransporter, MAItemBlockLogisticalTransporter> FLARE_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("flare", TransporterTier.ADVANCED);
    public static final BlockRegistryObject<MABlockLogisticalTransporter, MAItemBlockLogisticalTransporter> NEURAL_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("neural", TransporterTier.ELITE);
    public static final BlockRegistryObject<MABlockLogisticalTransporter, MAItemBlockLogisticalTransporter> ETERNAL_LOGISTICAL_TRANSPORTER = registerLogisticalTransporter("eternal", TransporterTier.ULTIMATE);
    // Thermodynamic Conductors
    public static final BlockRegistryObject<MABlockThermodynamicConductor, MAItemBlockThermodynamicConductor> PRISMATIC_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("prismatic", ConductorTier.BASIC);
    public static final BlockRegistryObject<MABlockThermodynamicConductor, MAItemBlockThermodynamicConductor> FLARE_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("flare", ConductorTier.ADVANCED);
    public static final BlockRegistryObject<MABlockThermodynamicConductor, MAItemBlockThermodynamicConductor> NEURAL_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("neural", ConductorTier.ELITE);
    public static final BlockRegistryObject<MABlockThermodynamicConductor, MAItemBlockThermodynamicConductor> ETERNAL_THERMODYNAMIC_CONDUCTOR = registerThermodynamicConductor("eternal", ConductorTier.ULTIMATE);
    
    private static <TILE extends TileEntityMAFactory<?>> BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory> registerFactory(MAFactory<TILE> type) {
        IMATier tier = Objects.requireNonNull(type.get(MAAttributeTier.class)).tier();
        return registerTieredBlock(tier.getMATier().getLowerName(), "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockMAFactoryMachine.BlockMAFactory<>(type), ItemBlockMAFactory::new);
    }

    public static BlockRegistryObject<BlockMAFactoryMachine.BlockMAFactory<?>, ItemBlockMAFactory>  getMAFactory(@NotNull MAFactoryTier tier, @NotNull FactoryType type) {
        return FACTORIES.get(tier, type);
    }

    private static BlockRegistryObject<BlockTile<TileEntityMAInductionCell, BlockTypeTile<TileEntityMAInductionCell>>, MAItemBlockInductionCell> registerInductionCell(String tileName, BlockTypeTile<TileEntityMAInductionCell> type) {
        return registerTieredBlock(type, tileName + "_induction_cell", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), MAItemBlockInductionCell::new);
    }

    private static BlockRegistryObject<BlockTile<TileEntityMAInductionProvider, BlockTypeTile<TileEntityMAInductionProvider>>, MAItemBlockInductionProvider> registerInductionProvider(String tileName, BlockTypeTile<TileEntityMAInductionProvider> type) {
        return registerTieredBlock(type, tileName + "_induction_provider", color -> new BlockTile<>(type, properties -> properties.mapColor(color)), MAItemBlockInductionProvider::new);
    }
    
    private static BlockRegistryObject<MABlockUniversalCable, MAItemBlockUniversalCable> registerUniversalCable(String tileName, CableTier tier) {
        return registerTieredBlock(tileName, "_universal_cable", () -> new MABlockUniversalCable(tier), MAItemBlockUniversalCable::new);
    }

    private static BlockRegistryObject<MABlockMechanicalPipe, MAItemBlockMechanicalPipe> registerMechanicalPipe(String tileName, PipeTier tier) {
        return registerTieredBlock(tileName, "_mechanical_pipe", () -> new MABlockMechanicalPipe(tier), MAItemBlockMechanicalPipe::new);
    }

    private static BlockRegistryObject<MABlockPressurizedTube, MAItemBlockPressurizedTube> registerPressurizedTube(String tileName, TubeTier tier) {
        return registerTieredBlock(tileName, "_pressurized_tube", () -> new MABlockPressurizedTube(tier), MAItemBlockPressurizedTube::new);
    }

    private static BlockRegistryObject<MABlockLogisticalTransporter, MAItemBlockLogisticalTransporter> registerLogisticalTransporter(String tileName, TransporterTier tier) {
        return registerTieredBlock(tileName, "_logistical_transporter", () -> new MABlockLogisticalTransporter(tier), MAItemBlockLogisticalTransporter::new);
    }

    private static BlockRegistryObject<MABlockThermodynamicConductor, MAItemBlockThermodynamicConductor> registerThermodynamicConductor(String tileName, ConductorTier tier) {
        return registerTieredBlock(tileName, "_thermodynamic_conductor", () -> new MABlockThermodynamicConductor(tier), MAItemBlockThermodynamicConductor::new);
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}
