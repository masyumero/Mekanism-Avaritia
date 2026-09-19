package io.github.masyumero.mekavaritia.common.integration.mekmm.registry;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.mekmm.inventory.container.MAMoreMachineFactoryContainer;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAMoreMachineFactory;
import mekanism.common.inventory.container.tile.MekanismTileContainer;
import mekanism.common.registration.impl.ContainerTypeDeferredRegister;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;

import net.minecraftforge.eventbus.api.IEventBus;

public class MAMoreMachineContainerTypes {

    private MAMoreMachineContainerTypes() {}

    public static final ContainerTypeDeferredRegister CONTAINER_TYPES = new ContainerTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final ContainerTypeRegistryObject<MekanismTileContainer<TileEntityMAMoreMachineFactory<?>>> MORE_MACHINE_FACTORY = CONTAINER_TYPES.register("more_machine_factory", factoryClass(), MAMoreMachineFactoryContainer::new);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Class<TileEntityMAMoreMachineFactory<?>> factoryClass() {
        return (Class) TileEntityMAMoreMachineFactory.class;
    }

    public static void register(IEventBus eventBus) {
        CONTAINER_TYPES.register(eventBus);
    }
}