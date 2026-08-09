package io.github.masyumero.mekavaritia.api.tier;

import lombok.Getter;

public enum MAAlloyTier implements IMATier {
    CRYSTALLINE("crystalline", MATier.PRISMATIC),
    BLAZING("blazing", MATier.FLARE),
    NEUTRON("neutron", MATier.NEURAL),
    INFINITY("infinity", MATier.ETERNAL);

    private final MATier maTier;
    @Getter
    private final String name;

    MAAlloyTier(String name, MATier base) {
        maTier = base;
        this.name = name;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }
}