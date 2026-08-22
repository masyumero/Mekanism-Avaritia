package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.common.tier.TubeTier;

public class MATTier {

    public static long getTubePullAmount(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticPressurizedTubePullAmount.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flarePressurizedTubePullAmount.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralPressurizedTubePullAmount.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalPressurizedTubePullAmount.get().longValue();
        };
    }

    public static long getTubeCapacity(TubeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticPressurizedTubeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flarePressurizedTubeCapacity.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralPressurizedTubeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalPressurizedTubeCapacity.get().longValue();
        };
    }
}
