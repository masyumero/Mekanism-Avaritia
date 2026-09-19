package io.github.masyumero.mekavaritia.common.integration.mekaf.content.blocktype;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryBlockShapes;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttributeUpgradeable;
import io.github.masyumero.mekavaritia.common.content.blocktype.MAMachine.MAFactoryMachine;
import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryBlockTypes;
import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryBlocks;
import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryContainerTypes;
import io.github.masyumero.mekavaritia.common.integration.mekaf.tile.factory.base.TileEntityMAAdvancedFactoryBase;
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

public class MAAdvancedFactory<TILE extends TileEntityMAAdvancedFactoryBase<?>> extends MAFactoryMachine<TILE> {

    private final MAFactoryMachine<?> origMachine;

    public MAAdvancedFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                                MAFactoryMachine<?> origMachine, MAFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getAdvancedFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new MAAttributeTier<>(tier));

        if (tier.ordinal() < MAEnumUtils.MA_FACTORY_TIERS.length - 1) {
            add(new MAAttributeUpgradeable(() -> MAAdvancedFactoryBlocks.getMAAdvancedFactory(MAEnumUtils.MA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getAdvancedFactoryType())));
        }
    }

    private void setMachineData(MAFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, AttributeAdvancedFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        if (origEnergy != null) {
            add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().max(origEnergy.getUsage()).multiply(tier.processes)));
        }
    }

    public static class MAAdvancedFactoryBuilder<FACTORY extends MAAdvancedFactory<TILE>, TILE extends TileEntityMAAdvancedFactoryBase<?>, T extends MAMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected MAAdvancedFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityMAAdvancedFactoryBase<?>> MAAdvancedFactoryBuilder<MAAdvancedFactory<TILE>, TILE, ?> createAdvancedFactory(Supplier<?> tileEntityRegistrar,
                                                                                                                                                                  AdvancedFactoryType type, MAFactoryTier tier) {
            MAAdvancedFactoryBuilder<MAAdvancedFactory<TILE>, TILE, ?> builder = getMAAdvancedFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(AdvancedFactoryBlockShapes.getShape(type));
            if (type == AdvancedFactoryType.CENTRIFUGING) {
                builder.withBounding((pos, state, builderPos) -> builderPos.add(pos.above()));
            }
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F)));
            return builder;
        }
    }

    private static <TILE extends TileEntityMAAdvancedFactoryBase<?>> @NotNull MAAdvancedFactoryBuilder<MAAdvancedFactory<TILE>, TILE, ?> getMAAdvancedFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, AdvancedFactoryType type, MAFactoryTier tier) {
        MAAdvancedFactoryBuilder<MAAdvancedFactory<TILE>, TILE, ?> builder = new MAAdvancedFactoryBuilder<>(new MAAdvancedFactory<>(tileEntityRegistrar,
                () -> MAAdvancedFactoryContainerTypes.ADVANCED_FACTORY,
                switch (type) {
                    case OXIDIZING -> MAAdvancedFactoryBlockTypes.CHEMICAL_OXIDIZER;
                    case DISSOLVING -> MAAdvancedFactoryBlockTypes.CHEMICAL_DISSOLUTION_CHAMBER;
                    case WASHING -> MAAdvancedFactoryBlockTypes.CHEMICAL_WASHER;
                    case CRYSTALLIZING -> MAAdvancedFactoryBlockTypes.CHEMICAL_CRYSTALLIZER;
                    case PRESSURISED_REACTING -> MAAdvancedFactoryBlockTypes.PRESSURIZED_REACTION_CHAMBER;
                    case CENTRIFUGING -> MAAdvancedFactoryBlockTypes.ISOTOPIC_CENTRIFUGE;
                    case LIQUIFYING -> MAAdvancedFactoryBlockTypes.NUTRITIONAL_LIQUIFIER;
                    case PIGMENT_EXTRACTING -> MAAdvancedFactoryBlockTypes.PIGMENT_EXTRACTOR;
                    case PAINTING -> MAAdvancedFactoryBlockTypes.PAINTING_MACHINE;
                },
                tier));
        builder.withComputerSupport(tier.getMATier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}