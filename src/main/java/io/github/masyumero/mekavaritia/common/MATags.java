package io.github.masyumero.mekavaritia.common;

import io.github.masyumero.mekavaritia.common.util.MAUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MATags {

    public static void init() {
        Items.init();
    }

    public static class Items {

        private static void init() {}

        public static final TagKey<Item> PRISMATIC_CONTROL_CIRCUIT =    forgeTag("circuits/prismatic");
        public static final TagKey<Item> FLARE_CONTROL_CIRCUIT =        forgeTag("circuits/flare");
        public static final TagKey<Item> NEURAL_CONTROL_CIRCUIT =       forgeTag("circuits/neural");
        public static final TagKey<Item> ETERNAL_CONTROL_CIRCUIT =      forgeTag("circuits/eternal");

        public static final TagKey<Item> DUSTS_CRYSTALLINE =    forgeTag("dusts/crystalline");
        public static final TagKey<Item> DUSTS_BLAZING  =       forgeTag("dusts/blazing");
        public static final TagKey<Item> DUSTS_NEUTRON  =       forgeTag("dusts/neutronium");
        public static final TagKey<Item> DUSTS_INFINITY =       forgeTag("dusts/infinity");

        public static final TagKey<Item> ALLOYS =               tag("alloys");
        public static final TagKey<Item> ALLOYS_CRYSTALLINE =   tag("alloys/crystalline");
        public static final TagKey<Item> ALLOYS_BLAZING =       tag("alloys/blazing");
        public static final TagKey<Item> ALLOYS_NEUTRON =       tag("alloys/neutronium");
        public static final TagKey<Item> ALLOYS_INFINITY =      tag("alloys/infinity");
        public static final TagKey<Item> ALLOYS_PRISMATIC =     forgeTag("alloys/prismatic");
        public static final TagKey<Item> ALLOYS_FLARE =         forgeTag("alloys/flare");
        public static final TagKey<Item> ALLOYS_NEURAL =        forgeTag("alloys/neural");
        public static final TagKey<Item> ALLOYS_ETERNAL =       forgeTag("alloys/eternal");

        public static final TagKey<Item> ENRICHED =             tag("enriched");
        public static final TagKey<Item> ENRICHED_CRYSTALLINE = tag("enriched/crystalline");
        public static final TagKey<Item> ENRICHED_BLAZING  =    tag("enriched/blazing");
        public static final TagKey<Item> ENRICHED_NEUTRON  =    tag("enriched/neutronium");
        public static final TagKey<Item> ENRICHED_INFINITY =    tag("enriched/infinity");
        public static final TagKey<Item> ENRICHED_PRISMATIC =   forgeTag("enriched/prismatic");
        public static final TagKey<Item> ENRICHED_FLARE =       forgeTag("enriched/flare");
        public static final TagKey<Item> ENRICHED_NEURAL =      forgeTag("enriched/neural");
        public static final TagKey<Item> ENRICHED_ETERNAL  =    forgeTag("enriched/eternal");

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(MAUtils.rl(name));
        }
    }
}
