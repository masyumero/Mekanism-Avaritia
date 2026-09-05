package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import fr.iglee42.evolvedmekanism.registries.EMContainerTypes;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.content.blocktype.MABlockShapes;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tier.MAICTier;
import io.github.masyumero.mekavaritia.common.tier.MAIPTier;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import io.github.masyumero.mekavaritia.common.util.MAUpgradeUtil;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;
import mekanism.common.util.EnumUtils;

import java.util.EnumSet;
import java.util.function.Supplier;

public class MABlockTypes {

    private static final Table<MAFactoryTier, FactoryType, MAFactory<?>> FACTORIES = HashBasedTable.create();

    public static final MAMachine.MAFactoryMachine<TileEntityAlloyer> ALLOYER = MAAddons.EVOLVEDMEKANISM.isLoaded() ? MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> EMTileEntityTypes.ALLOYER, MekanismAvaritiaLang.getAlloyer(), EMFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withComputerSupport("alloyer")
            .build() : null;

    public static final MAMachine.MAFactoryMachine<TileEntityEnergizedSmelter> ENERGIZED_SMELTER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.ENERGIZED_SMELTER, MekanismLang.DESCRIPTION_ENERGIZED_SMELTER, FactoryType.SMELTING)
            .withGui(() -> MekanismContainerTypes.ENERGIZED_SMELTER)
            .withSound(MekanismSounds.ENERGIZED_SMELTER)
            .withEnergyConfig(MekanismConfig.usage.energizedSmelter, MekanismConfig.storage.energizedSmelter)
            .withComputerSupport("energizedSmelter")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityEnrichmentChamber> ENRICHMENT_CHAMBER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.ENRICHMENT_CHAMBER, MekanismLang.DESCRIPTION_ENRICHMENT_CHAMBER, FactoryType.ENRICHING)
            .withGui(() -> MekanismContainerTypes.ENRICHMENT_CHAMBER)
            .withSound(MekanismSounds.ENRICHMENT_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.enrichmentChamber, MekanismConfig.storage.enrichmentChamber)
            .withComputerSupport("enrichmentChamber")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityCrusher> CRUSHER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.CRUSHER, MekanismLang.DESCRIPTION_CRUSHER, FactoryType.CRUSHING)
            .withGui(() -> MekanismContainerTypes.CRUSHER)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MekanismConfig.usage.crusher, MekanismConfig.storage.crusher)
            .withComputerSupport("crusher")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityOsmiumCompressor> OSMIUM_COMPRESSOR = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.OSMIUM_COMPRESSOR, MekanismLang.DESCRIPTION_OSMIUM_COMPRESSOR, FactoryType.COMPRESSING)
            .withGui(() -> MekanismContainerTypes.OSMIUM_COMPRESSOR)
            .withSound(MekanismSounds.OSMIUM_COMPRESSOR)
            .withEnergyConfig(MekanismConfig.usage.osmiumCompressor, MekanismConfig.storage.osmiumCompressor)
            .withSupportedUpgrades(MAUpgradeUtil.getDefaultGasUpgrades())
            .withComputerSupport("osmiumCompressor")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityCombiner> COMBINER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.COMBINER, MekanismLang.DESCRIPTION_COMBINER, FactoryType.COMBINING)
            .withGui(() -> MekanismContainerTypes.COMBINER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withComputerSupport("combiner")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityPurificationChamber> PURIFICATION_CHAMBER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.PURIFICATION_CHAMBER, MekanismLang.DESCRIPTION_PURIFICATION_CHAMBER, FactoryType.PURIFYING)
            .withGui(() -> MekanismContainerTypes.PURIFICATION_CHAMBER)
            .withSound(MekanismSounds.PURIFICATION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.purificationChamber, MekanismConfig.storage.purificationChamber)
            .withSupportedUpgrades(MAUpgradeUtil.getDefaultGasUpgrades())
            .withComputerSupport("purificationChamber")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            .withSupportedUpgrades(MAUpgradeUtil.getDefaultGasUpgrades())
            .withComputerSupport("chemicalInjectionChamber")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityMetallurgicInfuser> METALLURGIC_INFUSER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.METALLURGIC_INFUSER, MekanismLang.DESCRIPTION_METALLURGIC_INFUSER, FactoryType.INFUSING)
            .withGui(() -> MekanismContainerTypes.METALLURGIC_INFUSER)
            .withSound(MekanismSounds.METALLURGIC_INFUSER)
            .withEnergyConfig(MekanismConfig.usage.metallurgicInfuser, MekanismConfig.storage.metallurgicInfuser)
            .withCustomShape(BlockShapes.METALLURGIC_INFUSER)
            .withComputerSupport("metallurgicInfuser")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityPrecisionSawmill> PRECISION_SAWMILL = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.PRECISION_SAWMILL, MekanismLang.DESCRIPTION_PRECISION_SAWMILL, FactoryType.SAWING)
            .withGui(() -> MekanismContainerTypes.PRECISION_SAWMILL)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .withComputerSupport("precisionSawmill")
            .build();

    public static final MAMachine<TileEntityElectricNeutronCollector> ELECTRIC_NEUTRON_COLLECTOR = MAMachine.MAMachineBuilder
            .createMAMachine(() -> MATileEntityTypes.ELECTRIC_NEUTRON_COLLECTOR, MekanismAvaritiaLang.DESCRIPTION_ELECTRIC_NEUTRON_COLLECTOR)
            .withCustomShape(MABlockShapes.ELECTRIC_NEUTRON_COLLECTOR)
            .withSound(MekanismSounds.RESISTIVE_HEATER)
            .withGui(() -> MAContainerTypes.ELECTRIC_NEUTRON_COLLECTOR)
            .withEnergyConfig(LoadConfig.USAGE_CONFIG.electricNeutronCollector, LoadConfig.STORAGE_CONFIG.electricNeutronCollector)
            .withSupportedUpgrades(MAAddons.MEKANISM_EXTRAS.isLoaded() ? EnumSet.of(Upgrade.MUFFLING,ExtraUpgrade.CREATIVE) : EnumSet.of(Upgrade.MUFFLING))
            .withComputerSupport("ElectricNeutronCollector")
            .replace(Attributes.ACTIVE_LIGHT)
            .build();

    // Induction Cells
    public static final BlockTypeTile<TileEntityMAInductionCell> PRISMATIC_INDUCTION_CELL = createInductionCell(MAICTier.PRISMATIC, () -> MATileEntityTypes.PRISMATIC_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityMAInductionCell> FLARE_INDUCTION_CELL = createInductionCell(MAICTier.FLARE, () -> MATileEntityTypes.FLARE_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityMAInductionCell> NEURAL_INDUCTION_CELL = createInductionCell(MAICTier.NEURAL, () -> MATileEntityTypes.NEURAL_INDUCTION_CELL);
    public static final BlockTypeTile<TileEntityMAInductionCell> ETERNAL_INDUCTION_CELL = createInductionCell(MAICTier.ETERNAL, () -> MATileEntityTypes.ETERNAL_INDUCTION_CELL);
    // Induction Provide
    public static final BlockTypeTile<TileEntityMAInductionProvider> PRISMATIC_INDUCTION_PROVIDER = createInductionProvider(MAIPTier.PRISMATIC, () -> MATileEntityTypes.PRISMATIC_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityMAInductionProvider> FLARE_INDUCTION_PROVIDER = createInductionProvider(MAIPTier.FLARE, () -> MATileEntityTypes.FLARE_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityMAInductionProvider> NEURAL_INDUCTION_PROVIDER = createInductionProvider(MAIPTier.NEURAL, () -> MATileEntityTypes.NEURAL_INDUCTION_PROVIDER);
    public static final BlockTypeTile<TileEntityMAInductionProvider> ETERNAL_INDUCTION_PROVIDER = createInductionProvider(MAIPTier.ETERNAL, () -> MATileEntityTypes.ETERNAL_INDUCTION_PROVIDER);


    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                FACTORIES.put(tier, type, MAFactory.MAFactoryBuilder.createFactory(() -> MATileEntityTypes.getMAFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static MAFactory<?> getMAFactory(MAFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }
    
    private static <TILE extends TileEntityMAInductionCell> BlockTypeTile<TILE> createInductionCell(MAICTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_CELL)
                .withEnergyConfig(tier::getMaxEnergy)
                .with(new MAAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }

    private static <TILE extends TileEntityMAInductionProvider> BlockTypeTile<TILE> createInductionProvider(MAIPTier tier, Supplier<TileEntityTypeRegistryObject<TILE>> tile) {
        return BlockTypeTile.BlockTileBuilder.createBlock(tile, MekanismLang.DESCRIPTION_INDUCTION_PROVIDER)
                .with(new MAAttributeTier<>(tier))
                .internalMultiblock()
                .build();
    }
}
