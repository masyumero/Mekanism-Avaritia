package io.github.masyumero.mekavaritia.mixin.minecraft;

import com.google.gson.JsonElement;
import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeManager.class)
public class MixinRecipeManager {

    @ModifyVariable(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"), argsOnly = true)
    private Map<ResourceLocation, JsonElement> applyModifyVariable(Map<ResourceLocation, JsonElement> original) {
        Map<ResourceLocation, JsonElement> modifiedRecipe = new HashMap<>(original);
        if (LoadConfig.RECIPE_CONFIG.removeAvaritiaCreativeRecipe.getAsBoolean()) {
            modifiedRecipe.remove(MAUtils.avaritia("mek_creative_bin"));
            modifiedRecipe.remove(MAUtils.avaritia("mek_creative_chemical_tank"));
            modifiedRecipe.remove(MAUtils.avaritia("mek_creative_energy_cube"));
            modifiedRecipe.remove(MAUtils.avaritia("mek_creative_fluid_tank"));
        }
        return modifiedRecipe;
    }
}
