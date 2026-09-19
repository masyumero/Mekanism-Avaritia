package io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.mekaf.inventory.container.MAAdvancedFactoryContainer;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;

import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class MAAdvancedFactoryContainerTypes {

    private MAAdvancedFactoryContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityMAAdvancedFactoryBase<?>>> ADVANCED_FACTORY = CONTAINER_TYPES.register("advanced_factory", factoryClass(), MAAdvancedFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityMAAdvancedFactoryBase<?>> factoryClass() {
        return (Class) TileEntityMAAdvancedFactoryBase.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}