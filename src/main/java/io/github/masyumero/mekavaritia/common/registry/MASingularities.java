package io.github.masyumero.mekavaritia.common.registry;

import committee.nova.mods.avaritia.core.singularity.Singularity;
import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.common.registries.MekanismItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("removal")
public class MASingularities {

    public static final List<Singularity> SINGULARITIES = new ArrayList<>();

    public static final Singularity CRYSTALLINE_ALLOY = create(MAUtils.rl("crystalline_alloy"), MekanismAvaritiaLang.CRYSTALLINE_ALLOY_SINGULARITY.getTranslationKey(), new int[]{0x6fb0ac, 0x134340}, Ingredient.of(MAItems.CRYSTALLINE_ALLOY));
    public static final Singularity ANTIMATTER_PERRET = create(MAUtils.rl("antimatter_pellet"), MekanismAvaritiaLang.ANTIMATTER_PELLET_SINGULARITY.getTranslationKey(), new int[]{0x9654a6, 0xbd80a5}, Ingredient.of(MekanismItems.ANTIMATTER_PELLET));

    private static Singularity create(ResourceLocation registryName, String displayName, int[] colors, Ingredient ingredient) {
        Singularity singularity = Singularity.create(registryName, displayName, colors, ingredient);
        SINGULARITIES.add(singularity);
        return singularity;
    }

    public static void init() {}
}

