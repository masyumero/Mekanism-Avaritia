package io.github.masyumero.mekavaritia.datagen.common.loot;

import io.github.masyumero.mekavaritia.datagen.common.loot.table.MABlockLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class MALootProvider extends BaseLootProvider {

    public MALootProvider(PackOutput output) {
        super(output, List.of(
                new SubProviderEntry(MABlockLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
