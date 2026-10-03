package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.api.providers.IItemProvider;
import mekanism.common.Mekanism;
import mekanism.common.block.interfaces.ITypeBlock;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class MAChemicalTankRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern CHEMICAL_TANK_PATTERN = RecipePattern.createPattern(
            TripleLine.of(Pattern.ALLOY, Pattern.OSMIUM, Pattern.ALLOY),
            TripleLine.of(Pattern.OSMIUM, Pattern.PREVIOUS, Pattern.OSMIUM),
            TripleLine.of(Pattern.ALLOY, Pattern.OSMIUM, Pattern.ALLOY));

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "chemical_tank/";
        addTieredChemicalTank(consumer, basePath, MABlocks.PRISMATIC_CHEMICAL_TANK, MekanismBlocks.ULTIMATE_CHEMICAL_TANK, MATags.Items.ALLOYS_CRYSTALLINE);
        addTieredChemicalTank(consumer, basePath, MABlocks.FLARE_CHEMICAL_TANK, MABlocks.PRISMATIC_CHEMICAL_TANK, MATags.Items.ALLOYS_BLAZING);
        addTieredChemicalTank(consumer, basePath, MABlocks.NEURAL_CHEMICAL_TANK, MABlocks.FLARE_CHEMICAL_TANK, MATags.Items.ALLOYS_NEUTRON);
        addTieredChemicalTank(consumer, basePath, MABlocks.ETERNAL_CHEMICAL_TANK, MABlocks.NEURAL_CHEMICAL_TANK, MATags.Items.ALLOYS_INFINITY);
    }

    private void addTieredChemicalTank(Consumer<FinishedRecipe> consumer, String basePath, BlockRegistryObject<? extends ITypeBlock, ?> tank, IItemProvider previousTank,
                                       TagKey<Item> alloyTag) {
        String tierName = MAAttribute.getMATier(tank.getBlock()).getLowerName();
        MekDataShapedRecipeBuilder.shapedRecipe(tank)
                .pattern(CHEMICAL_TANK_PATTERN)
                .key(Pattern.PREVIOUS, previousTank)
                .key(Pattern.OSMIUM, MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.INGOT, PrimaryResource.OSMIUM))
                .key(Pattern.ALLOY, alloyTag)
                .build(consumer, Mekanism.rl(basePath + tierName));
    }
}
