package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekanismavaritia.common.recipe.impl.ElectricNeutronCollectorIRecipe;
import io.github.masyumero.mekanismavaritia.common.recipe.serializer.ElectricNeutronCollectorRecipeSerializer;
import mekanism.common.registration.impl.RecipeSerializerDeferredRegister;
import mekanism.common.registration.impl.RecipeSerializerRegistryObject;

public class MARecipeSerializers {

    private MARecipeSerializers() {
    }

    public static final RecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new RecipeSerializerDeferredRegister(MekanismAvaritia.MODID);

    public static final RecipeSerializerRegistryObject<ElectricNeutronCollectorRecipe> ELECTRIC_NEUTRON_COLLECTOR = RECIPE_SERIALIZERS.register("electric_neutron_collector", () -> new ElectricNeutronCollectorRecipeSerializer<>(ElectricNeutronCollectorIRecipe::new));

}
