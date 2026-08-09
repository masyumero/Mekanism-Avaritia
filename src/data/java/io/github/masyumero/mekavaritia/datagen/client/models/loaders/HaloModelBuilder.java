package io.github.masyumero.mekavaritia.datagen.client.models.loaders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import committee.nova.mods.avaritia.client.model.loader.base.HaloSetting;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class HaloModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {

    private HaloSetting haloSetting;

    public static <T extends ModelBuilder<T>> HaloModelBuilder<T> begin(T perent, ExistingFileHelper existingFileHelper) {
        return new HaloModelBuilder<>(perent, existingFileHelper);
    }

    @SuppressWarnings("removal")
    protected HaloModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(new ResourceLocation("avaritia:halo"), parent, existingFileHelper);
    }

    public HaloModelBuilder<T> haloSetting(HaloSetting haloSetting) {
        this.haloSetting = haloSetting;
        return this;
    }

    public HaloModelBuilder<T> haloSetting(@Nullable IntArrayList layerColors, String texture, int color, int size, boolean pulse) {
        haloSetting = new HaloSetting(layerColors, texture, color, size, pulse);
        return this;
    }

    @Override
    public JsonObject toJson(JsonObject json) {
        json = super.toJson(json);
        JsonObject halo = new JsonObject();
        JsonArray layerColors = new JsonArray();

        if (haloSetting.layerColors() != null) {
            for (Integer color : haloSetting.layerColors()) {
                layerColors.add(color);
            }
        }

        if (!layerColors.isEmpty()) {
            halo.add("layerColors", layerColors);
        }
        halo.addProperty("texture", haloSetting.texture());
        halo.addProperty("color", haloSetting.color());
        halo.addProperty("size", haloSetting.size());
        halo.addProperty("pulse", haloSetting.pulse());

        json.add("halo", halo);

        return json;
    }
}
