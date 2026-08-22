package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.datagen.common.recipe.BaseRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.ExtendedShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern.TripleLine;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

@NothingNullByDefault
public class MekanismAvaritiaRecipeProvider extends BaseRecipeProvider {

    public MekanismAvaritiaRecipeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void addRecipes(Consumer<FinishedRecipe> consumer) {
        addMiscRecipes(consumer);
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(
                new FactoryRecipeProvider(),
                new ControlCircuitRecipeProvider(),
                new CrusherRecipeProvider(),
                new EnrichingRecipeProvider(),
                new InfusionConversionRecipeProvider(),
                new MetallurgicInfuserRecipeProvider(),
                new ElectricNeutronCollectorRecipeProvider(),
                new SmeltingRecipeProvider(),
                new ExtremeCraftingRecipeProvider(),
                new TransmitterRecipeProvider()
        );
    }

    private void addMiscRecipes(Consumer<FinishedRecipe> consumer) {
        // Module
        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_INFINITY_ENERGY)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.ENERGY, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.ENERGY, MekanismBlocks.CREATIVE_ENERGY_CUBE)
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_INFINITY_ELYTRA)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.INGOT, Pattern.EXTRA_CONSTANT, Pattern.INGOT)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_elytra.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.INGOT, MekanismItems.ANTIMATTER_PELLET)
                .key(Pattern.EXTRA_CONSTANT, MAItems.ENRICHED_CRYSTALLINE)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_INFINITY_EXCAVATION_ESCALATION)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_pickaxe.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_CELESTIAL)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_chest.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_NEBULIGHT)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_helmet.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_STARFEAST)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_pants.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_LIGHTSPEED)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_boots.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_INFINITY_ATTACK_AMPLIFICATION)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.crystal_sword.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_COSMIC_STRIKE)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_sword.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_CELESTIAL_SHOT)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.OTHER, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.HDPE_CHAR, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.OTHER, ModItems.infinity_bow.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);

        ExtendedShapedRecipeBuilder.shapedRecipe(MAItems.MODULE_INFINITY_DAMAGE)
                .pattern(
                        RecipePattern.createPattern(
                                TripleLine.of(Pattern.ALLOY, Pattern.INGOT, Pattern.ALLOY),
                                TripleLine.of(Pattern.ALLOY, Pattern.CONSTANT, Pattern.ALLOY),
                                TripleLine.of(Pattern.HDPE_CHAR, Pattern.EXTRA_CONSTANT, Pattern.HDPE_CHAR)
                        )
                )
                .key(Pattern.ALLOY, MAItems.INFINITY_ALLOY)
                .key(Pattern.INGOT, ModItems.infinity_ingot.get())
                .key(Pattern.CONSTANT, MekanismItems.MODULE_BASE)
                .key(Pattern.EXTRA_CONSTANT, ModItems.infinity_catalyst.get())
                .key(Pattern.HDPE_CHAR, MekanismItems.HDPE_SHEET)
                .build(consumer);
    }
}
