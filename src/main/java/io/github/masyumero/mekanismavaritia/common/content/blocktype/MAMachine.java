package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeFactoryType;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeUpgradeable;
import io.github.masyumero.mekanismavaritia.common.registry.MABlocks;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import mekanism.api.Upgrade;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.AttributeParticleFX;
import mekanism.common.block.attribute.AttributeStateFacing;
import mekanism.common.block.attribute.AttributeUpgradeSupport;
import mekanism.common.block.attribute.Attributes;
import mekanism.common.content.blocktype.BlockTypeTile;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Supplier;

public class MAMachine <TILE extends TileEntityMekanism> extends BlockTypeTile<TILE> {

    public MAMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, ILangEntry description) {
        super(tileEntityRegistrar, description);
        add(new AttributeParticleFX()
                .add(ParticleTypes.SMOKE, rand -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52))
                .add(DustParticleOptions.REDSTONE, rand -> new Pos3D(rand.nextFloat() * 0.6F - 0.3F, rand.nextFloat() * 6.0F / 16.0F, 0.52)));
        add(Attributes.ACTIVE_LIGHT, new AttributeStateFacing(), Attributes.SECURITY, Attributes.INVENTORY, Attributes.REDSTONE, Attributes.COMPARATOR);
    }

    public static class MAFactoryMachine<TILE extends TileEntityMekanism> extends MAMachine<TILE> {
        public MAFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntitySupplier, MekanismLang description, MAFactoryType factoryType) {
            super(tileEntitySupplier, description);
            add(new AttributeUpgradeSupport(EnumSet.of(Upgrade.SPEED, Upgrade.ENERGY, Upgrade.MUFFLING)));
            add(new MAAttributeFactoryType(factoryType), new MAAttributeUpgradeable(() -> MABlocks.getMAFactory(MAFactoryTier.PRISMATIC, getFactoryType())));
        }

        public MAFactoryType getFactoryType() {
            return Objects.requireNonNull(get(MAAttributeFactoryType.class)).getFactoryType();
        }
    }

    public static class MAMachineBuilder<MACHINE extends MAMachine<TILE>, TILE extends TileEntityMekanism, T extends MAMachineBuilder<MACHINE, TILE, T>> extends BlockTileBuilder<MACHINE, TILE, T> {

        protected MAMachineBuilder(MACHINE holder) {
            super(holder);
        }

        public static <TILE extends TileEntityMekanism> MAMachineBuilder<MAFactoryMachine<TILE>, TILE, ?> createMAFactoryMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                                 MekanismLang description, MAFactoryType factoryType) {
            return new MAMachineBuilder<>(new MAFactoryMachine<>(tileEntityRegistrar, description, factoryType));
        }

        public static <TILE extends TileEntityMekanism> MAMachineBuilder<MAMachine<TILE>, TILE, ?> createMAMachine(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar,
                                                                                                                   ILangEntry description) {
            return new MAMachineBuilder<>(new MAMachine<>(tileEntityRegistrar, description));
        }
    }
}
