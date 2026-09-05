package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.ExtendedShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.api.providers.IItemProvider;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class MATransmitterRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern TRANSMITTER_UPGRADE_PATTERN = RecipePattern.createPattern(
            TripleLine.of(Pattern.PREVIOUS, Pattern.PREVIOUS, Pattern.PREVIOUS),
            TripleLine.of(Pattern.PREVIOUS, Pattern.ALLOY, Pattern.PREVIOUS),
            TripleLine.of(Pattern.PREVIOUS, Pattern.PREVIOUS, Pattern.PREVIOUS));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "transmitter/";
        addLogisticalTransporterRecipes(consumer, basePath + "logistical_transporter/");
        addMechanicalPipeRecipes(consumer, basePath + "mechanical_pipe/");
        addPressurizedTubeRecipes(consumer, basePath + "pressurized_tube/");
        addThermodynamicConductorRecipes(consumer, basePath + "thermodynamic_conductor/");
        addUniversalCableRecipes(consumer, basePath + "universal_cable/");
    }

    private void addLogisticalTransporterRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER, MekanismBlocks.ULTIMATE_LOGISTICAL_TRANSPORTER, MATags.Items.ALLOYS_PRISMATIC);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.FLARE_LOGISTICAL_TRANSPORTER, MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER, MATags.Items.ALLOYS_FLARE);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.NEURAL_LOGISTICAL_TRANSPORTER, MABlocks.FLARE_LOGISTICAL_TRANSPORTER, MATags.Items.ALLOYS_NEURAL);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER, MABlocks.NEURAL_LOGISTICAL_TRANSPORTER, MATags.Items.ALLOYS_ETERNAL);
    }

    private void addMechanicalPipeRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.PRISMATIC_MECHANICAL_PIPE, MekanismBlocks.ULTIMATE_MECHANICAL_PIPE, MATags.Items.ALLOYS_PRISMATIC);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.FLARE_MECHANICAL_PIPE, MABlocks.PRISMATIC_MECHANICAL_PIPE, MATags.Items.ALLOYS_FLARE);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.NEURAL_MECHANICAL_PIPE, MABlocks.FLARE_MECHANICAL_PIPE, MATags.Items.ALLOYS_NEURAL);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.ETERNAL_MECHANICAL_PIPE, MABlocks.NEURAL_MECHANICAL_PIPE, MATags.Items.ALLOYS_ETERNAL);
    }

    private void addPressurizedTubeRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.PRISMATIC_PRESSURIZED_TUBE, MekanismBlocks.ULTIMATE_PRESSURIZED_TUBE, MATags.Items.ALLOYS_PRISMATIC);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.FLARE_PRESSURIZED_TUBE, MABlocks.PRISMATIC_PRESSURIZED_TUBE, MATags.Items.ALLOYS_FLARE);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.NEURAL_PRESSURIZED_TUBE, MABlocks.FLARE_PRESSURIZED_TUBE, MATags.Items.ALLOYS_NEURAL);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.ETERNAL_PRESSURIZED_TUBE, MABlocks.NEURAL_PRESSURIZED_TUBE, MATags.Items.ALLOYS_ETERNAL);
    }

    private void addThermodynamicConductorRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR, MekanismBlocks.ULTIMATE_THERMODYNAMIC_CONDUCTOR, MATags.Items.ALLOYS_PRISMATIC);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR, MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR, MATags.Items.ALLOYS_FLARE);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR, MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR, MATags.Items.ALLOYS_NEURAL);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.ETERNAL_THERMODYNAMIC_CONDUCTOR, MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR, MATags.Items.ALLOYS_ETERNAL);
    }

    private void addUniversalCableRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.PRISMATIC_UNIVERSAL_CABLE, MekanismBlocks.ULTIMATE_UNIVERSAL_CABLE, MATags.Items.ALLOYS_PRISMATIC);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.FLARE_UNIVERSAL_CABLE, MABlocks.PRISMATIC_UNIVERSAL_CABLE, MATags.Items.ALLOYS_FLARE);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.NEURAL_UNIVERSAL_CABLE, MABlocks.FLARE_UNIVERSAL_CABLE, MATags.Items.ALLOYS_NEURAL);
        addTransmitterUpgradeRecipe(consumer, basePath, MABlocks.ETERNAL_UNIVERSAL_CABLE, MABlocks.NEURAL_UNIVERSAL_CABLE, MATags.Items.ALLOYS_ETERNAL);
    }

    private void addTransmitterUpgradeRecipe(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<?, ?> transmitter,
                                             IItemProvider previousTransmitter, TagKey<Item> alloyTag) {
        ExtendedShapedRecipeBuilder.shapedRecipe(transmitter, 8)
                .pattern(TRANSMITTER_UPGRADE_PATTERN)
                .key(Pattern.PREVIOUS, previousTransmitter)
                .key(Pattern.ALLOY, alloyTag)
                .build(consumer, MAUtils.rl(basePath + Attribute.getBaseTier(transmitter.getBlock()).getLowerName()));
    }
}
