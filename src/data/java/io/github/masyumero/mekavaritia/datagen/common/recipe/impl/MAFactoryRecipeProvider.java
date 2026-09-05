package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tier.FactoryTier;
import mekanism.common.util.EnumUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;
import java.util.function.Function;

public class MAFactoryRecipeProvider implements ISubRecipeProvider {

    private static final RecipePattern FACTORY_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY),
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.PREVIOUS, Pattern.CONSTANT),
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY)
    );

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "factory/";
        factory(basePath, consumer, MAFactoryTier.PRISMATIC, MATags.Items.ALLOYS_PRISMATIC, MATags.Items.PRISMATIC_CONTROL_CIRCUIT, ModItems.crystal_matrix_ingot.get(), factoryType -> MekanismBlocks.getFactory(FactoryTier.ULTIMATE, factoryType));
        factory(basePath, consumer, MAFactoryTier.FLARE, MATags.Items.ALLOYS_FLARE, MATags.Items.FLARE_CONTROL_CIRCUIT, ModItems.blaze_cube.get(), factoryType -> MABlocks.getMAFactory(MAFactoryTier.PRISMATIC, factoryType));
        factory(basePath, consumer, MAFactoryTier.NEURAL, MATags.Items.ALLOYS_NEURAL, MATags.Items.NEURAL_CONTROL_CIRCUIT, ModItems.neutron_ingot.get(), factoryType -> MABlocks.getMAFactory(MAFactoryTier.FLARE, factoryType));
        factory(basePath, consumer, MAFactoryTier.ETERNAL, MATags.Items.ALLOYS_ETERNAL, MATags.Items.ETERNAL_CONTROL_CIRCUIT, ModItems.infinity_catalyst.get(), factoryType -> MABlocks.getMAFactory(MAFactoryTier.NEURAL, factoryType));
    }

    private void factory(String path, Consumer<FinishedRecipe> consumer, MAFactoryTier tier,
                         TagKey<Item> alloy, TagKey<Item> circuit, Item constant, Function<FactoryType, BlockRegistryObject<?, ?>> previous) {
        for (FactoryType type : EnumUtils.FACTORY_TYPES) {
            var factory = MABlocks.getMAFactory(tier, type);
            MekDataShapedRecipeBuilder.shapedRecipe(factory).pattern(FACTORY_PATTERN)
                    .key(Pattern.ALLOY, alloy)
                    .key(Pattern.CIRCUIT, circuit)
                    .key(Pattern.CONSTANT, constant)
                    .key(Pattern.PREVIOUS, previous.apply(type))
                    .build(consumer, MAUtils.rl(path + tier.getMATier().getLowerName() + "/" + type.getRegistryNameComponent()));
        }
    }
}