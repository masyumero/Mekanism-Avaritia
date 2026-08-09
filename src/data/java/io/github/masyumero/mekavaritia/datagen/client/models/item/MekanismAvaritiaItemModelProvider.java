package io.github.masyumero.mekavaritia.datagen.client.models.item;

import committee.nova.mods.avaritia.Const;
import committee.nova.mods.avaritia.client.model.loader.base.HaloSetting;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.providers.IItemProvider;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.util.EnumUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public class MekanismAvaritiaItemModelProvider extends BaseItemModelProvider {

    private static final List<IItemProvider> MASKED_ITEMS = List.of(
            MAItems.ETERNAL_CONTROL_CIRCUIT,
            MAItems.MODULE_COSMIC_STRIKE,
            MAItems.MODULE_COSMIC
    );

    private static final List<HaloItem> HALO_ITEMS = List.of(
            new HaloItem(MAItems.NEURAL_CONTROL_CIRCUIT, Const.rl("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            new HaloItem(MAItems.NEUTRON_DUST, Const.rl("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            new HaloItem(MAItems.ENRICHED_NEUTRON, Const.rl("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            new HaloItem(MAItems.NEUTRON_ALLOY, Const.rl("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            new HaloItem(MAItems.ETERNAL_CONTROL_CIRCUIT, Const.rl("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            new HaloItem(MAItems.INFINITY_DUST, Const.rl("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            new HaloItem(MAItems.ENRICHED_INFINITY, Const.rl("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            new HaloItem(MAItems.INFINITY_ALLOY, Const.rl("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true))
    );

    public MekanismAvaritiaItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MekanismAvaritia.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        for (IItemProvider item : MAItems.ITEM.getAllItems()) {
            if (MASKED_ITEMS.contains(item)) {
                maskedItem(item);
            } else {
                for (HaloItem haloItem : HALO_ITEMS) {
                    if (haloItem.item == item) {
                        hasHaloItem(item, haloItem.halo, haloItem.setting);
                    }
                }
                basicItem(item.asItem());
            }
        }
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            for (FactoryType type : EnumUtils.FACTORY_TYPES) {
                factoryBlock(MABlocks.getMAFactory(tier, type));
            }
//            for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
//                if (type == AdvancedFactoryType.CENTRIFUGING) {
//                    doubleLayeredBlock(advancedFactoryBlock(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, type)));
//                }
//                advancedFactoryBlock(MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, type));
//            }
//            for (MoreMachineFactoryType type : MoreMachineEnumUtils.MM_FACTORY_TYPES) {
//                if (type == MoreMachineFactoryType.PLANTING) {
//                    doubleLayeredBlock(moreMachineFactoryBlock(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, type)));
//                }
//                moreMachineFactoryBlock(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, type));
//            }
        }
    }

    private record HaloItem(IItemProvider item, ResourceLocation halo, HaloSetting setting) {}
}
