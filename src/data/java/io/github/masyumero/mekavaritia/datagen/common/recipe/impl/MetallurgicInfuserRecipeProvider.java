package io.github.masyumero.mekavaritia.datagen.common.recipe.impl;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.recipe.condition.ConfigEnabledCondition;
import io.github.masyumero.mekavaritia.common.registry.MAInfuseTypes;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.ISubRecipeProvider;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

import java.util.function.Consumer;

public class MetallurgicInfuserRecipeProvider implements ISubRecipeProvider {

    @Override
    public void addRecipes(Consumer<FinishedRecipe> consumer) {
        String basePath = "metallurgic_infusing/";
        addMetallurgicInfuserAlloyRecipes(consumer, basePath + "alloy/");
    }

    private void addMetallurgicInfuserAlloyRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        // Crystalline
        ItemStackChemicalToItemStackRecipeBuilder.metallurgicInfusing(
                IngredientCreatorAccess.item().from(MekanismTags.Items.ALLOYS_ATOMIC),
                IngredientCreatorAccess.infusion().from(MAInfuseTypes.CRYSTALLINE, 40),
                MAItems.CRYSTALLINE_ALLOY.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "crystalline"));
        // Blazing
        ItemStackChemicalToItemStackRecipeBuilder.metallurgicInfusing(
                IngredientCreatorAccess.item().from(MATags.Items.ALLOYS_CRYSTALLINE),
                IngredientCreatorAccess.infusion().from(MAInfuseTypes.BLAZING, 80),
                MAItems.BLAZING_ALLOY.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "blazing"));
        // Neutron
        ItemStackChemicalToItemStackRecipeBuilder.metallurgicInfusing(
                IngredientCreatorAccess.item().from(MATags.Items.ALLOYS_BLAZING),
                IngredientCreatorAccess.infusion().from(MAInfuseTypes.NEUTRON, 120),
                MAItems.NEUTRON_ALLOY.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "neutron"));
        // Infinity
        ItemStackChemicalToItemStackRecipeBuilder.metallurgicInfusing(
                IngredientCreatorAccess.item().from(MATags.Items.ALLOYS_NEUTRON),
                IngredientCreatorAccess.infusion().from(MAInfuseTypes.INFINITY, 160),
                MAItems.INFINITY_ALLOY.getItemStack()
        ).build(consumer, MAUtils.rl(basePath + "infinity"));
        // Creative
        ItemStackChemicalToItemStackRecipeBuilder.metallurgicInfusing(
                IngredientCreatorAccess.item().from(MATags.Items.ALLOYS_INFINITY),
                IngredientCreatorAccess.infusion().from(MAInfuseTypes.INFINITY, 320),
                EMItems.CREATIVE_ALLOY.getItemStack()
        ).addCondition(new AndCondition(new ModLoadedCondition(EvolvedMekanism.MODID),
                        new NotCondition(ConfigEnabledCondition.HARD_CREATIVE_ALLOY_RECIPE))
                ).build(consumer, MAUtils.rl(basePath + "creative"));
    }
}
