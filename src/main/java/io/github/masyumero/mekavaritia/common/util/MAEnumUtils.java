package io.github.masyumero.mekavaritia.common.util;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;

public class MAEnumUtils {

    private MAEnumUtils() {
    }

    public static final MAFactoryTier[] MA_FACTORY_TIERS = MAFactoryTier.values();
    public static final MATier[] MA_TIERS = MATier.values();
}
