package io.github.masyumero.mekavaritia.datagen.common.singularity;

import committee.nova.mods.avaritia.init.data.provider.base.SingularityProvider;
import io.github.masyumero.mekavaritia.common.registry.MASingularities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MekanismAvaritiaSingularityProvider extends SingularityProvider {

    public MekanismAvaritiaSingularityProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper fileHelper) {
        super(generator, registries, fileHelper);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        addSingularity(MASingularities.SINGULARITIES);
    }
}
