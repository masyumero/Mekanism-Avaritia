package io.github.masyumero.mekavaritia.client.gui.machine;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.client.gui.element.tab.GuiMASortingTab;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityItemStackGasToItemStackMAFactory;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMetallurgicInfuserMAFactory;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntitySawingMAFactory;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.client.gui.GuiConfigurableTile;
import mekanism.client.gui.element.GuiDumpButton;
import mekanism.client.gui.element.bar.GuiChemicalBar;
import mekanism.client.gui.element.bar.GuiVerticalPowerBar;
import mekanism.client.gui.element.progress.GuiProgress;
import mekanism.client.gui.element.progress.ProgressType;
import mekanism.client.gui.element.tab.GuiEnergyTab;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.inventory.warning.ISupportsWarning;
import mekanism.common.inventory.warning.WarningTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GuiMAFactory extends GuiConfigurableTile<TileEntityMAFactory<?>, MekanismTileContainer<TileEntityMAFactory<?>>> {

    public GuiMAFactory(MekanismTileContainer<TileEntityMAFactory<?>> container, Inventory inv, Component title) {
        super(container, inv, title);
        if (tile.hasSecondaryResourceBar()) {
            imageHeight += 11;
            inventoryLabelY = 85;
        } else if (tile instanceof TileEntitySawingMAFactory) {
            imageHeight += 21;
            inventoryLabelY = 95;
        } else {
            inventoryLabelY = 75;
        }
        imageWidth += tile.tier.imageWidth;
        inventoryLabelX = tile.tier.inventoryLabelX;
        titleLabelY = 4;
        dynamicSlots = true;
    }

    @Override
    protected void addGuiElements() {
        super.addGuiElements();
        addRenderableWidget(new GuiMASortingTab(this, tile));
        addRenderableWidget(new GuiVerticalPowerBar(this, tile.getEnergyContainer(), imageWidth - 12, 16,  52))
                .warning(WarningTracker.WarningType.NOT_ENOUGH_ENERGY, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_ENERGY, 0));
        addRenderableWidget(new GuiEnergyTab(this, tile.getEnergyContainer(), tile::getLastUsage));
        if (tile.hasSecondaryResourceBar()) {
            ISupportsWarning<?> secondaryBar = null;
            if (tile instanceof TileEntityMetallurgicInfuserMAFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getInfusionTank(), tile.getInfusionTanks(null)),
                        7, 76, imageWidth - 38, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, imageWidth - 28, 76));
            } else if (tile instanceof TileEntityItemStackGasToItemStackMAFactory factory) {
                secondaryBar = addRenderableWidget(new GuiChemicalBar<>(this, GuiChemicalBar.getProvider(factory.getGasTank(), tile.getGasTanks(null)),
                        7, 76, imageWidth - 38, 4, true));
                addRenderableWidget(new GuiDumpButton<>(this, factory, imageWidth - 28, 76));
            }
            if (secondaryBar != null) {
                secondaryBar.warning(WarningTracker.WarningType.NO_MATCHING_RECIPE, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.NOT_ENOUGH_SECONDARY_INPUT, 0));
            }
        }

        int baseX = 27;
        int baseXMult = 19;
        for (int i = 0; i < tile.tier.processes; i++) {
            int cacheIndex = i;
            addProgress(new GuiProgress(() -> tile.getScaledProgress(1, cacheIndex), ProgressType.DOWN, this, 4 + baseX + (i * baseXMult), 33))
                    .warning(WarningTracker.WarningType.INPUT_DOESNT_PRODUCE_OUTPUT, tile.getWarningCheck(CachedRecipe.OperationTracker.RecipeError.INPUT_DOESNT_PRODUCE_OUTPUT, cacheIndex));
        }
    }

    private GuiProgress addProgress(GuiProgress progressBar) {
        MekanismJEIRecipeType<?> jeiType = MAUtils.isAlloying(tile.getFactoryType()) ? EMJEI.ALLOYING : switch (tile.getFactoryType()) {
            case SMELTING -> MekanismJEIRecipeType.SMELTING;
            case ENRICHING -> MekanismJEIRecipeType.ENRICHING;
            case CRUSHING -> MekanismJEIRecipeType.CRUSHING;
            case SAWING -> MekanismJEIRecipeType.SAWING;
            case INFUSING -> MekanismJEIRecipeType.METALLURGIC_INFUSING;
            case COMBINING -> MekanismJEIRecipeType.COMBINING;
            case INJECTING -> MekanismJEIRecipeType.INJECTING;
            case PURIFYING -> MekanismJEIRecipeType.PURIFYING;
            case COMPRESSING -> MekanismJEIRecipeType.COMPRESSING;
        };

        return addRenderableWidget(progressBar.jeiCategories(jeiType));
    }

    @Override
    protected void drawForegroundText(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        renderTitleText(guiGraphics);
        drawString(guiGraphics, playerInventoryTitle, inventoryLabelX, inventoryLabelY, titleTextColor());
        super.drawForegroundText(guiGraphics, mouseX, mouseY);
    }
}