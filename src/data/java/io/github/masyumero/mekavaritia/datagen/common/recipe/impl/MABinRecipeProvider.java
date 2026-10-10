package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import java.util.function.Consumer;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.block.basic.MABlockBin;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.SpecialRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;

import mekanism.api.providers.IItemProvider;
import mekanism.common.Mekanism;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismRecipeSerializers;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

class MABinRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern BIN_PATTERN = RecipePattern.createPattern(
            TripleLine.of(Pattern.COBBLESTONE, Pattern.CIRCUIT, Pattern.COBBLESTONE),
            TripleLine.of(Pattern.ALLOY, Pattern.PREVIOUS, Pattern.ALLOY),
            TripleLine.of(Pattern.COBBLESTONE, Pattern.COBBLESTONE, Pattern.COBBLESTONE));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        //Special recipes (bins)
        SpecialRecipeBuilder.build(consumer, MekanismRecipeSerializers.BIN_INSERT);
        SpecialRecipeBuilder.build(consumer, MekanismRecipeSerializers.BIN_EXTRACT);
        //Recipes for making bins
        String basePath = "bin/";
        //Note: For the basic bin, we have to handle the empty slot differently than batching it against our bin pattern
        addTieredBin(consumer, basePath, MABlocks.PRISMATIC_BIN, MekanismBlocks.ULTIMATE_BIN, MATags.Items.PRISMATIC_CONTROL_CIRCUIT, MATags.Items.ALLOYS_CRYSTALLINE);
        addTieredBin(consumer, basePath, MABlocks.FLARE_BIN, MABlocks.PRISMATIC_BIN, MATags.Items.FLARE_CONTROL_CIRCUIT, MATags.Items.ALLOYS_BLAZING);
        addTieredBin(consumer, basePath, MABlocks.NEURAL_BIN, MABlocks.FLARE_BIN, MATags.Items.NEURAL_CONTROL_CIRCUIT, MATags.Items.ALLOYS_NEUTRON);
        addTieredBin(consumer, basePath, MABlocks.ETERNAL_BIN, MABlocks.NEURAL_BIN, MATags.Items.ETERNAL_CONTROL_CIRCUIT, MATags.Items.ALLOYS_INFINITY);
    }

    private void addTieredBin(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<MABlockBin, ?> bin, IItemProvider previousBin, TagKey<Item> circuitTag,
                              TagKey<Item> alloyTag) {
        String tierName = MAAttribute.getMATier(bin.getBlock()).getLowerName();
        MekDataShapedRecipeBuilder.shapedRecipe(bin)
                .pattern(BIN_PATTERN)
                .key(Pattern.PREVIOUS, previousBin)
                .key(Pattern.COBBLESTONE, Tags.Items.COBBLESTONE_NORMAL)
                .key(Pattern.CIRCUIT, circuitTag)
                .key(Pattern.ALLOY, alloyTag)
                .build(consumer, Mekanism.rl(basePath + tierName));
    }
}