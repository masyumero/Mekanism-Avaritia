package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import committee.nova.mods.avaritia.init.registry.ModItems;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.recipes.builder.ElectricNeutronCollectorRecipeBuilder;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ElectricNeutronCollectorRecipeProvider implements ISubRecipeProvider {
    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        var basePath = "electric_neutron_collector/";
        ElectricNeutronCollectorRecipeBuilder.neutronCollector(
                IngredientCreatorAccess.item().from(MATags.Items.PRISMATIC_CONTROL_CIRCUIT),
                new ItemStack(ModItems.neutron_pile.get()),
                FloatingLong.create(125),
                40
        ).build(consumer, MekanismAvaritia.rl(basePath + "prismatic/neutron_pile"));

        ElectricNeutronCollectorRecipeBuilder.neutronCollector(
                IngredientCreatorAccess.item().from(MATags.Items.FLARE_CONTROL_CIRCUIT),
                new ItemStack(ModItems.neutron_pile.get()),
                FloatingLong.create(250),
                20
        ).build(consumer, MekanismAvaritia.rl(basePath + "flare/neutron_pile"));

        ElectricNeutronCollectorRecipeBuilder.neutronCollector(
                IngredientCreatorAccess.item().from(MATags.Items.NEURAL_CONTROL_CIRCUIT),
                new ItemStack(ModItems.neutron_pile.get()),
                FloatingLong.create(500),
                10
        ).build(consumer, MekanismAvaritia.rl(basePath + "neural/neutron_pile"));

        ElectricNeutronCollectorRecipeBuilder.neutronCollector(
                IngredientCreatorAccess.item().from(MATags.Items.ETERNAL_CONTROL_CIRCUIT),
                new ItemStack(ModItems.neutron_pile.get()),
                FloatingLong.create(1000),
                1
        ).build(consumer, MekanismAvaritia.rl(basePath + "eternal/neutron_pile"));
    }
}
