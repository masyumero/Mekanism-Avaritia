package io.github.masyumero.mekavaritia.common.integration.mekmm.registry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jerry.mekanism_extras.api.ExtraUpgrade;
import com.jerry.mekmm.common.MoreMachineLang;
import com.jerry.mekmm.common.config.MoreMachineConfig;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineContainerTypes;
import com.jerry.mekmm.common.registries.MoreMachineTileEntityTypes;
import com.jerry.mekmm.common.tile.machine.*;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAFactoryMachine;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAMachineBuilder;
import io.github.masyumero.mekavaritia.common.integration.mekmm.content.blocktype.MAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.Upgrade;
import mekanism.common.registries.MekanismSounds;

import java.util.EnumSet;

public class MAMoreMachineBlockTypes {

    private MAMoreMachineBlockTypes() {}

    private static final Table<MAFactoryTier, MoreMachineFactoryType, MAMoreMachineFactory<?>> FACTORIES = HashBasedTable.create();

    // Recycler
    public static final MAFactoryMachine<TileEntityRecycler> RECYCLER = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.RECYCLER, MoreMachineLang.DESCRIPTION_RECYCLER, MoreMachineFactoryType.RECYCLING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.recycler, MoreMachineConfig.storage.recycler)
            .build();
    // Planting Station
    public static final MAFactoryMachine<TileEntityPlantingStation> PLANTING_STATION = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.PLANTING_STATION, MoreMachineLang.DESCRIPTION_PLANTING_STATION, MoreMachineFactoryType.PLANTING)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.plantingStation, MoreMachineConfig.storage.plantingStation)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, Upgrade.GAS, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .withBounding((pos, state, builder) -> builder.add(pos.above()))
            .build();
    // CNC Stamper
    public static final MAFactoryMachine<TileEntityStamper> CNC_STAMPER = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_STAMPER, MoreMachineLang.DESCRIPTION_CNC_STAMPER, MoreMachineFactoryType.CNC_STAMPING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_stamper, MoreMachineConfig.storage.cnc_stamper)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Lathe
    public static final MAFactoryMachine<TileEntityLathe> CNC_LATHE = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_LATHE, MoreMachineLang.DESCRIPTION_CNC_LATHE, MoreMachineFactoryType.CNC_LATHING)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_lathe, MoreMachineConfig.storage.cnc_lathe)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // CNC Rolling Mill
    public static final MAFactoryMachine<TileEntityRollingMill> CNC_ROLLING_MILL = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.CNC_ROLLING_MILL, MoreMachineLang.DESCRIPTION_CNC_ROLLING_MILL, MoreMachineFactoryType.CNC_ROLLING_MILL)
            .withSound(MekanismSounds.CRUSHER)
            .withEnergyConfig(MoreMachineConfig.usage.cnc_rollingMill, MoreMachineConfig.storage.cnc_rollingMill)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();
    // Replicator
    public static final MAFactoryMachine<TileEntityReplicator> REPLICATOR = MAMachineBuilder
            .createMAMoreMachineFactoryMachine(() -> MoreMachineTileEntityTypes.REPLICATOR, MoreMachineLang.DESCRIPTION_REPLICATOR, MoreMachineFactoryType.REPLICATING)
            .withGui(() -> MoreMachineContainerTypes.REPLICATOR)
            .withSound(MekanismSounds.PRECISION_SAWMILL)
            .withEnergyConfig(MoreMachineConfig.usage.itemReplicator, MoreMachineConfig.storage.itemReplicator)
            .withSupportedUpgrades(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING, ExtraUpgrade.STACK, ExtraUpgrade.CREATIVE))
            .build();

    static {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
                FACTORIES.put(tier, type, MAMoreMachineFactory.MAMoreMachineFactoryBuilder.createMAMoreMachineFactory(() -> MAMoreMachineTileEntityTypes.getMAMoreMachineFactoryTile(tier, type), type, tier).build());
            }
        }
    }

    public static MAMoreMachineFactory<?> getMAMoreMachineFactory(MAFactoryTier tier, MoreMachineFactoryType type) {
        return FACTORIES.get(tier, type);
    }
}