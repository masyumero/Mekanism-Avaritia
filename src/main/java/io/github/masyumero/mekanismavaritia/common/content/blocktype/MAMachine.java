package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeFactoryType;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeUpgradeable;
import io.github.masyumero.mekanismavaritia.common.registry.MABlock;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import mekanism.api.Upgrade;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.content.blocktype.Machine;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraftforge.fml.ModList;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class MAMachine {

    public static class MAFactoryMachine<TILE extends TileEntityMekanism> extends Machine<TILE> {
        public MAFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, MAFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING)));
            add(new MAAttributeFactoryType(factoryType), new MAAttributeUpgradeable(() -> MABlock.getMAFactory(MAFactoryTier.PRISMATIC, getFactoryType())));
        }

        public MAFactoryType getFactoryType() {
            return Objects.requireNonNull(get(MAAttributeFactoryType.class)).getFactoryType();
        }
    }

    public static class MAMachineBuilder<MACHINE extends Machine<TILE>, TILE extends TileEntityMekanism, T extends MAMachineBuilder<MACHINE, TILE, T>> extends BlockTypeTile.BlockTileBuilder<MACHINE, TILE, T> {

        protected MAMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> MAMachineBuilder<MAFactoryMachine<TILE>, TILE, ?> createMAFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                 MekanismLang description, MAFactoryType factoryType) {
            return new MAMachineBuilder<>(new MAFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }
    }

}
