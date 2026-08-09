package io.github.masyumero.mekavaritia.client.jei;

import io.github.masyumero.mekavaritia.common.recipe.IMARecipeTypeProvider;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

import java.util.List;

public class MARecipeRegistryHelper {

    public static <RECIPE extends MekanismRecipe> void register(IRecipeRegistration registry, MekanismJEIRecipeType<RECIPE> recipeType,
                                                                IMARecipeTypeProvider<RECIPE, ?> type) {
        register(registry, recipeType, type.getRecipes(getWorld()));
    }

    public static <RECIPE> void register(IRecipeRegistration registry, MekanismJEIRecipeType<RECIPE> recipeType, List<RECIPE> recipes) {
        registry.addRecipes(MekanismJEI.recipeType(recipeType), recipes);
    }

    private static ClientLevel getWorld() {
        return Minecraft.getInstance().level;
    }
}
