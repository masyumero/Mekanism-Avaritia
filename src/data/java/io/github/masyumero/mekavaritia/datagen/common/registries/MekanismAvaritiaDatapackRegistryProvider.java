package io.github.masyumero.mekavaritia.datagen.common.registries;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MekanismAvaritiaDatapackRegistryProvider extends BaseDatapackRegistryProvider {

    public MekanismAvaritiaDatapackRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, MekanismAvaritia.MODID);
    }

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
}
