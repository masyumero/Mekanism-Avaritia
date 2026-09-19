package io.github.masyumero.mekavaritia.common.integration.mekmm.inventory.container;

import io.github.masyumero.mekavaritia.common.integration.mekmm.registry.MAMoreMachineContainerTypes;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAPlantingFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class MAMoreMachineFactoryContainer extends MekanismTileContainer<TileEntityMAMoreMachineFactory<?>> {

    public MAMoreMachineFactoryContainer(int id, Inventory inv, @NotNull TileEntityMAMoreMachineFactory<?> tile) {
        super(MAMoreMachineContainerTypes.MORE_MACHINE_FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return tile instanceof TileEntityMAPlantingFactory ? 115 : 95;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        int index = tile.tier.ordinal();
        return (22 * (index + 2)) - (3 * index);
    }
}
