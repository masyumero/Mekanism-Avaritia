package io.github.masyumero.mekanismavaritia.common.integration;

import dev.lapis256.mekanism_empowered.core.common.util.AdditionalUpgradeUtil;
import io.github.masyumero.mekanismavaritia.common.content.blocktype.MAFactoryType;
import io.github.masyumero.mekanismavaritia.common.registry.MABlockTypes;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekanismavaritia.common.util.MAEnumUtils;
import mekanism.api.Upgrade;

import static dev.lapis256.mekanism_empowered.common.init.MekEmpUpgrades.INSTANCE;

public class MekEmp {

    public static void registerSupportedUpgrades() {
        registerFactoryUpgrades(MAFactoryType.ENRICHING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.CRUSHING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.SMELTING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.SAWING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.COMPRESSING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.COMBINING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.INFUSING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.PURIFYING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
        registerFactoryUpgrades(MAFactoryType.INJECTING, INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES());
    }

    private static void registerFactoryUpgrades(MAFactoryType type, Upgrade... upgrade) {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            AdditionalUpgradeUtil.addSupported(MABlockTypes.getMAFactory(tier, type), upgrade);
        }
    }

}
