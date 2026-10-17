package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import java.util.function.Consumer;

import committee.nova.mods.avaritia.init.registry.ModItems;
import committee.nova.mods.avaritia.init.registry.ModTags;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.block.MABlockEnergyCube;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.registries.MABlocks;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;

import mekanism.api.providers.IItemProvider;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class MAEnergyCubeRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern ENERGY_CUBE_PATTERN = RecipePattern.createPattern(
            TripleLine.of(Pattern.ALLOY, Pattern.ENERGY, Pattern.ALLOY),
            TripleLine.of(Pattern.INGOT, Pattern.PREVIOUS, Pattern.INGOT),
            TripleLine.of(Pattern.ALLOY, Pattern.ENERGY, Pattern.ALLOY));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "energy_cube/";
        addTieredEnergyCube(consumer, basePath, MABlocks.PRISMATIC_ENERGY_CUBE, MekanismBlocks.ULTIMATE_ENERGY_CUBE, Ingredient.of(ModItems.crystal_matrix_ingot.get()), MATags.Items.ALLOYS_CRYSTALLINE);
        addTieredEnergyCube(consumer, basePath, MABlocks.FLARE_ENERGY_CUBE, MABlocks.PRISMATIC_ENERGY_CUBE, Ingredient.of(ModItems.blaze_cube.get()), MATags.Items.ALLOYS_BLAZING);
        addTieredEnergyCube(consumer, basePath, MABlocks.NEURAL_ENERGY_CUBE, MABlocks.FLARE_ENERGY_CUBE, Ingredient.of(ModTags.NEUTRON_INGOT), MATags.Items.ALLOYS_NEUTRON);
        addTieredEnergyCube(consumer, basePath, MABlocks.ETERNAL_ENERGY_CUBE, MABlocks.NEURAL_ENERGY_CUBE, Ingredient.of(ModItems.infinity_catalyst.get()), MATags.Items.ALLOYS_INFINITY);
    }

    private void addTieredEnergyCube(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<MABlockEnergyCube, ?> energyCube,
                                     IItemProvider previousEnergyCube, Ingredient ingot, TagKey<Item> alloyTag) {
        String tierName = MAAttribute.getMATier(energyCube.getBlock()).getLowerName();
        MekDataShapedRecipeBuilder.shapedRecipe(energyCube)
                .pattern(ENERGY_CUBE_PATTERN)
                .key(Pattern.PREVIOUS, previousEnergyCube)
                .key(Pattern.ENERGY, MekanismItems.ENERGY_TABLET)
                .key(Pattern.INGOT, ingot)
                .key(Pattern.ALLOY, alloyTag)
                .build(consumer, Mekanism.rl(basePath + tierName));
    }
}