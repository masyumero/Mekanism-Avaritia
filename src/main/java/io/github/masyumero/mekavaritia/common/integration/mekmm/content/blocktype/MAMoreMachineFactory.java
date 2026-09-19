package io.github.masyumero.mekavaritia.common.integration.mekmm.content.blocktype;

import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineBlockShapes;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeUpgradeable;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAFactoryMachine;
import io.github.masyumero.mekavaritia.common.integration.mekmm.registry.MAMoreMachineBlockTypes;
import io.github.masyumero.mekavaritia.common.integration.mekmm.registry.MAMoreMachineBlocks;
import io.github.masyumero.mekavaritia.common.integration.mekmm.registry.MAMoreMachineContainerTypes;
import io.github.masyumero.mekavaritia.common.integration.mekmm.tile.TileEntityMAMoreMachineFactory;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MAMoreMachineFactory<TILE extends TileEntityMAMoreMachineFactory<?>> extends MAFactoryMachine<TILE> {

    private final MAFactoryMachine<?> origMachine;

    public MAMoreMachineFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                   MAFactoryMachine<?> origMachine, MAFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getMoreMachineFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new MAAttributeTier<>(tier));

        // 添加升级后的方块
        if (tier.ordinal() < MAEnumUtils.MA_FACTORY_TIERS.length - 1) {
            add(new MAAttributeUpgradeable(() -> MAMoreMachineBlocks.getMAMoreMachineFactory(MAEnumUtils.MA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getMoreMachineFactoryType())));
        }
    }

    private void setMachineData(MAFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeMoreMachineFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().multiply(0.5).max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class MAMoreMachineFactoryBuilder<FACTORY extends MAMoreMachineFactory<TILE>, TILE extends TileEntityMAMoreMachineFactory<?>, T extends MAMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected MAMoreMachineFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityMAMoreMachineFactory<?>> MAMoreMachineFactoryBuilder<MAMoreMachineFactory<TILE>, TILE, ?> createMAMoreMachineFactory(Supplier<?> tileEntityRegistrar, MoreMachineFactoryType type,
                                                                                                                                                                               MAFactoryTier tier) {
            MAMoreMachineFactoryBuilder<MAMoreMachineFactory<TILE>, TILE, ?> builder = getMAMoreMachineFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(MoreMachineBlockShapes.getShape(type));
            if (type == MoreMachineFactoryType.PLANTING) {
                builder.withBounding((pos, state, builderPos) -> builderPos.add(pos.above()));
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityMAMoreMachineFactory<?>> @NotNull MAMoreMachineFactoryBuilder<MAMoreMachineFactory<TILE>, TILE, ?> getMAMoreMachineFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, MoreMachineFactoryType type, MAFactoryTier tier) {
        MAMoreMachineFactoryBuilder<MAMoreMachineFactory<TILE>, TILE, ?> builder = new MAMoreMachineFactoryBuilder<>(new MAMoreMachineFactory<>(tileEntityRegistrar,
                () -> MAMoreMachineContainerTypes.MORE_MACHINE_FACTORY,
                switch (type) {
                    case RECYCLING -> MAMoreMachineBlockTypes.RECYCLER;
                    case PLANTING -> MAMoreMachineBlockTypes.PLANTING_STATION;
                    case CNC_STAMPING -> MAMoreMachineBlockTypes.CNC_STAMPER;
                    case CNC_LATHING -> MAMoreMachineBlockTypes.CNC_LATHE;
                    case CNC_ROLLING_MILL -> MAMoreMachineBlockTypes.CNC_ROLLING_MILL;
                    case REPLICATING -> MAMoreMachineBlockTypes.REPLICATOR;
                },
                tier));
        builder.withComputerSupport(tier.getMATier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}
