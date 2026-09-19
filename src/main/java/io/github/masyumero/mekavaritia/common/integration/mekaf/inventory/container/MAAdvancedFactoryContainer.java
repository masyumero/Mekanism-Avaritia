package io.github.masyumero.mekavaritia.common.integration.mekaf.inventory.container;

import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryContainerTypes;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.TileEntityMAPressurizedReactingFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAGasToGasFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAItemToItemAdvancedFactory;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMASlurryToSlurryFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;

import net.minecraft.world.entity.player.Inventory;

import org.jetbrains.annotations.NotNull;

public class MAAdvancedFactoryContainer extends MekanismTileContainer<TileEntityMAAdvancedFactoryBase<?>> {

    public MAAdvancedFactoryContainer(int id, Inventory inv, @NotNull TileEntityMAAdvancedFactoryBase<?> tile) {
        super(MAAdvancedFactoryContainerTypes.ADVANCED_FACTORY, id, inv, tile);
    }

    protected int getInventoryYOffset() {
        if (tile.hasExtrasResourceBar()) {
            if (tile instanceof TileEntityMAGasToGasFactory<?> || tile instanceof TileEntityMASlurryToSlurryFactory<?>) {
                return 121;
            }
            if (tile instanceof TileEntityMAItemToItemAdvancedFactory<?>) {
                return 95;
            } else {
                return tile instanceof TileEntityMAPressurizedReactingFactory ? 103 : 108;
            }
        }
        if (tile instanceof TileEntityMAGasToGasFactory<?> || tile instanceof TileEntityMASlurryToSlurryFactory<?>) {
            return 112;
        } else {
            return 98;
        }
    }

    protected int getInventoryXOffset() {
        int index = this.tile.tier.ordinal();
        return 22 * (index + 2) - 3 * index;
    }
}
