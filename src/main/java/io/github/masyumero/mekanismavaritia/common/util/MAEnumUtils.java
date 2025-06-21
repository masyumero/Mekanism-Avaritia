package io.github.masyumero.mekanismavaritia.common.util;

import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactoryType;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;

public class MAEnumUtils {

    private MAEnumUtils() {
    }

    public static final MAFactoryTier[] MA_FACTORY_TIERS = MAFactoryTier.values();
    public static final MAFactoryType[] MA_FACTORY_TYPES = MAFactoryType.values();
}
