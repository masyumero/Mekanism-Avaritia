package io.github.masyumero.mekavaritia.common.content.network.transmitter;

import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import mekanism.api.tier.ITier;
import mekanism.common.upgrade.transmitter.TransmitterUpgradeData;
import org.jetbrains.annotations.NotNull;

public interface IMAUpgradeableTransmitter<DATA extends TransmitterUpgradeData> {

    DATA getUpgradeData();

    boolean dataTypeMatches(@NotNull TransmitterUpgradeData data);

    void parseUpgradeData(@NotNull DATA data);

    ITier getTier();

    default boolean canUpgrade(MAAlloyTier alloyTier) {
        return alloyTier.getMATier().ordinal() == getTier().getBaseTier().ordinal() + 1;
    }
}
