package io.github.masyumero.mekavaritia.datagen.client.models.block;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;

import mekanism.api.tier.ITier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.*;
import mekanism.common.util.EnumUtils;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MekanismAvaritiaBlockModelProvider extends BaseBlockModelsProvider {

    public MekanismAvaritiaBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MekanismAvaritia.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                simpleFactoryMachineBlock(MABlocks.getMAFactory(tier, type));
            }
//            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
//                if (type == AdvancedFactoryType.CENTRIFUGING) {
//                    continue;
//                }
//                simpleAdvancedFactoryMachineBlock(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, type));
//            }
//            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
//                if (type == MoreMachineFactoryType.PLANTING) {
//                    continue;
//                }
//                simpleMoreMachineFactoryMachineBlock(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, type));
//            }
        }

        inductionCellAndProvider(MABlocks.PRISMATIC_INDUCTION_CELL, MABlocks.PRISMATIC_INDUCTION_PROVIDER);
        inductionCellAndProvider(MABlocks.FLARE_INDUCTION_CELL, MABlocks.FLARE_INDUCTION_PROVIDER);
        inductionCellAndProvider(MABlocks.NEURAL_INDUCTION_CELL, MABlocks.NEURAL_INDUCTION_PROVIDER);
        inductionCellAndProvider(MABlocks.ETERNAL_INDUCTION_CELL, MABlocks.ETERNAL_INDUCTION_PROVIDER);
        
        transporter(MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER);
        transporter(MABlocks.FLARE_LOGISTICAL_TRANSPORTER);
        transporter(MABlocks.NEURAL_LOGISTICAL_TRANSPORTER);
        transporter(MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER);

        pipe(MABlocks.PRISMATIC_MECHANICAL_PIPE);
        pipe(MABlocks.FLARE_MECHANICAL_PIPE);
        pipe(MABlocks.NEURAL_MECHANICAL_PIPE);
        pipe(MABlocks.ETERNAL_MECHANICAL_PIPE);

        tube(MABlocks.PRISMATIC_PRESSURIZED_TUBE);
        tube(MABlocks.FLARE_PRESSURIZED_TUBE);
        tube(MABlocks.NEURAL_PRESSURIZED_TUBE);
        tube(MABlocks.ETERNAL_PRESSURIZED_TUBE);

        conductor(MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR);
        conductor(MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR);
        conductor(MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR);
        conductor(MABlocks.ETERNAL_THERMODYNAMIC_CONDUCTOR);

        cable(MABlocks.PRISMATIC_UNIVERSAL_CABLE);
        cable(MABlocks.FLARE_UNIVERSAL_CABLE);
        cable(MABlocks.NEURAL_UNIVERSAL_CABLE);
        cable(MABlocks.ETERNAL_UNIVERSAL_CABLE);
    }

    private void transporter(BlockRegistryObject<?, ?> transmitter) {
        TransporterTier tier = Attribute.getTier(transmitter, TransporterTier.class);
        transmitters(transmitter, "logistical_transporter", tier, Mekanism.rl("block/transmitter/large/logistical_transporter/transporter"), false);
    }

    private void pipe(BlockRegistryObject<?, ?> transmitter) {
        PipeTier tier = Attribute.getTier(transmitter, PipeTier.class);
        transmitters(transmitter, "mechanical_pipe", tier, Mekanism.rl("block/transmitter/large/large"), false);
    }

    private void tube(BlockRegistryObject<?, ?> transmitter) {
        TubeTier tier = Attribute.getTier(transmitter, TubeTier.class);
        smallTransmitter(transmitter, "pressurized_tube", tier);
    }

    private void conductor(BlockRegistryObject<?, ?> transmitter) {
        ConductorTier tier = Attribute.getTier(transmitter, ConductorTier.class);
        smallTransmitter(transmitter, "thermodynamic_conductor", tier);
    }

    private void cable(BlockRegistryObject<?, ?> transmitter) {
        CableTier tier = Attribute.getTier(transmitter, CableTier.class);
        smallTransmitter(transmitter, "universal_cable", tier);
    }

    private void smallTransmitter(BlockRegistryObject<?, ?> transmitter, String type, ITier tier) {
        transmitters(transmitter, type, tier, Mekanism.rl("block/transmitter/small/small"), true);
    }
}
