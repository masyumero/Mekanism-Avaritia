package io.github.masyumero.mekanismavaritia.client;

import io.github.masyumero.mekanismavaritia.api.recipes.ElectricNeutronCollectorRecipe;
import io.github.masyumero.mekanismavaritia.common.registry.MABlocks;
import mekanism.client.jei.MekanismJEIRecipeType;

public class MAJEIRecipeType {
    public static final MekanismJEIRecipeType<ElectricNeutronCollectorRecipe> ELECTRIC_NEUTRON_COLLECTOR = new MekanismJEIRecipeType<>(MABlocks.ELECTRIC_NEUTRON_COLLECTOR, ElectricNeutronCollectorRecipe.class);
}
