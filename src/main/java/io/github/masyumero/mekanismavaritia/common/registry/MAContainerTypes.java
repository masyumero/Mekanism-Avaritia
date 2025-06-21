package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.common.inventory.container.tile.MAFactoryContainer;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntityMAFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAContainerTypes {
    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityMAFactory<?>>> FACTORY = CONTAINER_TYPES.register("factory", factoryClass(), MAFactoryContainer::new);

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Class<TileEntityMAFactory<?>> factoryClass() {
        return (Class) TileEntityMAFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}