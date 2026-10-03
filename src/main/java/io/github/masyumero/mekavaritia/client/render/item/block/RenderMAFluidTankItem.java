package io.github.masyumero.mekavaritia.client.render.item.block;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.masyumero.mekavaritia.client.render.tileentity.RenderMAFluidTank;
import io.github.masyumero.mekavaritia.common.item.block.machine.MAItemBlockFluidTank;
import io.github.masyumero.mekavaritia.common.tier.MAFTTier;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.RenderResizableCuboid;
import mekanism.client.render.item.MekanismISTER;
import mekanism.common.util.StorageUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class RenderMAFluidTankItem extends MekanismISTER {

    public static final RenderMAFluidTankItem RENDERER = new RenderMAFluidTankItem();

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        // Note: We don't need to register this as a reload listener as we don't have an in code model or make use of
        // this
        // reload in any way
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer,
                             int light, int overlayLight) {
        MAFTTier tier = ((MAItemBlockFluidTank) stack.getItem()).getMATier();
        FluidStack fluid = StorageUtils.getStoredFluidFromNBT(stack);
        if (!fluid.isEmpty()) {
            float fluidScale = (float) fluid.getAmount() / tier.getStorage();
            if (fluidScale > 0) {
                MekanismRenderer.renderObject(RenderMAFluidTank.getFluidModel(fluid, fluidScale), matrix, renderer.getBuffer(Sheets.translucentCullBlockSheet()),
                        MekanismRenderer.getColorARGB(fluid, fluidScale), MekanismRenderer.calculateGlowLight(light, fluid), overlayLight, RenderResizableCuboid.FaceDisplay.FRONT, getCamera());
            }
        }
        renderBlockItem(stack, displayContext, matrix, renderer, light, overlayLight, ModelData.EMPTY);
    }
}