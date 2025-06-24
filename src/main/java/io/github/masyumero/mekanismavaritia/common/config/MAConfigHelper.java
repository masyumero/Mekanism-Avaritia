package io.github.masyumero.mekanismavaritia.common.config;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import mekanism.common.config.IMekanismConfig;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class MAConfigHelper {

    public static final Path CONFIG_DIR = FMLPaths.getOrCreateGameRelativePath(FMLPaths.CONFIGDIR.get().resolve(MekanismAvaritia.MODNAME));

    public static void registerConfig(ModContainer modContainer, IMekanismConfig config) {
        MAModConfig modConfig = new MAModConfig(modContainer, config);
        if (config.addToContainer()) {
            modContainer.addConfig(modConfig);
        }
    }
}
