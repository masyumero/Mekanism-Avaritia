package io.github.masyumero.mekavaritia;

import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;

public enum MekanismAvaritiaLang implements ILangEntry{
    TAB("constants", "mod_name"),
    NULL("null", "null"),
    CRYSTALLINE_ALLOY_SINGULARITY("singularity", "crystalline_alloy"),
    ANTIMATTER_PELLET_SINGULARITY("singularity", "antimatter_pellet"),
    DESCRIPTION_ELECTRIC_NEUTRON_COLLECTOR("description", "electric_neutron_collector"),
    DESCRIPTION_ELECTRIC_SINGULARITY_COMPRESSOR("description", "electric_singularity_compressor");

    private final String key;

    MekanismAvaritiaLang(String type, String path) {
        this(Util.makeDescriptionId(type, MAUtils.rl(path)));
    }

    MekanismAvaritiaLang(String key) {
        this.key = key;
    }

    public static MekanismLang getAlloyer() {
        if (MAAddons.EVOLVEDMEKANISM.isLoaded()) {
            return EvolvedMekanismLang.DESCRIPTION_ALLOYER;
        }
        return MekanismLang.MEKANISM;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }
}