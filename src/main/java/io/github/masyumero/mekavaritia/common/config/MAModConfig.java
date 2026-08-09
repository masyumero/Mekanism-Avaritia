package io.github.masyumero.mekavaritia.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import mekanism.common.config.IMekanismConfig;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ConfigFileTypeHandler;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Function;

public class MAModConfig extends ModConfig {
    private static final MAConfigFileTypeHandler MA_TOML = new MAConfigFileTypeHandler();

    private final IMekanismConfig MAConfig;

    public MAModConfig(ModContainer container, IMekanismConfig config) {
        super(config.getConfigType(), config.getConfigSpec(), container, MekanismAvaritia.MODID + "/" + config.getFileName() + ".toml");
        this.MAConfig = config;
    }

    @Override
    public ConfigFileTypeHandler getHandler() {
        return MA_TOML;
    }

    public  void clearCache(ModConfigEvent event) {
        MAConfig.clearCache(event instanceof ModConfigEvent.Unloading);
    }

    private static class MAConfigFileTypeHandler extends ConfigFileTypeHandler {
        private static Path getPath(Path configBasePath) {
            if (configBasePath.endsWith("serverconfig")) {
                return FMLPaths.CONFIGDIR.get();
            }
            return configBasePath;
        }

        @Override
        public Function<ModConfig, CommentedFileConfig> reader(Path configBasePath) {
            return super.reader(getPath(configBasePath));
        }

        @Override
        public void unload(Path configBasePath, ModConfig config) {
            super.unload(getPath(configBasePath), config);
        }
    }

}
