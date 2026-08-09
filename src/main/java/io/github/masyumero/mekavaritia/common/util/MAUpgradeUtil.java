package io.github.masyumero.mekavaritia.common.util;

import mekanism.api.Upgrade;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.content.blocktype.BlockType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MAUpgradeUtil {

    public static void addSupported(BlockType blockType, Upgrade... upgrades) {
        AttributeUpgradeSupport attribute = blockType.get(AttributeUpgradeSupport.class);
        if (attribute == null) {
            return;
        }
        Set<Upgrade> supportedUpgrades = new HashSet<>(attribute.supportedUpgrades());
        supportedUpgrades.addAll(Arrays.asList(upgrades));
        blockType.add(new AttributeUpgradeSupport(supportedUpgrades));
    }
}
