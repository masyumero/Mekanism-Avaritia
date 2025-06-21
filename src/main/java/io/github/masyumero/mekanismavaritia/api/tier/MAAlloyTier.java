package io.github.masyumero.mekanismavaritia.api.tier;

public enum MAAlloyTier implements IMATier {
    CRYSTALLINE("crystalline", MATier.PRISMATIC),
    BLAZING("blazing", MATier.FLARE),
    NEUTRON("neutron", MATier.NEURAL),
    INFINITE("infinite", MATier.ETERNAL);

    private final MATier maTier;
    private final String name;

    MAAlloyTier(String name, MATier base) {
        maTier = base;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public MATier getMATier() {
        return maTier;
    }
}