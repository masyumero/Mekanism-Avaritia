package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class CrusherRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "crushing/";
        // CrystalMatrixIngot -> CrystallineDust
        ItemStackToItemStackRecipeBuilder.crushing(
                IngredientCreatorAccess.item().from(ModItems.crystal_matrix_ingot.get()),
                MAItems.CRYSTALLINE_DUST.getItemStack()
        ).build(consumer, MekanismAvaritia.rl(basePath + "crystalline_dust_from_crystal_matrix_ingot"));
        // BlazeCube -> Dust
        ItemStackToItemStackRecipeBuilder.crushing(
                IngredientCreatorAccess.item().from(ModItems.blaze_cube.get()),
                MAItems.BLAZING_DUST.getItemStack()
        ).build(consumer, MekanismAvaritia.rl(basePath + "blazing_dust_from_blaze_cube"));
        // NeutronIngot -> NeutronDust
        ItemStackToItemStackRecipeBuilder.crushing(
                IngredientCreatorAccess.item().from(ModItems.neutron_ingot.get()),
                MAItems.NEUTRON_DUST.getItemStack()
        ).build(consumer, MekanismAvaritia.rl(basePath + "neutron_dust_from_neutron_ingot"));
        // InfinityIngot -> InfiniteDust
        ItemStackToItemStackRecipeBuilder.crushing(
                IngredientCreatorAccess.item().from(ModItems.infinity_ingot.get()),
                MAItems.INFINITY_DUST.getItemStack()
        ).build(consumer, MekanismAvaritia.rl(basePath + "infinity_dust_from_infinity_ingot"));
    }
}
