package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import io.github.masyumero.mekavaritia.common.recipe.condition.ConfigEnabledCondition;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.ExtremeCraftingRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class ExtremeCraftingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {

        ExtremeCraftingRecipeBuilder.shapedRecipe(EMItems.CREATIVE_ALLOY)
                .pattern(RecipePattern.createPattern(
                        RecipePattern.NonupleLine.of('S','S','S','A','H','A','S','S','S'),
                        RecipePattern.NonupleLine.of('S','L','L','L','L','L','L','L','S'),
                        RecipePattern.NonupleLine.of('S','L','N','N','B','N','N','L','S'),
                        RecipePattern.NonupleLine.of('A','L','N','C','I','C','N','L','A'),
                        RecipePattern.NonupleLine.of('H','L','B','I','E','I','B','L','H'),
                        RecipePattern.NonupleLine.of('A','L','N','C','I','C','N','L','A'),
                        RecipePattern.NonupleLine.of('S','L','N','N','B','N','N','L','S'),
                        RecipePattern.NonupleLine.of('S','L','L','L','L','L','L','L','S'),
                        RecipePattern.NonupleLine.of('S','S','S','A','H','A','S','S','S')
                ))
                .key('E', EMItems.EXOVERSAL_ALLOY)
                .key('I', MAItems.INFINITY_ALLOY)
                .key('C', ModItems.infinity_catalyst.get())
                .key('B', MAItems.BLAZING_ALLOY)
                .key('N', MAItems.NEUTRON_ALLOY)
                .key('L', MAItems.CRYSTALLINE_ALLOY)
                .key('H', EMItems.HYPERCHARGED_ALLOY)
                .key('A', EMItems.SUBATOMIC_ALLOY)
                .key('S', EMItems.SINGULAR_ALLOY)
                .addCondition(ConfigEnabledCondition.HARD_CREATIVE_ALLOY_RECIPE)
                .build(consumer, MAUtils.rl("hard/creative_alloy"));
    }
}
