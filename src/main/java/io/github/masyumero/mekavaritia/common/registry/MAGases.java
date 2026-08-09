package io.github.masyumero.mekavaritia.common.registry;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import mekanism.common.registration.impl.GasDeferredRegister;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAGases {

    private MAGases() {
    }

    public static final GasDeferredRegister GASES = new GasDeferredRegister(MekanismAvaritia.MODID);

    public static void register(IEventBus eventBus) {
        GASES.register(eventBus);
    }
}
