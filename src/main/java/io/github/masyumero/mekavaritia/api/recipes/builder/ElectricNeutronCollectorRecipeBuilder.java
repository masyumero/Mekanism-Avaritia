package io.github.masyumero.mekavaritia.api.recipes.builder;

import com.google.gson.JsonObject;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import mekanism.api.JsonConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.datagen.recipe.MekanismRecipeBuilder;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

@NothingNullByDefault
public class ElectricNeutronCollectorRecipeBuilder extends MekanismRecipeBuilder<ElectricNeutronCollectorRecipeBuilder> {
    private final ItemStackIngredient input;
    private final ItemStack mainOutput;
    private final FloatingLong energyRequired;
    private final int duration;

    protected ElectricNeutronCollectorRecipeBuilder(ItemStackIngredient input, ItemStack mainOutput, FloatingLong energyRequired, int duration) {
        super(MekanismAvaritia.rl("electric_neutron_collector"));
        this.input = input;
        this.mainOutput = mainOutput;
        this.energyRequired = energyRequired;
        this.duration = duration;
    }

    public static ElectricNeutronCollectorRecipeBuilder neutronCollector(ItemStackIngredient input, ItemStack mainOutput, FloatingLong energyRequired, int duration) {
        if (mainOutput.isEmpty()) {
            throw new IllegalArgumentException("This electric neutron collector recipe requires a non empty item output.");
        } else {
            return new ElectricNeutronCollectorRecipeBuilder(input, mainOutput, energyRequired, duration);
        }
    }

    @Override
    protected MekanismRecipeBuilder<ElectricNeutronCollectorRecipeBuilder>.RecipeResult getResult(ResourceLocation resourceLocation) {
        return new ElectricNeutronCollectorRecipeResult(resourceLocation);
    }

    public void build(Consumer<FinishedRecipe> consumer) {
        build(consumer, mainOutput.getItem());
    }

    private class ElectricNeutronCollectorRecipeResult extends MekanismRecipeBuilder<ElectricNeutronCollectorRecipeBuilder>.RecipeResult {
        public ElectricNeutronCollectorRecipeResult(ResourceLocation resourceLocation) {
            super(resourceLocation);
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add(JsonConstants.INPUT, input.serialize());
            json.addProperty(JsonConstants.DURATION, duration);
            json.addProperty(JsonConstants.ENERGY_REQUIRED, energyRequired);
            json.add(JsonConstants.OUTPUT, SerializerHelper.serializeItemStack(mainOutput));
        }
    }
}
