package io.github.masyumero.mekavaritia.datagen.client.models.block;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.common.content.blocktype.FactoryType;
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
    }
//
//    private void transporter(BlockRegistryObject<?, ?> transmitter) {
//        TransporterTier tier = Attribute.getTier(transmitter, TransporterTier.class);
//        transmitters(transmitter, "logistical_transporter", tier, Mekanism.rl("block/transmitter/large/logistical_transporter/transporter"), false);
//    }
//
//    private void pipe(BlockRegistryObject<?, ?> transmitter) {
//        PipeTier tier = Attribute.getTier(transmitter, PipeTier.class);
//        transmitters(transmitter, "mechanical_pipe", tier, Mekanism.rl("block/transmitter/large/large"), false);
//    }
//
//    private void tube(BlockRegistryObject<?, ?> transmitter) {
//        TubeTier tier = Attribute.getTier(transmitter, TubeTier.class);
//        smallTransmitter(transmitter, "pressurized_tube", tier);
//    }
//
//    private void conductor(BlockRegistryObject<?, ?> transmitter) {
//        ConductorTier tier = Attribute.getTier(transmitter, ConductorTier.class);
//        smallTransmitter(transmitter, "thermodynamic_conductor", tier);
//    }
//
//    private void cable(BlockRegistryObject<?, ?> transmitter) {
//        CableTier tier = Attribute.getTier(transmitter, CableTier.class);
//        smallTransmitter(transmitter, "universal_cable", tier);
//    }
//
//    private void smallTransmitter(BlockRegistryObject<?, ?> transmitter, String type, ITier tier) {
//        transmitters(transmitter, type, tier, Mekanism.rl("block/transmitter/small/small"), true);
//    }
}
