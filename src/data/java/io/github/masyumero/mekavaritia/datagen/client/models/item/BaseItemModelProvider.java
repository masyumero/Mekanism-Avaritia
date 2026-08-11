package io.github.masyumero.mekavaritia.datagen.client.models.item;

import com.jerry.mekaf.common.block.attribute.AttributeAdvancedFactoryType;
import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import com.jerry.mekmm.common.block.attribute.AttributeMoreMachineFactoryType;
import com.jerry.mekmm.common.content.blocktype.MoreMachineFactoryType;
import committee.nova.mods.avaritia.client.model.loader.base.HaloSetting;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.client.models.loaders.CosmicModelBuilder;
import io.github.masyumero.mekavaritia.datagen.client.models.loaders.HaloModelBuilder;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import mekanism.api.providers.IItemProvider;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class BaseItemModelProvider extends ItemModelProvider {

    public BaseItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    protected ItemModelBuilder maskedItem(IItemProvider item) {
        return customLoaderItem(item.asItem(), CosmicModelBuilder::begin, builder ->
                builder.mask(MAUtils.rl("mask/" + item.getRegistryName().getPath() + "_mask")));
    }

    protected ItemModelBuilder hasHaloItem(IItemProvider item, ResourceLocation halo, HaloSetting haloSetting) {
        return customLoaderItem(item.asItem(), HaloModelBuilder::begin, builder -> {
            builder.haloSetting(haloSetting);
            builder.end().texture("halo", halo);
        });
    }

    protected ItemModelBuilder hasHaloItem(IItemProvider item, ResourceLocation halo, @Nullable IntArrayList layerColors, String texture, int color, int size, boolean pulse) {
        return hasHaloItem(item, halo, new HaloSetting(layerColors, texture, color, size, pulse));
    }

    protected <L extends CustomLoaderBuilder<ItemModelBuilder>> ItemModelBuilder customLoaderItem(Item item, BiFunction<ItemModelBuilder, ExistingFileHelper, L> customLoaderFactory, Consumer<L> customLoaderBuilderSupplier) {
        var builder = super.basicItem(item).customLoader(customLoaderFactory);
        customLoaderBuilderSupplier.accept(builder);
        return builder.end();
    }

    protected ItemModelBuilder factoryBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), MAUtils.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getMATier().getLowerName()));
    }

    protected ItemModelBuilder extraAlloyingFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        ExtraFactoryTier tier = ExtraAttribute.getTier(blockRO.getBlock(), ExtraFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), MAUtils.rl("block/factory/alloying/" + tier.getAdvanceTier().getLowerName()));
    }

    protected ItemModelBuilder advancedFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), MAUtils.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getMATier().getLowerName()));
    }

    protected ItemModelBuilder moreMachineFactoryBlock(BlockRegistryObject<?, ?> blockRO) {
        MoreMachineFactoryType type = Attribute.get(blockRO, AttributeMoreMachineFactoryType.class).getMoreMachineFactoryType();
        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);

        return this.withExistingParent(blockRO.getName(), MAUtils.rl("block/factory/" + type.getRegistryNameComponent() +  "/" + tier.getMATier().getLowerName()));
    }

    protected ItemModelBuilder doubleLayeredBlock(ItemModelBuilder modelBuilder) {
        return modelBuilder.transforms()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75,45,0)
                .translation(0,2.5F,1.25F)
                .scale(0.375F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                .rotation(75,45,0)
                .translation(0,2.5F,1.25F)
                .scale(0.375F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0,45,0)
                .scale(0.4F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0,225,0)
                .scale(0.4F)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .translation(0, 3, 0)
                .scale(0.25F)
                .end()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .translation(0, -2.5F, 0)
                .scale(0.43F)
                .end()
                .transform(ItemDisplayContext.HEAD)
                .translation(0, 14, 0)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .translation(0, -3.75F, 0)
                .scale(0.5F)
                .end()
                .end();
    }
}
