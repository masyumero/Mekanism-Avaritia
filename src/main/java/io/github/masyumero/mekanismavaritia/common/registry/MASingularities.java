package io.github.masyumero.mekanismavaritia.common.registry;

import committee.nova.mods.avaritia.common.item.singularity.Singularity;
import committee.nova.mods.avaritia.init.config.ModConfig;
import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.MekanismAvaritiaLang;
import mekanism.common.registries.MekanismItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

@SuppressWarnings("removal")
public class MASingularities {
    public static final Singularity CRYSTALLINE_ALLOY = new Singularity(new ResourceLocation(MekanismAvaritia.MODID, "crystalline_alloy"), MekanismAvaritiaLang.CRYSTALLINE_ALLOY_SINGULARITY.getTranslationKey(), new int[]{0x6fb0ac, 0x134340}, Ingredient.of(MAItem.CRYSTALLINE_ALLOY));
    public static final Singularity ANTIMATTER_PERRET = new Singularity(new ResourceLocation(MekanismAvaritia.MODID, "antimatter_pellet"), MekanismAvaritiaLang.ANTIMATTER_PELLET_SINGULARITY.getTranslationKey(), new int[]{0x9654a6, 0xbd80a5}, Ingredient.of(MekanismItems.ANTIMATTER_PELLET));
}

