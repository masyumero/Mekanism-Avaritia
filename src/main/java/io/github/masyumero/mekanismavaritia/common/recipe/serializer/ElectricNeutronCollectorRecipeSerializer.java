package io.github.masyumero.mekanismavaritia.common.recipe.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.masyumero.mekanismavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import mekanism.api.JsonConstants;
import mekanism.api.SerializerHelper;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.Mekanism;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class ElectricNeutronCollectorRecipeSerializer<RECIPE extends ElectricNeutronCollectorRecipe> implements RecipeSerializer<RECIPE> {
    private final ElectricNeutronCollectorRecipeSerializer.IFactory<RECIPE> factory;

    public ElectricNeutronCollectorRecipeSerializer(ElectricNeutronCollectorRecipeSerializer.IFactory<RECIPE> factory) {
        this.factory = factory;
    }

    @NotNull
    @Override
    public RECIPE fromJson(@NotNull ResourceLocation Id, @NotNull JsonObject json) {
        JsonElement input = GsonHelper.isArrayNode(json, JsonConstants.INPUT) ? GsonHelper.getAsJsonArray(json, JsonConstants.INPUT) :
                GsonHelper.getAsJsonObject(json, JsonConstants.INPUT);
        ItemStackIngredient inputIngredient = IngredientCreatorAccess.item().deserialize(input);
        ItemStack output = SerializerHelper.getItemStack(json, JsonConstants.OUTPUT);
        JsonElement ticks = json.get(JsonConstants.DURATION);
        if (output.isEmpty()) {
            throw new JsonSyntaxException("Recipe output must not be empty.");
        }
        int duration = ticks.getAsJsonPrimitive().getAsInt();
        FloatingLong energyRequired = FloatingLong.ZERO;
        if (json.has(JsonConstants.ENERGY_REQUIRED)) {
            energyRequired = SerializerHelper.getFloatingLong(json, JsonConstants.ENERGY_REQUIRED);
        }
        return this.factory.create(Id, inputIngredient, output, energyRequired, duration);
    }

    @Override
    public RECIPE fromNetwork(@NotNull ResourceLocation Id, @NotNull FriendlyByteBuf buffer) {
        try {
            ItemStackIngredient inputIngredient = IngredientCreatorAccess.item().read(buffer);
            ItemStack Output = buffer.readItem();
            FloatingLong energyRequired = FloatingLong.readFromBuffer(buffer);
            int duration = buffer.readVarInt();
            return this.factory.create(Id, inputIngredient, Output, energyRequired, duration);
        } catch (Exception e) {
            Mekanism.logger.error("Error reading itemstack to itemstack recipe from packet.", e);
            throw e;
        }
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buffer, @NotNull RECIPE recipe) {
        try {
            recipe.write(buffer);
        } catch (Exception e) {
            Mekanism.logger.error("Error writing itemstack to itemstack recipe to packet.", e);
            throw e;
        }
    }

    @FunctionalInterface
    public interface IFactory<RECIPE extends ElectricNeutronCollectorRecipe> {

        RECIPE create(ResourceLocation id, ItemStackIngredient input, ItemStack output, FloatingLong energyRequired, int duration);
    }
}
