package io.github.masyumero.mekavaritia.client.jei;

import io.github.masyumero.mekavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import mekanism.client.jei.MekanismJEIRecipeType;

public class MAJEIRecipeType {
    public static final MekanismJEIRecipeType<ElectricNeutronCollectorRecipe> ELECTRIC_NEUTRON_COLLECTOR = new MekanismJEIRecipeType<>(MABlocks.ELECTRIC_NEUTRON_COLLECTOR, ElectricNeutronCollectorRecipe.class);
}
