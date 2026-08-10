package io.github.masyumero.mekavaritia.datagen.client.texture;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MekanismAvaritiaSpriteSourceProvider extends BaseSpriteSourceProvider {

    public MekanismAvaritiaSpriteSourceProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, MekanismAvaritia.MODID, fileHelper);
    }

    @Override
    protected void addSources() {
        SourceList atlas = atlas(BLOCKS_ATLAS);
        addChemicalSprites(atlas);
        addFiles(atlas,
                MekanismAvaritia.rl("entity/armor/mekasuit_infinity_elytra"));
    }
}
