package io.github.masyumero.mekavaritia.common.recipe.impl;

import io.github.masyumero.mekavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.registry.MARecipeSerializers;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@NothingNullByDefault
public class ElectricNeutronCollectorIRecipe extends ElectricNeutronCollectorRecipe {
    public ElectricNeutronCollectorIRecipe(ResourceLocation id, ItemStackIngredient input, ItemStack output, FloatingLong energyRequired, int duration) {
        super(id, input, output, energyRequired, duration);
    }

    @Override
    public RecipeType<ElectricNeutronCollectorRecipe> getType() {
        return MARecipeType.ELECTRIC_NEUTRON_COLLECTOR.get();
    }

    @Override
    public RecipeSerializer<ElectricNeutronCollectorRecipe> getSerializer() {
        return MARecipeSerializers.ELECTRIC_NEUTRON_COLLECTOR.get();
    }

    @Override
    public String getGroup() {
        return MABlocks.ELECTRIC_NEUTRON_COLLECTOR.getName();
    }

    @Override
    public ItemStack getToastSymbol() {
        return MABlocks.ELECTRIC_NEUTRON_COLLECTOR.getItemStack();
    }
}