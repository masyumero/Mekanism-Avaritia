package io.github.masyumero.mekavaritia.client.jei;

import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.mekavaritia.client.jei.machine.ElectricNeutronCollectorRecipeCategory;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.client.jei.CatalystRegistryHelper;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.registries.MekanismBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

@JeiPlugin
public class MAJEI implements IModPlugin {
    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return MAUtils.rl("jei_plugin");
    }

    @Override
    public void registerItemSubtypes(@Nonnull ISubtypeRegistration registry) {
        MekanismJEI.registerItemSubtypes(registry, MABlocks.BLOCK.getAllBlocks());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new ElectricNeutronCollectorRecipeCategory(guiHelper, MAJEIRecipeType.ELECTRIC_NEUTRON_COLLECTOR, MABlocks.ELECTRIC_NEUTRON_COLLECTOR));
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registry) {
        CatalystRegistryHelper.register(registry, MABlocks.ELECTRIC_NEUTRON_COLLECTOR, MAJEIRecipeType.ELECTRIC_NEUTRON_COLLECTOR);

        MACatalystRegistryHelper.register(registry, MekanismBlocks.ENRICHMENT_CHAMBER);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.CRUSHER);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.COMBINER);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.PURIFICATION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.OSMIUM_COMPRESSOR, MekanismJEIRecipeType.GAS_CONVERSION);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.CHEMICAL_INJECTION_CHAMBER, MekanismJEIRecipeType.GAS_CONVERSION);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.PRECISION_SAWMILL);
        MACatalystRegistryHelper.register(registry, MekanismBlocks.METALLURGIC_INFUSER, MekanismJEIRecipeType.INFUSION_CONVERSION);
        MACatalystRegistryHelper.registerRecipeItem(registry, MekanismBlocks.ENERGIZED_SMELTER, MekanismJEIRecipeType.SMELTING, RecipeTypes.SMELTING);

        if (MAAddons.EVOLVEDMEKANISM.isLoaded()) {
            MACatalystRegistryHelper.register(registry, EMBlocks.ALLOYER);
        }
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        MARecipeRegistryHelper.register(registry, MAJEIRecipeType.ELECTRIC_NEUTRON_COLLECTOR, MARecipeType.ELECTRIC_NEUTRON_COLLECTOR);
    }
}