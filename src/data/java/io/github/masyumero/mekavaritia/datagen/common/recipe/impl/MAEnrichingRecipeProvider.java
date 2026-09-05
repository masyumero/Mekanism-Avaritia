package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class MAEnrichingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "enriching/";
        addEnrichingEnrichedRecipes(consumer, basePath + "enriched/");
    }

    private void addEnrichingEnrichedRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        // Crystalline
        ItemStackToItemStackRecipeBuilder.enriching(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_CRYSTALLINE),
                MAItems.ENRICHED_CRYSTALLINE.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "crystalline"));
        // Blazing
        ItemStackToItemStackRecipeBuilder.enriching(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_BLAZING),
                MAItems.ENRICHED_BLAZING.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "blazing"));
        // Neutron
        ItemStackToItemStackRecipeBuilder.enriching(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_NEUTRON),
                MAItems.ENRICHED_NEUTRON.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "neutron"));
        // Infinite
        ItemStackToItemStackRecipeBuilder.enriching(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_INFINITY),
                MAItems.ENRICHED_INFINITY.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "infinite"));
    }
}
