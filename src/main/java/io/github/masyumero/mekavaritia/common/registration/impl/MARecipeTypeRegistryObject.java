package io.github.masyumero.mekavaritia.common.registration.impl;

import io.github.masyumero.mekavaritia.common.recipe.IMARecipeTypeProvider;
import io.github.masyumero.mekavaritia.common.recipe.MARecipeType;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.common.recipe.lookup.cache.IInputRecipeCache;
import mekanism.common.registration.WrappedRegistryObject;
import net.minecraftforge.registries.RegistryObject;

public class MARecipeTypeRegistryObject<RECIPE extends MekanismRecipe, MA_INPUT_CACHE extends IInputRecipeCache> extends
        WrappedRegistryObject<MARecipeType<RECIPE, MA_INPUT_CACHE>> implements IMARecipeTypeProvider<RECIPE, MA_INPUT_CACHE> {

    public MARecipeTypeRegistryObject(RegistryObject<MARecipeType<RECIPE, MA_INPUT_CACHE>> registryObject) {
        super(registryObject);
    }

    @Override
    public MARecipeType<RECIPE, MA_INPUT_CACHE> getMARecipeType() {
        return get();
    }
}