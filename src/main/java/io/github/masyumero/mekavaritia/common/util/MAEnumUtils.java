package io.github.masyumero.mekavaritia.common.util;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.tier.*;

public class MAEnumUtils {

    private MAEnumUtils() {
    }

    public static final MAFactoryTier[] MA_FACTORY_TIERS = MAFactoryTier.values();
    public static final MATier[] MA_TIERS = MATier.values();
    public static final MAECTier[] ENERGY_CUBE_TIERS =  MAECTier.values();
    public static final MAFTTier[] FLUID_TANK_TIERS =  MAFTTier.values();
    public static final MACTTier[] CHEMICAL_TANK_TIERS =  MACTTier.values();
    public static final MABTier[] BIN_TIERS =  MABTier.values();
    public static final MAICTier[] INDUCTION_CELL_TIERS =  MAICTier.values();
    public static final MAIPTier[] INDUCTION_PROVIDER_TIERS =  MAIPTier.values();
}
