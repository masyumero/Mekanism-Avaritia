package io.github.masyumero.mekavaritia.common.config;

import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;

public class LoadConfig {

    private LoadConfig() {
    }

    public static final MAGearConfig GEAR_CONFIG = new MAGearConfig();
    public static final MARecipeConfig RECIPE_CONFIG = new MARecipeConfig();
    public static final MAConfig MA_CONFIG = new MAConfig();
    public static final MAUsageConfig USAGE_CONFIG = new MAUsageConfig();
    public static final MAStorageConfig STORAGE_CONFIG = new MAStorageConfig();

    @SuppressWarnings("removal")
    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        ModContainer modContainer = modLoadingContext.getActiveContainer();
        MAConfigHelper.registerConfig(modContainer, GEAR_CONFIG);
        MAConfigHelper.registerConfig(modContainer, RECIPE_CONFIG);
        MAConfigHelper.registerConfig(modContainer, MA_CONFIG);
        MAConfigHelper.registerConfig(modContainer, USAGE_CONFIG);
        MAConfigHelper.registerConfig(modContainer, STORAGE_CONFIG);
    }
}
