package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.common.tier.PipeTier;

public class MAPTier {

    public static int getPipePullAmount(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticMechanicalPipePullAmount.get().intValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareMechanicalPipePullAmount.get().intValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralMechanicalPipePullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalMechanicalPipePullAmount.get().intValue();
        };
    }

    public static long getPipeCapacity(PipeTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticMechanicalPipeCapacity.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareMechanicalPipeCapacity.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralMechanicalPipeCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalMechanicalPipeCapacity.get().longValue();
        };
    }
}
