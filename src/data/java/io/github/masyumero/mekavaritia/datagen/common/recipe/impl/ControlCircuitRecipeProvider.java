package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.ExtendedShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.api.providers.IItemProvider;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class ControlCircuitRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern CIRCUIT_PATTERN = RecipePattern.createPattern(TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "control_circuit/";
        addCircuitUpgradeRecipe(basePath, consumer, MAItems.PRISMATIC_CONTROL_CIRCUIT, MATags.Items.ALLOYS_PRISMATIC, MekanismTags.Items.CIRCUITS_ULTIMATE, MATier.PRISMATIC);
        addCircuitUpgradeRecipe(basePath, consumer, MAItems.FLARE_CONTROL_CIRCUIT, MATags.Items.ALLOYS_FLARE, MATags.Items.PRISMATIC_CONTROL_CIRCUIT, MATier.FLARE);
        addCircuitUpgradeRecipe(basePath, consumer, MAItems.NEURAL_CONTROL_CIRCUIT, MATags.Items.ALLOYS_NEURAL, MATags.Items.FLARE_CONTROL_CIRCUIT, MATier.NEURAL);
        addCircuitUpgradeRecipe(basePath, consumer, MAItems.ETERNAL_CONTROL_CIRCUIT, MATags.Items.ALLOYS_ETERNAL, MATags.Items.NEURAL_CONTROL_CIRCUIT, MATier.ETERNAL);
    }

    private void addCircuitUpgradeRecipe(String basePath, Consumer<FinishedRecipe> consumer, IItemProvider output, TagKey<Item> circuitTag, TagKey<Item> alloyTag, MATier tier) {
        ExtendedShapedRecipeBuilder.shapedRecipe(output)
                .pattern(CIRCUIT_PATTERN)
                .key(Pattern.ALLOY, circuitTag)
                .key(Pattern.CIRCUIT, alloyTag)
                .build(consumer, MAUtils.rl(basePath + tier.getLowerName()));
    }
}
