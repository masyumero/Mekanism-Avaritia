package io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAFactoryMachine;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAMachineBuilder;
import io.github.masyumero.mekavaritia.common.integration.mekaf.content.blocktype.MAAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.content.blocktype.MAAdvancedFactory.MAAdvancedFactoryBuilder;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.registries.MekanismSounds;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tile.machine.*;

import java.util.EnumSet;

public class MAAdvancedFactoryBlockTypes {

    private MAAdvancedFactoryBlockTypes() {
    }

    private static final Table<MAFactoryTier, AdvancedFactoryType, MAAdvancedFactory<?>> FACTORIES = HashBasedTable.create();

    public static final MAFactoryMachine<TileEntityChemicalOxidizer> CHEMICAL_OXIDIZER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_OXIDIZER, MekanismLang.DESCRIPTION_CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING)
            .withSound(MekanismSounds.CHEMICAL_OXIDIZER)
            .withEnergyConfig(MekanismConfig.usage.oxidationChamber, MekanismConfig.storage.oxidationChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final MAFactoryMachine<TileEntityChemicalDissolutionChamber> CHEMICAL_DISSOLUTION_CHAMBER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_DISSOLUTION_CHAMBER, MekanismLang.DESCRIPTION_CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING)
            .withSound(MekanismSounds.CHEMICAL_DISSOLUTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.chemicalDissolutionChamber, MekanismConfig.storage.chemicalDissolutionChamber)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final MAFactoryMachine<TileEntityChemicalWasher> CHEMICAL_WASHER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_WASHER, MekanismLang.DESCRIPTION_CHEMICAL_WASHER, AdvancedFactoryType.WASHING)
            .withSound(MekanismSounds.CHEMICAL_WASHER)
            .withEnergyConfig(MekanismConfig.usage.chemicalWasher, MekanismConfig.storage.chemicalWasher)
            .build();

    public static final MAFactoryMachine<TileEntityChemicalCrystallizer> CHEMICAL_CRYSTALLIZER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.CHEMICAL_CRYSTALLIZER, MekanismLang.DESCRIPTION_CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING)
            .withSound(MekanismSounds.CHEMICAL_CRYSTALLIZER)
            .withEnergyConfig(MekanismConfig.usage.chemicalCrystallizer, MekanismConfig.storage.chemicalCrystallizer)
            .build();

    public static final MAFactoryMachine<TileEntityPressurizedReactionChamber> PRESSURIZED_REACTION_CHAMBER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PRESSURIZED_REACTION_CHAMBER, MekanismLang.DESCRIPTION_PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING)
            .withSound(MekanismSounds.PRESSURIZED_REACTION_CHAMBER)
            .withEnergyConfig(MekanismConfig.usage.pressurizedReactionBase, MekanismConfig.storage.pressurizedReactionBase)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final MAFactoryMachine<TileEntityIsotopicCentrifuge> ISOTOPIC_CENTRIFUGE = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.ISOTOPIC_CENTRIFUGE, MekanismLang.DESCRIPTION_ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING)
            .withSound(MekanismSounds.ISOTOPIC_CENTRIFUGE)
            .withEnergyConfig(MekanismConfig.usage.isotopicCentrifuge, MekanismConfig.storage.isotopicCentrifuge)
            .build();

    public static final MAFactoryMachine<TileEntityNutritionalLiquifier> NUTRITIONAL_LIQUIFIER = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.NUTRITIONAL_LIQUIFIER, MekanismLang.DESCRIPTION_NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING)
            .withSound(MekanismSounds.NUTRITIONAL_LIQUIFIER)
            .withEnergyConfig(MekanismConfig.usage.nutritionalLiquifier, MekanismConfig.storage.nutritionalLiquifier)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final MAFactoryMachine<TileEntityPigmentExtractor> PIGMENT_EXTRACTOR = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PIGMENT_EXTRACTOR, MekanismLang.DESCRIPTION_PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING)
            .withSound(MekanismSounds.PIGMENT_EXTRACTOR)
            .withEnergyConfig(MekanismConfig.usage.pigmentExtractor, MekanismConfig.storage.pigmentExtractor)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    public static final MAFactoryMachine<TileEntityPaintingMachine> PAINTING_MACHINE = MAMachineBuilder
            .createMAAdvancedFactoryMachine(() -> MekanismTileEntityTypes.PAINTING_MACHINE, MekanismLang.DESCRIPTION_PAINTING_MACHINE, AdvancedFactoryType.PAINTING)
            .withSound(MekanismSounds.PAINTING_MACHINE)
            .withEnergyConfig(MekanismConfig.usage.paintingMachine, MekanismConfig.storage.paintingMachine)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
                FACTORIES.put(tier, type, MAAdvancedFactoryBuilder.createAdvancedFactory(() -> MAAdvancedFactoryTileEntityTypes.getMAAdvancedFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static MAAdvancedFactory<?> getMAAdvancedFactory(MAFactoryTier tier, AdvancedFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}
