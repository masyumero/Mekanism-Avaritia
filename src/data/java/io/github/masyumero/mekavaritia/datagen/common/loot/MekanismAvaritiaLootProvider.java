package io.github.masyumero.mekavaritia.datagen.common.loot;

import io.github.masyumero.mekavaritia.datagen.common.loot.table.MekanismAvaritiaBlockLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class MekanismAvaritiaLootProvider extends BaseLootProvider {

    public MekanismAvaritiaLootProvider(PackOutput output) {
        super(output, List.of(
                new SubProviderEntry(MekanismAvaritiaBlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
