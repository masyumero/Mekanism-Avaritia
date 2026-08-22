package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.common.tier.ConductorTier;

public class MATCTier {
    
    public static long getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductorConduction.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductorConduction.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductorConduction.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductorConduction.get().longValue();
        };
    }

    public static long getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductornCapacity.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductornCapacity.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductornCapacity.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductornCapacity.get().longValue();
        };
    }

    public static long getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductornInsulation.get().longValue();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductornInsulation.get().longValue();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductornInsulation.get().longValue();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductornInsulation.get().longValue();
        };
    }
}
