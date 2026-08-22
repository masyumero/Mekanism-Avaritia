package io.github.masyumero.mekavaritia.common.util;

import com.jerry.mekanism_extras.api.ExtraUpgrade;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import mekanism.common.tile.component.TileComponentUpgrade;

public class MAUpgradeUtil {

    public static boolean isCreative(TileComponentUpgrade upgradeComponent) {
        return MAAddons.MEKANISM_EXTRAS.isLoaded() && upgradeComponent.isUpgradeInstalled(ExtraUpgrade.CREATIVE);
    }
}
