package io.github.masyumero.mekavaritia.client.jei.machine;

import io.github.masyumero.mekavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import mekanism.api.providers.IBlockProvider;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.client.jei.BaseRecipeCategory;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.slot.SlotOverlay;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import org.jetbrains.annotations.NotNull;

public class ElectricNeutronCollectorRecipeCategory extends BaseRecipeCategory<ElectricNeutronCollectorRecipe> {
    private final GuiSlot input;
    private final GuiSlot output;

    public ElectricNeutronCollectorRecipeCategory(IGuiHelper helper, MekanismJEIRecipeType<ElectricNeutronCollectorRecipe> recipeType, IBlockProvider mekanismBlock) {
        super(helper, recipeType, mekanismBlock, 3, 3, 170, 79);
        input = addSlot(SlotType.INPUT, 48, 35);
        output = addSlot(SlotType.OUTPUT, 126, 35);
        addSlot(SlotType.POWER, 17, 35).with(SlotOverlay.POWER);
        addElement(new GuiVerticalPowerBar(this, FULL_BAR, 37, 15));
        addSimpleProgress(ProgressType.LARGE_RIGHT, 71, 38);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, ElectricNeutronCollectorRecipe recipe, @NotNull IFocusGroup focusGroup) {
        initItem(builder, RecipeIngredientRole.INPUT, input, recipe.getInput().getRepresentations());
        initItem(builder, RecipeIngredientRole.OUTPUT, output, recipe.getMainOutputDefinition());
    }
}