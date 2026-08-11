package io.github.masyumero.mekavaritia.common.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedBooleanValue;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class MARecipeConfig extends BaseMekanismConfig {

    private final ForgeConfigSpec configSpec;
    public final CachedBooleanValue hardCreativeAlloyRecipe;
    public final CachedBooleanValue removeAvaritiaCreativeRecipe;

    public MARecipeConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Recipe Config");
        builder.push("Creative Recipe");
        hardCreativeAlloyRecipe = CachedBooleanValue.wrap(this, builder.comment("This works if EvolvedMekanism is installed.").define("HardCreativeAlloyRecipe", false));
        removeAvaritiaCreativeRecipe = CachedBooleanValue.wrap(this, builder.comment("I will delete Avaritia's creative recipe.").define("RemoveAvaritiaCreativeRecipe", false));
        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "recipe";
    }

    @Override
    public ForgeConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public ModConfig.Type getConfigType() {
        return ModConfig.Type.COMMON;
    }
}
