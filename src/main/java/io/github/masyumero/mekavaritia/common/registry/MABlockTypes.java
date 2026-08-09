package io.github.masyumero.mekavaritia.common.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import fr.iglee42.evolvedmekanism.registries.EMContainerTypes;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import io.github.masyumero.mekavaritia.common.content.blocktype.MABlockShapes;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAFactory;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.tile.machine.TileEntityElectricNeutronCollector;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.blocktype.BlockShapes;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;
import mekanism.common.util.EnumUtils;
import net.minecraftforge.fml.ModList;

import java.util.EnumSet;
import java.util.Set;

public class MABlockTypes {

    private static final Table<MAFactoryTier, FactoryType, MAFactory<?>> FACTORIES = HashBasedTable.create();
    private static final Set<Upgrade> UPGRADES = ModList.get().isLoaded("mekanism_extras") ? EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE) : EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS);

    public static final MAMachine.MAFactoryMachine<TileEntityAlloyer> ALLOYER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> EMTileEntityTypes.ALLOYER, MekanismAvaritiaLang.NULL.getAlloyer(), EMFactoryType.ALLOYING)
            .withGui(() -> EMContainerTypes.ALLOYER)
            .withSound(MekanismSounds.COMBINER)
            .withEnergyConfig(MekanismConfig.usage.combiner, MekanismConfig.storage.combiner)
            .withComputerSupport("alloyer")
            .build();

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
            //.withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS))
            .withSupportedUpgrades(UPGRADES)
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
            //.withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS))
            .withSupportedUpgrades(UPGRADES)
            .withComputerSupport("purificationChamber")
            .build();

    public static final MAMachine.MAFactoryMachine<TileEntityChemicalInjectionChamber> CHEMICAL_INJECTION_CHAMBER = MAMachine.MAMachineBuilder
            .createMAFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_INJECTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_INJECTION_CHAMBER, FactoryType.INJECTING)
            .withGui(() -> MekanismContainerTypes.CHEMICAL_INJECTION_CHAMBER)
            .withSound(MekanismSounds.CHEMICAL_INJECTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalInjectionChamber, MekanismConfig.storage.chemicalInjectionChamber)
            //.withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS))
            .withSupportedUpgrades(UPGRADES)
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
            //.withEnergyConfig(LoadConfig.usageConfig.electricNeutronCollector, LoadConfig.storageConfig.electricNeutronCollector)
            .withEnergyConfig(MekanismConfig.usage.precisionSawmill, MekanismConfig.storage.precisionSawmill)
            .withSupportedUpgrades(ModList.get().isLoaded("mekanism_extras") ? EnumSet.of(Upgrade.MUFFLING,ExtraUpgrade.CREATIVE) : EnumSet.of(Upgrade.MUFFLING))
            .withComputerSupport("ElectricNeutronCollector")
            .replace(Attributes.ACTIVE_LIGHT)
            .build();


    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                if (type == EMFactoryType.ALLOYING) {
                    if(ModList.get().isLoaded("evolvedmekanism")) {
                        FACTORIES.put(tier, type, MAFactory.MAFactoryBuilder.createFactory(() -> MATileEntityTypes.getMAFactoryTile(tier, type), type, tier).build());
                    }
                } else {
                    FACTORIES.put(tier, type, MAFactory.MAFactoryBuilder.createFactory(() -> MATileEntityTypes.getMAFactoryTile(tier, type), type, tier).build());
                }
            }
        }
    }

    public static MAFactory<?> getMAFactory(MAFactoryTier tier, FactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
