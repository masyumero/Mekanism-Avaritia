package io.github.masyumero.mekavaritia.datagen.client.models.loaders;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class CosmicModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> {

    private final List<ResourceLocation> maskTextures = new ArrayList<>();

    public static <T extends ModelBuilder<T>> CosmicModelBuilder<T> begin(T perent, ExistingFileHelper existingFileHelper) {
        return new CosmicModelBuilder<>(perent, existingFileHelper);
    }

    @SuppressWarnings("removal")
    protected CosmicModelBuilder(T parent, ExistingFileHelper existingFileHelper) {
        super(new ResourceLocation("avaritia:cosmic"), parent, existingFileHelper);
    }

    public CosmicModelBuilder<T> mask(ResourceLocation... maskTextures) {
        Preconditions.checkNotNull(maskTextures, "maskTextures must not be null");
        this.maskTextures.addAll(List.of(maskTextures));
        return this;
    }

    @Override
    public JsonObject toJson(JsonObject json) {
        json = super.toJson(json);
        JsonObject mask = new JsonObject();

        for (ResourceLocation maskTexture : maskTextures) {
            mask.addProperty("mask", maskTexture.toString());
        }

        json.add("cosmic", mask);
        return json;
    }
}
