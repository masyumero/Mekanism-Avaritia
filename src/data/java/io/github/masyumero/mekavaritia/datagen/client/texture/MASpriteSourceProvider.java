package io.github.masyumero.mekavaritia.datagen.client.texture;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MASpriteSourceProvider extends MABaseSpriteSourceProvider {

    public MASpriteSourceProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, MekanismAvaritia.MODID, fileHelper);
    }

    @Override
    protected void addSources() {
        SourceList atlas = atlas(BLOCKS_ATLAS);
        addChemicalSprites(atlas);
        addFiles(atlas,
                MAUtils.rl("entity/armor/mekasuit_infinity_elytra"));
    }
}
