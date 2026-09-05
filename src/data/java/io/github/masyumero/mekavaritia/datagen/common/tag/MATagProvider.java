package io.github.masyumero.mekavaritia.datagen.common.tag;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.registry.MAItems;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MATagProvider extends BaseTagProvider {

    public MATagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MekanismAvaritia.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags(HolderLookup.Provider registries) {
        addToTag(MAItems.PRISMATIC_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, MATags.Items.PRISMATIC_CONTROL_CIRCUIT);
        addToTag(MAItems.FLARE_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, MATags.Items.FLARE_CONTROL_CIRCUIT);
        addToTag(MAItems.NEURAL_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, MATags.Items.NEURAL_CONTROL_CIRCUIT);
        addToTag(MAItems.ETERNAL_CONTROL_CIRCUIT, MekanismTags.Items.CIRCUITS, MATags.Items.ETERNAL_CONTROL_CIRCUIT);

        addToTag(MAItems.CRYSTALLINE_DUST, MATags.Items.DUSTS_CRYSTALLINE);
        addToTag(MAItems.BLAZING_DUST, MATags.Items.DUSTS_BLAZING);
        addToTag(MAItems.NEUTRON_DUST, MATags.Items.DUSTS_NEUTRON);
        addToTag(MAItems.INFINITY_DUST, MATags.Items.DUSTS_INFINITY);

        addToTag(MAItems.ENRICHED_CRYSTALLINE, MATags.Items.ENRICHED, MATags.Items.ENRICHED_CRYSTALLINE, MATags.Items.ENRICHED_PRISMATIC);
        addToTag(MAItems.ENRICHED_BLAZING, MATags.Items.ENRICHED, MATags.Items.ENRICHED_BLAZING, MATags.Items.ENRICHED_FLARE);
        addToTag(MAItems.ENRICHED_NEUTRON, MATags.Items.ENRICHED, MATags.Items.ENRICHED_NEUTRON, MATags.Items.ENRICHED_NEURAL);
        addToTag(MAItems.ENRICHED_INFINITY, MATags.Items.ENRICHED, MATags.Items.ENRICHED_INFINITY, MATags.Items.ENRICHED_ETERNAL);

        addToTag(MAItems.CRYSTALLINE_ALLOY, MATags.Items.ALLOYS, MATags.Items.ALLOYS_CRYSTALLINE, MATags.Items.ALLOYS_PRISMATIC);
        addToTag(MAItems.BLAZING_ALLOY, MATags.Items.ALLOYS, MATags.Items.ALLOYS_BLAZING, MATags.Items.ALLOYS_FLARE);
        addToTag(MAItems.NEUTRON_ALLOY, MATags.Items.ALLOYS, MATags.Items.ALLOYS_NEUTRON, MATags.Items.ALLOYS_NEURAL);
        addToTag(MAItems.INFINITY_ALLOY, MATags.Items.ALLOYS, MATags.Items.ALLOYS_INFINITY, MATags.Items.ALLOYS_ETERNAL);
    }
}
