package io.github.masyumero.mekavaritia.client.jei;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryBlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.providers.IItemProvider;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

public class MAAFCatalystRegistryHelper {

    public static void register(IRecipeCatalystRegistration registry) {
        register(registry, false, MekanismJEIRecipeType.OXIDIZING, MekanismJEIRecipeType.DISSOLUTION, MekanismJEIRecipeType.WASHING,
                MekanismJEIRecipeType.CRYSTALLIZING, MekanismJEIRecipeType.REACTION, MekanismJEIRecipeType.CENTRIFUGING,
                MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION, MekanismJEIRecipeType.PIGMENT_EXTRACTING, MekanismJEIRecipeType.PAINTING);
    }

    public static void register(IRecipeCatalystRegistration registry, boolean needOrdinary, MekanismJEIRecipeType<?>... categories) {
        for (MekanismJEIRecipeType<?> category : categories) {
            RecipeType<?> recipeType = MekanismJEI.recipeType(category);
            if (category == MekanismJEIRecipeType.OXIDIZING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_OXIDIZER, AdvancedFactoryType.OXIDIZING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.DISSOLUTION) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER, AdvancedFactoryType.DISSOLVING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.WASHING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_WASHER, AdvancedFactoryType.WASHING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.CRYSTALLIZING) {
                register(registry, recipeType, MekanismBlocks.CHEMICAL_CRYSTALLIZER, AdvancedFactoryType.CRYSTALLIZING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.REACTION) {
                register(registry, recipeType, MekanismBlocks.PRESSURIZED_REACTION_CHAMBER, AdvancedFactoryType.PRESSURISED_REACTING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.CENTRIFUGING) {
                register(registry, recipeType, MekanismBlocks.ISOTOPIC_CENTRIFUGE, AdvancedFactoryType.CENTRIFUGING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.NUTRITIONAL_LIQUIFICATION) {
                register(registry, recipeType, MekanismBlocks.NUTRITIONAL_LIQUIFIER, AdvancedFactoryType.LIQUIFYING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.PIGMENT_EXTRACTING) {
                register(registry, recipeType, MekanismBlocks.PIGMENT_EXTRACTOR, AdvancedFactoryType.PIGMENT_EXTRACTING, needOrdinary);
            } else if (category == MekanismJEIRecipeType.PAINTING) {
                register(registry, recipeType, MekanismBlocks.PAINTING_MACHINE, AdvancedFactoryType.PAINTING, needOrdinary);
            }
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, IItemProvider workstation, AdvancedFactoryType type, boolean needOrdinary) {
        if (needOrdinary) {
            registry.addRecipeCatalyst(workstation.getItemStack(), recipeType);
        }
        for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
            registry.addRecipeCatalyst(factory(tier, type).getItemStack(), recipeType);
        }
    }

    private static IItemProvider factory(MAFactoryTier tier, AdvancedFactoryType type) {
        return MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, type);
    }
}
