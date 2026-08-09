package io.github.masyumero.mekavaritia.common.integration;

import lombok.Getter;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

@Getter
public enum MAAddons {
    MEKMM("MekanismMoreMachine"),
    EVOMEK("EvolvedMekanism"),
    MEK_EXTRA("Mekanism_Extras");

    private final String modName;

    MAAddons(String modName) {
        this.modName = modName;
    }

    public String getModId() {
        return name().toLowerCase();
    }

    public boolean isLoaded() {
        return ModList.get() != null ? ModList.get().isLoaded(getModId()) : LoadingModList.get().getMods().stream().map(ModInfo::getModId).anyMatch(getModId()::equals);
    }
}
