package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeFactoryType;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeTier;
import io.github.masyumero.mekanismavaritia.common.block.attribute.MAAttributeUpgradeable;
import io.github.masyumero.mekanismavaritia.common.registry.MABlockTypes;
import io.github.masyumero.mekanismavaritia.common.registry.MABlocks;
import io.github.masyumero.mekanismavaritia.common.registry.MAContainerTypes;
import io.github.masyumero.mekanismavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekanismavaritia.common.tile.factory.TileEntityMAFactory;
import io.github.masyumero.mekanismavaritia.common.util.MAEnumUtils;
import mekanism.common.MekanismLang;
import mekanism.common.block.attribute.*;
import mekanism.common.inventory.container.MekanismContainer;
import mekanism.common.lib.math.Pos3D;
import mekanism.common.registration.impl.ContainerTypeRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class MAFactory<TILE extends TileEntityMAFactory<?>> extends MAMachine.MAFactoryMachine<TILE> {

    private final MAMachine.MAFactoryMachine<?> origMachine;

    public MAFactory(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, Supplier<ContainerTypeRegistryObject<? extends MekanismContainer>> containerRegistrar,
                     MAMachine.MAFactoryMachine<?> origMachine, MAFactoryTier tier) {
        super(tileEntityRegistrar, MekanismLang.DESCRIPTION_FACTORY, origMachine.getFactoryType());
        this.origMachine = origMachine;
        setMachineData(tier);
        add(new AttributeGui(containerRegistrar, null), new MAAttributeTier<>(tier));

        if (tier.ordinal() < MAEnumUtils.MA_FACTORY_TIERS.length - 1) {
            add(new MAAttributeUpgradeable(() -> MABlocks.getMAFactory(MAEnumUtils.MA_FACTORY_TIERS[tier.ordinal() + 1], origMachine.getFactoryType())));
        }
    }

    private void setMachineData(MAFactoryTier tier) {
        setFrom(origMachine, AttributeSound.class, MAAttributeFactoryType.class, AttributeUpgradeSupport.class);
        AttributeEnergy origEnergy = origMachine.get(AttributeEnergy.class);
        add(new AttributeEnergy(origEnergy::getUsage, () -> origEnergy.getConfigStorage().multiply(0.5).max(origEnergy.getUsage()).multiply(tier.processes)));
    }

    public static class MAFactoryBuilder<FACTORY extends MAFactory<TILE>, TILE extends TileEntityMAFactory<?>, T extends MAMachine.MAMachineBuilder<FACTORY, TILE, T>>
            extends BlockTileBuilder<FACTORY, TILE, T> {

        protected MAFactoryBuilder(FACTORY holder) {
            super(holder);
        }

        @SuppressWarnings("unchecked")
        public static <TILE extends TileEntityMAFactory<?>> MAFactoryBuilder<MAFactory<TILE>, TILE, ?> createFactory(Supplier<?> tileEntityRegistrar, MAFactoryType type,
                                                                                                                               MAFactoryTier tier) {

            MAFactoryBuilder<MAFactory<TILE>, TILE, ?> builder = getEMFactoryTILEEMFactoryBuilder((Supplier<TileEntityTypeRegistryObject<TILE>>) tileEntityRegistrar, type, tier);
            builder.withCustomShape(MABlockShapes.getShape(null, type));
//            builder.with(switch (type) {
//                case SMELTING, ENRICHING, CRUSHING, COMBINING, SAWING -> AttributeSideConfig.ELECTRIC_MACHINE;
//                case COMPRESSING, INJECTING, PURIFYING, INFUSING -> AttributeSideConfig.ADVANCED_ELECTRIC_MACHINE;
//            });
            builder.replace(new AttributeParticleFX().addDense(ParticleTypes.SMOKE, 5, rand -> new Pos3D(
                    rand.nextFloat() * 0.7F - 0.3F,
                    rand.nextFloat() * 0.1F + 0.7F,
                    rand.nextFloat() * 0.7F - 0.3F
            )));
            return builder;
        }
    }

    private static <TILE extends TileEntityMAFactory<?>> @NotNull MAFactoryBuilder<MAFactory<TILE>, TILE, ?> getEMFactoryTILEEMFactoryBuilder(Supplier<TileEntityTypeRegistryObject<TILE>> tileEntityRegistrar, MAFactoryType type, MAFactoryTier tier) {

        MAFactoryBuilder<MAFactory<TILE>, TILE, ?> builder = new MAFactoryBuilder<>(new MAFactory<>(tileEntityRegistrar,
                () -> MAContainerTypes.FACTORY,
                switch (type) {
                    case ALLOYING -> MABlockTypes.ALLOYER;
                    case SMELTING -> MABlockTypes.ENERGIZED_SMELTER;
                    case ENRICHING -> MABlockTypes.ENRICHMENT_CHAMBER;
                    case CRUSHING -> MABlockTypes.CRUSHER;
                    case SAWING -> MABlockTypes.PRECISION_SAWMILL;
                    case INFUSING -> MABlockTypes.METALLURGIC_INFUSER;
                    case COMBINING -> MABlockTypes.COMBINER;
                    case INJECTING -> MABlockTypes.CHEMICAL_INJECTION_CHAMBER;
                    case PURIFYING -> MABlockTypes.PURIFICATION_CHAMBER;
                    case COMPRESSING -> MABlockTypes.OSMIUM_COMPRESSOR;
                },
                tier)
        );
        //Note, we can't just return the builder here as then it gets all confused about object types, so we just
        // assign the value here, and then return the builder itself as it is the same object
        builder.withComputerSupport(tier.getMATier().getLowerName() + type.getRegistryNameComponentCapitalized() + "Factory");
        return builder;
    }
}