package io.github.masyumero.mekavaritia.datagen.client.models.item;

import committee.nova.mods.avaritia.client.model.loader.base.HaloSetting;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.api.providers.IItemProvider;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.util.EnumUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Map;

public class MAItemModelProvider extends MABaseItemModelProvider {

    private static final List<IItemProvider> MASKED_ITEMS = List.of(
            MAItems.ETERNAL_CONTROL_CIRCUIT,
            MAItems.MODULE_COSMIC_STRIKE,
            MAItems.MODULE_COSMIC
    );

    private static final Map<IItemProvider, Halo> HALO_ITEMS = Map.of(
            MAItems.NEURAL_CONTROL_CIRCUIT, new Halo(MAUtils.avaritia("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            MAItems.NEUTRON_DUST, new Halo(MAUtils.avaritia("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            MAItems.ENRICHED_NEUTRON, new Halo(MAUtils.avaritia("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            MAItems.NEUTRON_ALLOY, new Halo(MAUtils.avaritia("misc/halo_noise"), new HaloSetting(null, "#halo", -1711276033, 6, false)),
            MAItems.ETERNAL_CONTROL_CIRCUIT, new Halo(MAUtils.avaritia("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            MAItems.INFINITY_DUST, new Halo(MAUtils.avaritia("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            MAItems.ENRICHED_INFINITY, new Halo(MAUtils.avaritia("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true)),
            MAItems.INFINITY_ALLOY, new Halo(MAUtils.avaritia("misc/halo"), new HaloSetting(null, "#halo", -16777216, 10, true))
    );

    public MAItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MekanismAvaritia.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        for (IItemProvider item : MAItems.ITEM.getAllItems()) {
            if (MASKED_ITEMS.contains(item)) {
                maskedItem(item);
                continue;
            }
            if (HALO_ITEMS.containsKey(item)) {
                var halo = HALO_ITEMS.get(item);
                hasHaloItem(item, halo.haloLoc, halo.setting);
                continue;
            }
            basicItem(item.asItem());
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

    private record Halo(ResourceLocation haloLoc, HaloSetting setting) {}
}
