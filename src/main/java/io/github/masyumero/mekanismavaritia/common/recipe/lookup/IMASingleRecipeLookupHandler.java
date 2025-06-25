package io.github.masyumero.mekanismavaritia.common.recipe.lookup;

import io.github.masyumero.mekanismavaritia.common.recipe.lookup.cache.MAInputRecipeCache;
import io.github.masyumero.mekanismavaritia.common.recipe.lookup.cache.MASingleInputRecipeCache;
import mekanism.api.chemical.Chemical;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.recipes.MekanismRecipe;
import mekanism.api.recipes.inputs.IInputHandler;
import mekanism.common.util.ChemicalUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public interface IMASingleRecipeLookupHandler<INPUT, RECIPE extends MekanismRecipe & Predicate<INPUT>, INPUT_CACHE extends MASingleInputRecipeCache<INPUT, ?, RECIPE, ?>>
        extends IMARecipeLookupHandler.IMSRecipeTypedLookupHandler<RECIPE, INPUT_CACHE> {
    default boolean containsRecipe(INPUT input) {
        return getMARecipeType().getInputCache().containsInput(getHandlerWorld(), input);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getMARecipeType()}) by looking up the given input against the recipe type's input cache.
     *
     * @param input Recipe input.
     *
     * @return Recipe matching the given input, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(INPUT input) {
        return getMARecipeType().getInputCache().findFirstRecipe(getHandlerWorld(), input);
    }

    /**
     * Finds the first recipe for the type of recipe we handle ({@link #getMARecipeType()}) by looking up the given input against the recipe type's input cache.
     *
     * @param inputHandler Input handler to grab the recipe input from.
     *
     * @return Recipe matching the given input, or {@code null} if no recipe matches.
     */
    @Nullable
    default RECIPE findFirstRecipe(IInputHandler<INPUT> inputHandler) {
        return findFirstRecipe(inputHandler.getInput());
    }

    /**
     * Helper interface to make the generics that we have to pass to {@link IMASingleRecipeLookupHandler} not as messy.
     */
    interface ItemRecipeLookupHandler<RECIPE extends MekanismRecipe & Predicate<ItemStack>> extends IMASingleRecipeLookupHandler<ItemStack, RECIPE, MAInputRecipeCache.SingleItem<RECIPE>> {
    }

    /**
     * Helper interface to make the generics that we have to pass to {@link IMASingleRecipeLookupHandler} not as messy.
     */
    interface FluidRecipeLookupHandler<RECIPE extends MekanismRecipe & Predicate<FluidStack>> extends IMASingleRecipeLookupHandler<FluidStack, RECIPE, MAInputRecipeCache.SingleFluid<RECIPE>> {
    }

    /**
     * Helper interface to make the generics that we have to pass to {@link IMASingleRecipeLookupHandler} not as messy.
     */
    interface ChemicalRecipeLookupHandler<CHEMICAL extends Chemical<CHEMICAL>, STACK extends ChemicalStack<CHEMICAL>, RECIPE extends MekanismRecipe & Predicate<STACK>>
            extends IMASingleRecipeLookupHandler<STACK, RECIPE, MAInputRecipeCache.SingleChemical<CHEMICAL, STACK, RECIPE>> {

        /**
         * Helper wrapper to convert a chemical to a chemical stack and pass it to {@link #containsRecipe(Object)} to make validity predicates easier and cleaner.
         */
        default boolean containsRecipe(CHEMICAL input) {
            return containsRecipe(ChemicalUtil.withAmount(input, 1));
        }
    }
}