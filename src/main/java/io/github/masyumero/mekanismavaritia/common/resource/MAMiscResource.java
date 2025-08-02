package io.github.masyumero.mekanismavaritia.common.resource;

import mekanism.common.resource.IResource;

public enum MAMiscResource implements IResource {
    PRISMATIC("prismatic"),
    FLARE("flare"),
    NEURAL("neural"),
    ETERNAL("eternal");

    private final String registrySuffix;

    MAMiscResource(String registrySuffix) {
        this.registrySuffix = registrySuffix;
    }

    @Override
    public String getRegistrySuffix() {
        return registrySuffix;
    }
}
