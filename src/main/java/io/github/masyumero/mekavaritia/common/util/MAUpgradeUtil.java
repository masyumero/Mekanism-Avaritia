package io.github.masyumero.mekavaritia.common.util;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import dev.lapis256.mekanism_empowered.api.MekEmpUpgrade;
import dev.lapis256.mekanism_empowered.common.init.MekEmpUpgrades;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import mekanism.api.Upgrade;
import mekanism.common.tile.component.TileComponentUpgrade;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public class MAUpgradeUtil {

    public static boolean isCreativeInstalled(TileComponentUpgrade upgradeComponent) {
        return MAAddons.MEKANISM_EXTRAS.isLoaded() && upgradeComponent.isUpgradeInstalled(ExtraUpgrade.CREATIVE);
    }

    public static boolean isStackInstalled(TileComponentUpgrade upgradeComponent) {
        return MAAddons.MEKANISM_EXTRAS.isLoaded() && upgradeComponent.isUpgradeInstalled(ExtraUpgrade.STACK);
    }

    public static boolean isEmpSpeedInstalled(TileComponentUpgrade upgradeComponent) {
        return MAAddons.MEKANISM_EMPOWERED.isLoaded() && upgradeComponent.isUpgradeInstalled(MekEmpUpgrade.getEMPOWERED_SPEED());
    }

    public static boolean isEmpEnergyInstalled(TileComponentUpgrade upgradeComponent) {
        return MAAddons.MEKANISM_EMPOWERED.isLoaded() && upgradeComponent.isUpgradeInstalled(MekEmpUpgrade.getEMPOWERED_ENERGY());
    }

    public static Set<Upgrade> getDefaultUpgrades() {
        var upgrades = EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING);
        if (MAAddons.MEKANISM_EMPOWERED.isLoaded()) {
            upgrades.addAll(Arrays.asList(MekEmpUpgrades.INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES()));
        }
        if (MAAddons.MEKANISM_EXTRAS.isLoaded()) {
            upgrades.add(ExtraUpgrade.STACK);
            upgrades.add(ExtraUpgrade.CREATIVE);
        }
        return upgrades;
    }

    public static Set<Upgrade> getDefaultGasUpgrades() {
        var upgrades = EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS);
        if (MAAddons.MEKANISM_EMPOWERED.isLoaded()) {
            upgrades.addAll(Arrays.asList(MekEmpUpgrades.INSTANCE.getITEM_IN_OUT_MACHINE_UPGRADES()));
        }
        if (MAAddons.MEKANISM_EXTRAS.isLoaded()) {
            upgrades.add(ExtraUpgrade.STACK);
            upgrades.add(ExtraUpgrade.CREATIVE);
        }
        return upgrades;
    }
}
