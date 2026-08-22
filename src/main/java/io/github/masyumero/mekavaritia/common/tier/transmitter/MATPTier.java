package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.common.tier.TransporterTier;

public class MATPTier {

    public static int getSpeed(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticLogisticalTransporterSpeed.get().intValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareLogisticalTransporterSpeed.get().intValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralLogisticalTransporterSpeed.get().intValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalLogisticalTransporterSpeed.get().intValue();
        };
    }

    public static int getPullAmount(TransporterTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticLogisticalTransporterPullAmount.get().intValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareLogisticalTransporterPullAmount.get().intValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralLogisticalTransporterPullAmount.get().intValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalLogisticalTransporterPullAmount.get().intValue();
        };
    }
}
