package io.github.masyumero.mekavaritia.datagen.client.lang;

import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MATextUtils;
import mekanism.api.providers.IItemProvider;
import mekanism.common.item.ItemModule;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

import java.util.List;

public class MekanismAvaritiaLangProvider extends BaseLanguageProvider {

    private static final List<Item> SWAP_WORD_ITEMS = List.of(
            MAItems.CRYSTALLINE_ALLOY.asItem(),
            MAItems.BLAZING_ALLOY.asItem(),
            MAItems.NEUTRON_ALLOY.asItem(),
            MAItems.INFINITY_ALLOY.asItem(),
            MAItems.CRYSTALLINE_DUST.asItem(),
            MAItems.BLAZING_DUST.asItem(),
            MAItems.NEUTRON_DUST.asItem(),
            MAItems.INFINITY_DUST.asItem()
    );

    public MekanismAvaritiaLangProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        addBlocks();
        addItem();
        addMisc();

        LANGS.forEach(this::add);
    }

    private void addBlocks() {
        MABlocks.BLOCK.getAllBlocks().forEach(this::addENAny);
        //MAAdvancedFactoryBlocks.BLOCKS.getAllBlocks().forEach(this::addENAny);
        //MAMoreMachineBlocks.BLOCKS.getAllBlocks().forEach(this::addENAny);
    }

    private void addItem() {
        for (IItemProvider item : MAItems.ITEM.getAllItems()) {
            if (item.asItem() instanceof ItemModule) {
                continue;
            }
            if (SWAP_WORD_ITEMS.contains(item.asItem())) {
                addENAny(item, MATextUtils.swapWordsToEnglishName(item.getName()));
            } else {
                addENAny(item);
            }
        }
    }

    private void addMisc() {
        add(MekanismAvaritiaLang.TAB, "Mekanism Avaritia");
        add(MekanismAvaritiaLang.ANTIMATTER_PELLET_SINGULARITY, "Antimatter Pellet");
        add(MekanismAvaritiaLang.CRYSTALLINE_ALLOY_SINGULARITY, "Crystalline Alloy");
        add(MekanismAvaritiaLang.DESCRIPTION_ELECTRIC_NEUTRON_COLLECTOR, "Electric Neutron Collector");
        add(MekanismAvaritiaLang.DESCRIPTION_ELECTRIC_SINGULARITY_COMPRESSOR, "Electric Singularity Compressor");
        add(MAModules.INFINITY_ENERGY_UNIT.getModuleData(), "Provides infinite energy");
        add(MAModules.COSMIC_UNIT.getModuleData(), "Grants the power of the cosmos");
        add(MAModules.INFINITY_ELYTRA_UNIT.getModuleData(), "Applies an Infinity Elytra to the MekaSuit.");
        add(MAModules.INFINITY_EXCAVATION_ESCALATION_UNIT.getModuleData(), "Increases digging speed on any block.");
        add(MAModules.CELESTIAL_UNIT.getModuleData(), "Applies an Infinity Armor to the MekaSuit.");
        add(MAModules.NEBULIGHT_UNIT.getModuleData(), "Applies an Infinity Armor to the MekaSuit.");
        add(MAModules.STARFEAST_UNIT.getModuleData(), "Applies an Infinity Armor to the MekaSuit.");
        add(MAModules.LIGHTSPEED_UNIT.getModuleData(), "Applies an Infinity Armor to the MekaSuit.");
        add(MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT.getModuleData(), "Amplifies melee attacks on players or mobs.");
        add(MAModules.COSMIC_STRIKE_UNIT.getModuleData(), "Applies an Infinity Sword to the MekaTana.");
        add(MAModules.CELESTIAL_SHOT_UNIT.getModuleData(), "Applies an Infinity Bow to the MekaBow.");
        add(MAModules.INFINITY_DAMAGE_UNIT.getModuleData(), "Significantly increases attack power.");
    }
}
