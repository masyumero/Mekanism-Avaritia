package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.api.math.FloatingLong;
import mekanism.common.tier.CableTier;

public class MACTier {

    public static FloatingLong getCapacityAsFloatingLong(CableTier tier) {
        if (tier == null) return FloatingLong.create(8000L);
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticUniversalCableCapacity.get();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareUniversalCableCapacity.get();
            case ELITE -> LoadConfig.MA_CONFIG.neuralUniversalCableCapacity.get();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalUniversalCableCapacity.get();
        };
    }
}