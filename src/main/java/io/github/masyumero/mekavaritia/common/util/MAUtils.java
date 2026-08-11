package io.github.masyumero.mekavaritia.common.util;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
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
}
