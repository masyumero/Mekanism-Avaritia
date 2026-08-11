package io.github.masyumero.mekavaritia.datagen.common.recipe;

import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.ExtendedCookingRecipeBuilder;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.datagen.recipe.RecipeCriterion;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

/**
 * Class for helpers that are also used by some of our recipe compat providers for convenience
 */
@NothingNullByDefault
public class RecipeProviderUtil {

    private RecipeProviderUtil() {
    }

    public static void addSmeltingBlastingRecipes(Consumer<FinishedRecipe> consumer, Ingredient smeltingInput, ItemLike output, float experience, int smeltingTime,
                                                  ResourceLocation blastingLocation, ResourceLocation smeltingLocation, RecipeCriterion... criteria) {
        ExtendedCookingRecipeBuilder blastingRecipe = ExtendedCookingRecipeBuilder.blasting(output, smeltingInput, smeltingTime / 2).experience(experience);
        ExtendedCookingRecipeBuilder smeltingRecipe = ExtendedCookingRecipeBuilder.smelting(output, smeltingInput, smeltingTime).experience(experience);
        //If there are any criteria add them
        for (RecipeCriterion criterion : criteria) {
            blastingRecipe.addCriterion(criterion);
            smeltingRecipe.addCriterion(criterion);
        }
        blastingRecipe.build(consumer, blastingLocation);
        smeltingRecipe.build(consumer, smeltingLocation);
    }
}
