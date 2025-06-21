package io.github.masyumero.mekanismavaritia.common.inventory.container.tile;

import io.github.masyumero.mekanismavaritia.common.registry.MAContainerTypes;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntitySawingMAFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import net.minecraft.world.entity.player.Inventory;

public class MAFactoryContainer extends MekanismTileContainer<TileEntityMAFactory<?>> {

    public MAFactoryContainer(int id, Inventory inv, TileEntityMAFactory<?> tile) {
        super(MAContainerTypes.FACTORY, id, inv, tile);
    }

    @Override
    protected int getInventoryYOffset() {
        if (tile.hasSecondaryResourceBar()) {
            return 95;
        } else if (tile instanceof TileEntitySawingMAFactory) {
            return 105;
        }
        return 85;
    }

    @Override
    protected int getInventoryXOffset() {
        return tile.tier.inventoryLabelX;
        //int index = tile.tier.ordinal();
        //return (22 * (index + 2)) - (3 * index);
    }
}