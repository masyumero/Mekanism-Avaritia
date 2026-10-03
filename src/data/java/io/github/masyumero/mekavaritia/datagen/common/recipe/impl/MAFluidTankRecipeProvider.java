package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.block.basic.MABlockFluidTank;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.api.providers.IItemProvider;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class MAFluidTankRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern FLUID_TANK_PATTERN = RecipePattern.createPattern(
            TripleLine.of(Pattern.ALLOY, Pattern.INGOT, Pattern.ALLOY),
            TripleLine.of(Pattern.INGOT, Pattern.PREVIOUS, Pattern.INGOT),
            TripleLine.of(Pattern.ALLOY, Pattern.INGOT, Pattern.ALLOY));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "fluid_tank/";
        addTieredFluidTank(consumer, basePath, MABlocks.PRISMATIC_FLUID_TANK, MekanismBlocks.ULTIMATE_FLUID_TANK, MATags.Items.ALLOYS_CRYSTALLINE);
        addTieredFluidTank(consumer, basePath, MABlocks.FLARE_FLUID_TANK, MABlocks.PRISMATIC_FLUID_TANK, MATags.Items.ALLOYS_BLAZING);
        addTieredFluidTank(consumer, basePath, MABlocks.NEURAL_FLUID_TANK, MABlocks.FLARE_FLUID_TANK, MATags.Items.ALLOYS_NEUTRON);
        addTieredFluidTank(consumer, basePath, MABlocks.ETERNAL_FLUID_TANK, MABlocks.NEURAL_FLUID_TANK, MATags.Items.ALLOYS_INFINITY);
    }

    private void addTieredFluidTank(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<MABlockFluidTank, ?> tank, IItemProvider previousTank,
                                    TagKey<Item> alloyTag) {
        String tierName = MAAttribute.getMATier(tank.getBlock()).getLowerName();
        MekDataShapedRecipeBuilder.shapedRecipe(tank)
                .pattern(FLUID_TANK_PATTERN)
                .key(Pattern.PREVIOUS, previousTank)
                .key(Pattern.INGOT, Tags.Items.INGOTS_IRON)
                .key(Pattern.ALLOY, alloyTag)
                .build(consumer, MAUtils.rl(basePath + tierName));
    }
}
