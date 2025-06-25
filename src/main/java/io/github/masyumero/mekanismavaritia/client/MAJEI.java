package io.github.masyumero.mekanismavaritia.client;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.client.jei.MARecipeRegistryHelper;
import io.github.masyumero.mekanismavaritia.client.jei.machine.ElectricNeutronCollectorRecipeCategory;
import io.github.masyumero.mekanismavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekanismavaritia.common.registry.MABlocks;
import mekanism.client.jei.CatalystRegistryHelper;
import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.MekanismJEIRecipeType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
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
        return MekanismAvaritia.rl("jei_plugin");
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
        CatalystRegistryHelper.register(registry, MABlocks.ELECTRIC_NEUTRON_COLLECTOR, MekanismJEIRecipeType.GAS_CONVERSION);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        MARecipeRegistryHelper.register(registry, MAJEIRecipeType.ELECTRIC_NEUTRON_COLLECTOR, MARecipeType.ELECTRIC_NEUTRON_COLLECTOR);
    }
}