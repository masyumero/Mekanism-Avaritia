package io.github.masyumero.mekavaritia.common.tier.transmitter;

import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import mekanism.common.tier.ConductorTier;

public class MATCTier {
    
    public static double getConduction(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductorConduction.get();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductorConduction.get();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductorConduction.get();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductorConduction.get();
        };
    }

    public static double getHeatCapacity(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductornCapacity.get();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductornCapacity.get();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductornCapacity.get();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductornCapacity.get();
        };
    }

    public static double getConductionInsulation(ConductorTier tier) {
        return switch (tier) {
            case BASIC -> LoadConfig.MA_CONFIG.prismaticThermodynamicConductornInsulation.get();
            case ADVANCED -> LoadConfig.MA_CONFIG.flareThermodynamicConductornInsulation.get();
            case ELITE -> LoadConfig.MA_CONFIG.neuralThermodynamicConductornInsulation.get();
            case ULTIMATE -> LoadConfig.MA_CONFIG.eternalThermodynamicConductornInsulation.get();
        };
    }
}
