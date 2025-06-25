package io.github.masyumero.mekanismavaritia;

import fr.iglee42.evolvedmekanism.EvolvedMekanismLang;
import mekanism.api.text.ILangEntry;
import mekanism.common.MekanismLang;
import net.minecraft.Util;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public enum MekanismAvaritiaLang implements ILangEntry{
    TAB("constants", "mod_name"),
    NULL("null", "null"),
    CRYSTALLINE_ALLOY_SINGULARITY("singularity", "crystalline_alloy"),
    ANTIMATTER_PELLET_SINGULARITY("singularity", "antimatter_pellet"),
    DESCRIPTION_ELECTRIC_NEUTRON_COLLECTOR("description", "electric_neutron_collector");

    private final String key;
    MekanismAvaritiaLang(String type, String path) {
        this(Util.makeDescriptionId(type, MekanismAvaritia.rl(path)));
    }

    MekanismAvaritiaLang(String key) {
        this.key = key;
    }

    public @NotNull MekanismLang getAlloyer() {
        if (ModList.get().isLoaded("evolvedmekanism")) {
            return EvolvedMekanismLang.DESCRIPTION_ALLOYER;
        }
        return MekanismLang.MEKANISM;
    }

    @Override
    public @NotNull String getTranslationKey() {
        return key;
    }
}