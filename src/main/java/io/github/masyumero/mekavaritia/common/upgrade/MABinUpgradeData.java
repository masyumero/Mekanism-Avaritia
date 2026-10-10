package io.github.masyumero.mekavaritia.common.upgrade;

import io.github.masyumero.mekavaritia.common.inventory.slot.MABinInventorySlot;
import mekanism.common.upgrade.IUpgradeData;

public record MABinUpgradeData(boolean redstone, MABinInventorySlot binSlot) implements IUpgradeData {}
