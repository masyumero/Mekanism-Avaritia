package io.github.masyumero.mekavaritia.common.registry;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekavaritia.common.recipe.impl.ElectricNeutronCollectorIRecipe;
import io.github.masyumero.mekavaritia.common.recipe.serializer.ElectricNeutronCollectorRecipeSerializer;
import mekanism.common.registration.impl.RecipeSerializerDeferredRegister;
import mekanism.common.registration.impl.RecipeSerializerRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class MARecipeSerializers {

    private MARecipeSerializers() {
    }

    public static final RecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new RecipeSerializerDeferredRegister(MekanismAvaritia.MODID);

    public static final RecipeSerializerRegistryObject<ElectricNeutronCollectorRecipe> ELECTRIC_NEUTRON_COLLECTOR = RECIPE_SERIALIZERS.register("electric_neutron_collector", () -> new ElectricNeutronCollectorRecipeSerializer<>(ElectricNeutronCollectorIRecipe::new));

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
