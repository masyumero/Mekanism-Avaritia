package io.github.masyumero.mekavaritia.client.jei;

import com.jerry.mekmm.client.jei.MoreMachineJEIRecipeType;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.registries.MoreMachineBlocks;
import io.github.masyumero.mekavaritia.common.integration.mekmm.registry.MAMoreMachineBlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAEnumUtils;
import mekanism.api.providers.IBlockProvider;
import mekanism.api.providers.IItemProvider;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.block.attribute.Attribute;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;

public class MAMMCatalystRegistryHelper {

    private MAMMCatalystRegistryHelper() {}

    public static void register(IRecipeCatalystRegistration registry) {
        register(registry, false, MoreMachineJEIRecipeType.RECYCLING, MoreMachineJEIRecipeType.PLANTING, MoreMachineJEIRecipeType.CNC_STAMPING,
                MoreMachineJEIRecipeType.CNC_LATHING, MoreMachineJEIRecipeType.CNC_ROLLING_MILL, MoreMachineJEIRecipeType.REPLICATOR, MekanismJEIRecipeType.GAS_CONVERSION);
    }

    public static void register(IRecipeCatalystRegistration registry, boolean needOrdinary, MekanismJEIRecipeType<?>... categories) {
        for (MekanismJEIRecipeType<?> category : categories) {
            RecipeType<?> recipeType = MekanismJEI.recipeType(category);
            if (category == MoreMachineJEIRecipeType.RECYCLING) {
                register(registry, recipeType, MoreMachineBlocks.RECYCLER, needOrdinary);
            } else if (category == MoreMachineJEIRecipeType.PLANTING) {
                register(registry, recipeType, MoreMachineBlocks.PLANTING_STATION, needOrdinary);
            } else if (category == MoreMachineJEIRecipeType.CNC_STAMPING) {
                register(registry, recipeType, MoreMachineBlocks.CNC_STAMPER, needOrdinary);
            } else if (category == MoreMachineJEIRecipeType.CNC_LATHING) {
                register(registry, recipeType, MoreMachineBlocks.CNC_LATHE, needOrdinary);
            } else if (category == MoreMachineJEIRecipeType.CNC_ROLLING_MILL) {
                register(registry, recipeType, MoreMachineBlocks.CNC_ROLLING_MILL, needOrdinary);
            } else if (category == MoreMachineJEIRecipeType.REPLICATOR) {
                register(registry, recipeType, MoreMachineBlocks.REPLICATOR, needOrdinary);
            } else if (category == MekanismJEIRecipeType.GAS_CONVERSION) {
                register(registry, recipeType, MoreMachineBlocks.PLANTING_STATION, needOrdinary);
                register(registry, recipeType, MoreMachineBlocks.REPLICATOR, needOrdinary);
            }
        }
    }

    public static void register(IRecipeCatalystRegistration registry, RecipeType<?> recipeType, IItemProvider workstation, boolean needOrdinary) {
        if (needOrdinary) {
            registry.addRecipeCatalyst(workstation.getItemStack(), recipeType);
        }
        if (workstation instanceof IBlockProvider blockProvider) {
            AttributeMoreMachineFactoryType factoryType = Attribute.get(blockProvider.getBlock(), AttributeMoreMachineFactoryType.class);
            if (factoryType != null) {
                for (MAFactoryTier tier : MAEnumUtils.MA_FACTORY_TIERS) {
                    registry.addRecipeCatalyst(MAMoreMachineBlocks.getMAMoreMachineFactory(tier, factoryType.getMoreMachineFactoryType()).getItemStack(), recipeType);
                }
            }
        }
    }
}
