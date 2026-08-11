package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.RecipeProviderUtil;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class SmeltingRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "processing/";
        RecipeProviderUtil.addSmeltingBlastingRecipes(consumer,
                Ingredient.of(MAItems.CRYSTALLINE_DUST),
                ModItems.crystal_matrix_ingot.get(),
                0.3F, 200,
                MAUtils.rl(basePath + "crystal_matrix/ingot/from_dust_blasting"),
                MAUtils.rl(basePath + "crystal_matrix/ingot/from_dust_smelting"));

        RecipeProviderUtil.addSmeltingBlastingRecipes(consumer,
                Ingredient.of(MAItems.BLAZING_DUST),
                ModItems.blaze_cube.get(),
                0.3F, 200,
                MAUtils.rl(basePath + "blaze/ingot/from_dust_blasting"),
                MAUtils.rl(basePath + "blaze/ingot/from_dust_smelting"));

        RecipeProviderUtil.addSmeltingBlastingRecipes(consumer,
                Ingredient.of(MAItems.NEUTRON_DUST),
                ModItems.neutron_ingot.get(),
                0.3F, 200,
                MAUtils.rl(basePath + "neutronium/ingot/from_dust_blasting"),
                MAUtils.rl(basePath + "neutronium/ingot/from_dust_smelting"));

        RecipeProviderUtil.addSmeltingBlastingRecipes(consumer,
                Ingredient.of(MAItems.INFINITY_DUST),
                ModItems.infinity_ingot.get(),
                0.3F, 200,
                MAUtils.rl(basePath + "infinity/ingot/from_dust_blasting"),
                MAUtils.rl(basePath + "infinity/ingot/from_dust_smelting"));
    }
}
