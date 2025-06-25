package io.github.masyumero.mekanismavaritia.common.registration;

import io.github.masyumero.mekanismavaritia.common.recipe.IMARecipeTypeProvider;
import io.github.masyumero.mekanismavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekanismavaritia.common.registration.impl.MARecipeTypeRegistryObject;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedDeferredRegister;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MARecipeTypeDeferredRegister extends WrappedDeferredRegister<RecipeType<?>> {
    private final List<IMARecipeTypeProvider<?, ?>> recipeTypes = new ArrayList<>();

    public MARecipeTypeDeferredRegister(String modid) {
        super(modid, ForgeRegistries.RECIPE_TYPES);
    }

    public <RECIPE extends MekanismRecipe, MA_INPUT_CACHE extends IInputRecipeCache> MARecipeTypeRegistryObject<RECIPE, MA_INPUT_CACHE> register(String name,
                                                                                                                                                 Supplier<? extends MARecipeType<RECIPE, MA_INPUT_CACHE>> sup) {
        MARecipeTypeRegistryObject<RECIPE, MA_INPUT_CACHE> registeredRecipeType = register(name, sup, MARecipeTypeRegistryObject::new);
        recipeTypes.add(registeredRecipeType);
        return registeredRecipeType;
    }

    public List<IMARecipeTypeProvider<?, ?>> getAllRecipeTypes() {
        return Collections.unmodifiableList(recipeTypes);
    }
}