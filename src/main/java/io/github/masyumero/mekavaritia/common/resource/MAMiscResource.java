package io.github.masyumero.mekavaritia.common.resource;

import mekanism.common.resource.IResource;

public enum MAMiscResource implements IResource {
    CRYSTALLINE("crystalline"),
    BLAZING("blazing"),
    NEUTRON("neutron"),
    INFINITY("infinity");

    private final String registrySuffix;

    MAMiscResource(String registrySuffix) {
        this.registrySuffix = registrySuffix;
    }

    @Override
    public String getRegistrySuffix() {
        return registrySuffix;
    }
}
