package io.github.masyumero.mekavaritia.client.gui.element.tab;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.network.to_server.MAPacketGuiInteract;
import io.github.masyumero.mekavaritia.common.tile.factory.TileEntityMAFactory;
import mekanism.client.SpecialColors;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiInsetElement;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.MekanismLang;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.text.BooleanStateDisplay;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;

public class GuiMASortingTab extends GuiInsetElement<TileEntityMAFactory<?>> {

    public GuiMASortingTab(IGuiWrapper gui, TileEntityMAFactory<?> tile) {
        super(MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "sorting.png"), gui, tile, -26, 62, 35, 18, true);
    }

    @Override
    public void drawBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.drawBackground(guiGraphics, mouseX, mouseY, partialTicks);
        drawTextScaledBound(guiGraphics, BooleanStateDisplay.OnOff.of(dataSource.isSorting()).getTextComponent(), relativeX + 3, relativeY + 24, titleTextColor(), 21);
    }

    @Override
    public void renderToolTip(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderToolTip(guiGraphics, mouseX, mouseY);
        displayTooltips(guiGraphics, mouseX, mouseY, MekanismLang.AUTO_SORT.translate());
    }

    @Override
    protected void colorTab(GuiGraphics guiGraphics) {
        MekanismRenderer.color(guiGraphics, SpecialColors.TAB_FACTORY_SORT);
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        MekanismAvaritia.packetHandler().sendToServer(new MAPacketGuiInteract(MAPacketGuiInteract.MAGuiInteraction.AUTO_SORT_BUTTON, dataSource));
    }
}
