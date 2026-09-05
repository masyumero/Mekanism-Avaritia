package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MAInfuseTypes;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackToChemicalRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class MAInfusionConversionRecipeProvider implements ISubRecipeProvider {
    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "infusion_conversion/";
        addInfusionConversionCrystallineRecipes(consumer, basePath + "crystalline/");
        addInfusionConversionBlazingRecipes(consumer, basePath + "blazing/");
        addInfusionConversionNeutronRecipes(consumer, basePath + "neutron/");
        addInfusionConversionInfinityRecipes(consumer, basePath + "infinity/");
    }

    private void addInfusionConversionCrystallineRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        //Dust
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_CRYSTALLINE),
                MAInfuseTypes.CRYSTALLINE.getStack(10)
        ).build(consumer, MAUtils.rl(basePath + "from_dust"));
        //Enriched
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.ENRICHED_CRYSTALLINE),
                MAInfuseTypes.CRYSTALLINE.getStack(80)
        ).build(consumer, MAUtils.rl(basePath + "from_enriched"));
    }

    private void addInfusionConversionBlazingRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        //Dust
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_BLAZING),
                MAInfuseTypes.BLAZING.getStack(10)
        ).build(consumer, MAUtils.rl(basePath + "from_dust"));
        //Enriched
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.ENRICHED_BLAZING),
                MAInfuseTypes.BLAZING.getStack(80)
        ).build(consumer, MAUtils.rl(basePath + "from_enriched"));
    }

    private void addInfusionConversionNeutronRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        //Dust
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_NEUTRON),
                MAInfuseTypes.NEUTRON.getStack(10)
        ).build(consumer, MAUtils.rl(basePath + "from_dust"));
        //Enriched
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.ENRICHED_NEUTRON),
                MAInfuseTypes.NEUTRON.getStack(80)
        ).build(consumer, MAUtils.rl(basePath + "from_enriched"));
    }

    private void addInfusionConversionInfinityRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        //Dust
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.DUSTS_INFINITY),
                MAInfuseTypes.INFINITY.getStack(10)
        ).build(consumer, MAUtils.rl(basePath + "from_dust"));
        //Enriched
        ItemStackToChemicalRecipeBuilder.infusionConversion(
                IngredientCreatorAccess.item().from(MATags.Items.ENRICHED_INFINITY),
                MAInfuseTypes.INFINITY.getStack(80)
        ).build(consumer, MAUtils.rl(basePath + "from_enriched"));
    }
}
