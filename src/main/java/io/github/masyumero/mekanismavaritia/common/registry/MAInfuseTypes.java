package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class MAInfuseTypes {

    private MAInfuseTypes() {
    }

    public static final InfuseTypeDeferredRegister INFUSE_TYPES = new InfuseTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final InfuseTypeRegistryObject<InfuseType> CRYSTALLINE = INFUSE_TYPES.register("crystalline", 0x9af3ee);
    public static final InfuseTypeRegistryObject<InfuseType> BLAZING = INFUSE_TYPES.register("blazing", 0xffa938);
    public static final InfuseTypeRegistryObject<InfuseType> NEUTRON = INFUSE_TYPES.register("neutron", 0x161616);
//    public static final InfuseTypeRegistryObject<InfuseType> INFINITY = INFUSE_TYPES.register("infinity", MekanismAvaritia.rl("infuse_type/infinity"),0xffffff);
    public static final InfuseTypeRegistryObject<InfuseType> INFINITY = INFUSE_TYPES.register("infinity",0xECE2EC);

    public static void register(IEventBus eventBus) {
        INFUSE_TYPES.register(eventBus);
    }
}
