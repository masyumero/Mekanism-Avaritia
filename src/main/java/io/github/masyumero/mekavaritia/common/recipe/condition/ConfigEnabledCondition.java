package io.github.masyumero.mekavaritia.common.recipe.condition;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.common.config.value.CachedBooleanValue;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public record ConfigEnabledCondition(String name, CachedBooleanValue cachedBooleanValue) implements ICondition {
    private static final ResourceLocation NAME = MAUtils.rl("config_enabled");
    private static final Map<String , ConfigEnabledCondition> CONDITIONS = new HashMap<>();

    public static final ConfigEnabledCondition HARD_CREATIVE_ALLOY_RECIPE = createConfig("hard_creative", LoadConfig.RECIPE_CONFIG.hardCreativeAlloyRecipe);

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(IContext iContext) {
        return this.cachedBooleanValue.getAsBoolean();
    }

    @Override
    public @NotNull String toString() {
        return  "config_enabled(" + name + ")";
    }

    private static ConfigEnabledCondition createConfig(String name, CachedBooleanValue cachedBooleanValue) {
        ConfigEnabledCondition condition = new ConfigEnabledCondition(name, cachedBooleanValue);
        CONDITIONS.put(name, condition);
        return condition;
    }

    public static class Serializer implements IConditionSerializer<ConfigEnabledCondition> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject jsonObject, ConfigEnabledCondition configEnabledCondition) {
            jsonObject.addProperty("config_name", configEnabledCondition.name);
        }

        @Override
        public ConfigEnabledCondition read(JsonObject jsonObject) {
            String name = jsonObject.get("config_name").getAsString();
            ConfigEnabledCondition config = CONDITIONS.get(name);
            if (config == null) {
                throw new JsonSyntaxException("Invalid config name '" + name + "'");
            } else {
                return config;
            }
        }

        @Override
        public ResourceLocation getID() {
            return NAME;
        }
    }
}
