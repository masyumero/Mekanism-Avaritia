package io.github.masyumero.mekavaritia.api.recipes;

import lombok.Getter;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.math.FloatingLong;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@NothingNullByDefault
public abstract class ElectricNeutronCollectorRecipe extends MekanismRecipe implements Predicate<@NotNull ItemStack> {
    private final ResourceLocation id;
    @Getter
    private final ItemStackIngredient input;
    private final ItemStack output;
    @Getter
    private final FloatingLong energyRequired;
    @Getter
    private final int duration;

    public ElectricNeutronCollectorRecipe(ResourceLocation id, ItemStackIngredient input, ItemStack output, FloatingLong energyRequired, int duration) {
        super(id);
        this.id = id;
        this.input = Objects.requireNonNull(input, "Input cannot be null.");
        Objects.requireNonNull(output, "Main output cannot be null.");
        if (output.isEmpty()) {
            throw new IllegalArgumentException("At least one output must not be empty.");
        }
        this.output = output.copy();
        this.energyRequired = Objects.requireNonNull(energyRequired, "Required energy cannot be null.").copyAsConst();
        this.duration = duration;
    }

    @Override
    public boolean test(ItemStack input) {
        return this.input.test(input);
    }

    @Contract(value = "_ -> new", pure = true)
    public ItemStack getOutput(ItemStack input) {
        return output.copy();
    }

    @NotNull
    @Override
    public ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return output.copy();
    }

    public List<ItemStack> getMainOutputDefinition() {
        return Collections.singletonList(output);
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public boolean isIncomplete() {
        return input.hasNoMatchingInstances();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        input.write(buffer);
        buffer.writeItem(output);
        energyRequired.writeToBuffer(buffer);
        buffer.writeVarInt(duration);
    }
}
