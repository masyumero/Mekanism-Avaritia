package io.github.masyumero.mekavaritia.common.recipe;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekavaritia.common.recipe.lookup.cache.MAInputRecipeCache;
import io.github.masyumero.mekavaritia.common.registration.MARecipeTypeDeferredRegister;
import io.github.masyumero.mekavaritia.common.registration.impl.MARecipeTypeRegistryObject;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.client.MekanismClient;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MARecipeType<RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> implements RecipeType<RECIPE>,
        IMARecipeTypeProvider<RECIPE, INPUT_CACHE> {
    public static final MARecipeTypeDeferredRegister RECIPE_TYPES = new MARecipeTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final MARecipeTypeRegistryObject<ElectricNeutronCollectorRecipe, MAInputRecipeCache.SingleItem<ElectricNeutronCollectorRecipe>> ELECTRIC_NEUTRON_COLLECTOR =
            register("electric_neutron_collector", recipeType -> new MAInputRecipeCache.SingleItem<>(recipeType, ElectricNeutronCollectorRecipe::getInput));

    public static <RECIPE extends MekanismRecipe, INPUT_CACHE extends IInputRecipeCache> MARecipeTypeRegistryObject<RECIPE, INPUT_CACHE> register(String name,
                                                                                                                                                  Function<MARecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        return RECIPE_TYPES.register(name, () -> new MARecipeType<>(name, inputCacheCreator));
    }

    public static void clearCache() {
        for (IMARecipeTypeProvider<?, ?> recipeTypeProvider : RECIPE_TYPES.getAllRecipeTypes()) {
            recipeTypeProvider.getMARecipeType().clearCaches();
        }
    }

    private List<RECIPE> cachedRecipes = Collections.emptyList();
    private final ResourceLocation registryName;
    private final INPUT_CACHE inputCache;

    private MARecipeType(String name, Function<MARecipeType<RECIPE, INPUT_CACHE>, INPUT_CACHE> inputCacheCreator) {
        this.registryName = MekanismAvaritia.rl(name);
        this.inputCache = inputCacheCreator.apply(this);
    }

    @Override
    public String toString() {
        return registryName.toString();
    }

    @Override
    public ResourceLocation getRegistryName() {
        return registryName;
    }

    @Override
    public MARecipeType<RECIPE, INPUT_CACHE> getMARecipeType() {
        return this;
    }

    private void clearCaches() {
        cachedRecipes = Collections.emptyList();
        inputCache.clear();
    }

    @Override
    public INPUT_CACHE getInputCache() {
        return inputCache;
    }

    @NotNull
    @Override
    public List<RECIPE> getRecipes(@Nullable Level world) {
        if (world == null) {
            if (FMLEnvironment.dist.isClient()) {
                world = MekanismClient.tryGetClientWorld();
            } else {
                world = ServerLifecycleHooks.getCurrentServer().overworld();
            }
            if (world == null) {
                return Collections.emptyList();
            }
        }
        if (cachedRecipes.isEmpty()) {
            RecipeManager recipeManager = world.getRecipeManager();
            List<RECIPE> recipes = recipeManager.getAllRecipesFor(this);
            cachedRecipes = recipes.stream()
                    .filter(recipe -> !recipe.isIncomplete())
                    .toList();
        }
        return cachedRecipes;
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static <C extends Container, RECIPE_TYPE extends Recipe<C>> Optional<RECIPE_TYPE> getRecipeFor(RecipeType<RECIPE_TYPE> recipeType, C inventory, Level level) {
        return level.getRecipeManager().getRecipeFor(recipeType, inventory, level)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }

    /**
     * Helper for getting a recipe from a world's recipe manager.
     */
    public static Optional<? extends Recipe<?>> byKey(Level level, ResourceLocation id) {
        return level.getRecipeManager().byKey(id)
                .filter(recipe -> recipe.isSpecial() || !recipe.isIncomplete());
    }

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }
}