package io.github.masyumero.mekavaritia.client.render;

import io.github.masyumero.mekavaritia.client.render.item.block.RenderMAFluidTankItem;
import mekanism.client.render.RenderPropertiesProvider;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class RenderMAPropertiesProvider {

    private RenderMAPropertiesProvider() {}

    //public static IClientItemExtensions maEnergyCube() {
    //    return new RenderPropertiesProvider.MekRenderProperties(RenderMAEnergyCubeItem.RENDERER);
    //}

    public static IClientItemExtensions maFluidTank() {
        return new RenderPropertiesProvider.MekRenderProperties(RenderMAFluidTankItem.RENDERER);
    }
}
