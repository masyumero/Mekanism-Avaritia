package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import mekanism.api.chemical.infuse.InfuseType;
import mekanism.common.registration.impl.InfuseTypeDeferredRegister;
import mekanism.common.registration.impl.InfuseTypeRegistryObject;

public class MAInfuseTypes {

    private MAInfuseTypes() {
    }

    public static final InfuseTypeDeferredRegister INFUSE_TYPES = new InfuseTypeDeferredRegister(MekanismAvaritia.MODID);

    public static final InfuseTypeRegistryObject<InfuseType> CRYSTALLINE = INFUSE_TYPES.register("crystalline", 0x2C2C2C);
    public static final InfuseTypeRegistryObject<InfuseType> BLAZING = INFUSE_TYPES.register("blazing", 0x2C2C2C);
    public static final InfuseTypeRegistryObject<InfuseType> NEUTRON = INFUSE_TYPES.register("neutron", 0x2C2C2C);
    public static final InfuseTypeRegistryObject<InfuseType> INFINITY = INFUSE_TYPES.register("infinity", 0x2C2C2C);
}
