package io.github.masyumero.mekavaritia.datagen.common.recipe.builder;

import committee.nova.mods.avaritia.init.registry.ModRecipeSerializers;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;
import net.minecraft.world.level.ItemLike;

public class ExtremeCraftingRecipeBuilder extends ExtendedShapedRecipeBuilder {

    protected ExtremeCraftingRecipeBuilder(ItemLike result, int count) {
        super(ModRecipeSerializers.SHAPED_CRAFT_SERIALIZER.get(), result, count);
    }

    public static ExtremeCraftingRecipeBuilder shapedRecipe(ItemLike result) {
        return shapedRecipe(result, 1);
    }

    public static ExtremeCraftingRecipeBuilder shapedRecipe(ItemLike result, int count) {
        return new ExtremeCraftingRecipeBuilder(result, count);
    }

    @Override
    public ExtremeCraftingRecipeBuilder pattern(RecipePattern pattern) {
        if (!this.pattern.isEmpty()) {
            throw new IllegalArgumentException("Recipe pattern has already been set!");
        }
        this.pattern.add(pattern.row1);
        if (pattern.row2 != null) {
            this.pattern.add(pattern.row2);
            if (pattern.row3 != null) {
                this.pattern.add(pattern.row3);
                if (pattern.row4 != null) {
                    this.pattern.add(pattern.row4);
                    if (pattern.row5 != null) {
                        this.pattern.add(pattern.row5);
                        if (pattern.row6 != null) {
                            this.pattern.add(pattern.row6);
                            if (pattern.row7 != null) {
                                this.pattern.add(pattern.row7);
                                if (pattern.row8 != null) {
                                    this.pattern.add(pattern.row8);
                                    if (pattern.row9 != null) {
                                        this.pattern.add(pattern.row9);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return this;
    }
}
