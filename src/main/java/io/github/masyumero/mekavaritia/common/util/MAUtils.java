package io.github.masyumero.mekavaritia.common.util;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import mekanism.common.content.blocktype.FactoryType;
import net.minecraft.resources.ResourceLocation;

public class MAUtils {

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MekanismAvaritia.MODID, path);
    }

    public static ResourceLocation avaritia(String path) {
        return ResourceLocation.fromNamespaceAndPath("avaritia", path);
    }

    public static ResourceLocation evolvedMekanism(String path) {
        return ResourceLocation.fromNamespaceAndPath("evolvedmekanism", path);
    }

    public static boolean isAlloying(FactoryType type) {
        if (MAAddons.EVOLVEDMEKANISM.isLoaded()) {
            return type == EMFactoryType.ALLOYING;
        }
        return false;
    }
}
