package io.github.masyumero.mekavaritia.datagen.client.models.block;

import com.jerry.mekanism_extras.MekanismExtras;
import com.jerry.mekanism_extras.common.block.attribute.ExtraAttribute;
import com.jerry.mekanism_extras.common.tier.ExtraFactoryTier;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.api.tier.ITier;
import mekanism.common.Mekanism;
import mekanism.common.block.attribute.Attribute;
import mekanism.common.block.attribute.AttributeFactoryType;
import mekanism.common.block.states.BlockStateHelper;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class BaseBlockModelsProvider extends BlockStateProvider {

    public BaseBlockModelsProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    protected void transmitters(BlockRegistryObject<?, ?> transmitter, String type, ITier tier, ResourceLocation parent, boolean isSmall) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        MATier maTier = switch (tier.getBaseTier()) {
            case ADVANCED -> MATier.FLARE;
            case ELITE -> MATier.NEURAL;
            case ULTIMATE -> MATier.ETERNAL;
            default -> MATier.PRISMATIC;
        };
        ResourceLocation path = MAUtils.rl("block/transmitter/" + (isSmall ? "small/" : "large/") + type + "/" + maTier.getLowerName());
        String name = transmitter.getName();

        simpleBlockItem(transmitter.getBlock(),
                models().withExistingParent(path.getPath(), parent)
                .texture("side", MAUtils.rl("block/models/multipart/" + name + "_vertical"))
                .texture("center_down", MAUtils.rl("block/models/multipart/" + name))
                .texture("side_opaque",  MAUtils.rl("block/models/multipart/opaque/" + name + "_vertical"))
                .texture("center_opaque", MAUtils.rl("block/models/multipart/" + (isSmall ? "" : "opaque/") + name)));

        getVariantBuilder(transmitter.getBlock())
                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(path)).build());
    }

//    protected void inductionCellAndProvider(BlockRegistryObject<?, ?> cellBlockRO, BlockRegistryObject<?, ?> providerBlockRO) {
//        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
//        MATier cellTier = MAAttribute.getTier(cellBlockRO.getBlock(), MAICTier.class).getMATier();
//        MATier providerTier = MAAttribute.getTier(providerBlockRO.getBlock(), MAIPTier.class).getMATier();
//        ResourceLocation cellPath = MAUtils.rl("block/induction/cell/" + cellTier.getLowerName());
//        ResourceLocation providerPath = MAUtils.rl("block/induction/provider/" + providerTier.getLowerName());
//
//        ResourceLocation cellTexture = MAUtils.rl("block/" + cellBlockRO.getName());
//        ResourceLocation providerTexture = MAUtils.rl("block/" + providerBlockRO.getName());
//
//        simpleBlockItem(cellBlockRO.getBlock(),
//                models().withExistingParent(cellPath.getPath() , Mekanism.rl("block/induction/cell/basic"))
//                .renderType(mcLoc("cutout"))
//                .texture("particle", cellTexture)
//                .texture("all", cellTexture));
//
//        getVariantBuilder(cellBlockRO.getBlock())
//                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(cellPath)).build());
//
//        simpleBlockItem(providerBlockRO.getBlock(),
//                models().withExistingParent(providerPath.getPath() , Mekanism.rl("block/induction/provider/base"))
//                .renderType(mcLoc("cutout"))
//                .texture("particle", providerTexture)
//                .texture("all", providerTexture)
//                .texture("glow", Mekanism.rl("block/induction_provider_glow"))
//                .texture("led", providerTexture + "_led"));
//
//        getVariantBuilder(providerBlockRO.getBlock())
//                .forAllStatesExcept(state -> builder.modelFile(models().getExistingFile(providerPath)).build());
//    }

    protected void simpleFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        FactoryType type = Attribute.get(blockRO, AttributeFactoryType.class).getFactoryType();
        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);

        String blockPath = "block/factory/" + type.getRegistryNameComponent();

        machineState(blockRO, getActiveFactoryBlockModel(blockPath, tier, type), getFactoryBlockModel(blockPath, tier, type));
    }

    private ModelFile getFactoryBlockModel(String blockPath, MAFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(factoryType == EMFactoryType.ALLOYING ? MAUtils.evolvedMekanism("block/factory/" + factoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + factoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile((MAUtils.rl("block/factory/front_led/" + tier.getMATier().getLowerName()))))).end();
    }

    private ModelFile getActiveFactoryBlockModel(String blockPath, MAFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(factoryType == EMFactoryType.ALLOYING ? MAUtils.evolvedMekanism("block/factory/" + factoryType.getRegistryNameComponent() + "/base") : Mekanism.rl("block/factory/" + factoryType.getRegistryNameComponent() + "/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.rl("block/factory/front_led/active/" + tier.getMATier().getLowerName())))).end();
    }

    protected void alloyingFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
        ExtraFactoryTier tier = ExtraAttribute.getTier(blockRO.getBlock(), ExtraFactoryTier.class);

        String blockPath = "block/factory/alloying";

        machineState(blockRO, getActiveExtraAlloyingFactoryBlockModel(blockPath, tier, EMFactoryType.ALLOYING), getExtraAlloyingFactoryBlockModel(blockPath, tier, EMFactoryType.ALLOYING));
    }

    private ModelFile getExtraAlloyingFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.evolvedMekanism("block/factory/alloying/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/" + tier.getAdvanceTier().getLowerName())))).end();
    }

    private ModelFile getActiveExtraAlloyingFactoryBlockModel(String blockPath, ExtraFactoryTier tier, FactoryType factoryType) {
        return models().withExistingParent(blockPath + "/active/" + tier.getAdvanceTier().getLowerName(), this.mcLoc("block/block"))
                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
                .customLoader(CompositeModelBuilder::begin)
                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.evolvedMekanism("block/factory/alloying/base"))))
                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MekanismExtras.rl("block/factory/front_led/active/" + tier.getAdvanceTier().getLowerName())))).end();
    }
//
//    public void simpleAdvancedFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
//        AdvancedFactoryType type = Attribute.get(blockRO, AttributeAdvancedFactoryType.class).getAdvancedFactoryType();
//        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);
//
//        String blockPath = "block/factory/" + type.getRegistryNameComponent();
//
//        machineState(blockRO, getActiveAdvancedFactoryBlockModel(blockPath, tier, type), getAdvancedFactoryBlockModel(blockPath, tier, type));
//    }
//
//    private ModelFile getAdvancedFactoryBlockModel(String blockPath, MAFactoryTier tier, AdvancedFactoryType advancedFactoryType) {
//        return models().withExistingParent(blockPath + "/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
//                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
//                .customLoader(CompositeModelBuilder::begin)
//                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + advancedFactoryType.getRegistryNameComponent() + "/base"))))
//                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.rl("block/factory/front_led/" + tier.getMATier().getLowerName())))).end();
//    }
//
//    private ModelFile getActiveAdvancedFactoryBlockModel(String blockPath, MAFactoryTier tier, AdvancedFactoryType advancedFactoryType) {
//        return models().withExistingParent(blockPath + "/active/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
//                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
//                .customLoader(CompositeModelBuilder::begin)
//                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + advancedFactoryType.getRegistryNameComponent() + "/base"))))
//                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.rl("block/factory/front_led/active/" + tier.getMATier().getLowerName())))).end();
//    }
//
//    public void simpleMoreMachineFactoryMachineBlock(BlockRegistryObject<?, ?> blockRO) {
//        MoreMachineFactoryType type = Attribute.get(blockRO, AttributeMoreMachineFactoryType.class).getMoreMachineFactoryType();
//        MAFactoryTier tier = MAAttribute.getTier(blockRO.getBlock(), MAFactoryTier.class);
//
//        String blockPath = "block/factory/" + type.getRegistryNameComponent();
//
//        machineState(blockRO, getActiveMoreMachineFactoryBlockModel(blockPath, tier, type), getMoreMachineFactoryBlockModel(blockPath, tier, type));
//    }
//
//    private ModelFile getMoreMachineFactoryBlockModel(String blockPath, MAFactoryTier tier, MoreMachineFactoryType moreMachineFactoryType) {
//        return models().withExistingParent(blockPath + "/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
//                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
//                .customLoader(CompositeModelBuilder::begin)
//                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + moreMachineFactoryType.getRegistryNameComponent() + "/base"))))
//                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.rl("block/factory/front_led/" + tier.getMATier().getLowerName())))).end();
//    }
//
//    private ModelFile getActiveMoreMachineFactoryBlockModel(String blockPath, MAFactoryTier tier, MoreMachineFactoryType moreMachineFactoryType) {
//        return models().withExistingParent(blockPath + "/active/" + tier.getMATier().getLowerName(), this.mcLoc("block/block"))
//                .texture("particle", Mekanism.rl("block/factory/factory_front_back"))
//                .customLoader(CompositeModelBuilder::begin)
//                .child("base", models().nested().parent(new ModelFile.UncheckedModelFile(Mekmm.rl("block/factory/" + moreMachineFactoryType.getRegistryNameComponent() + "/base"))))
//                .child("front_led", models().nested().parent(new ModelFile.UncheckedModelFile(MAUtils.rl("block/factory/front_led/active/" + tier.getMATier().getLowerName())))).end();
//    }

    private void machineState(BlockRegistryObject<?, ?> blockRO, ModelFile activeBlockModel, ModelFile blockModel) {
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(blockRO.getBlock());

        variantBuilder.forAllStatesExcept(state -> {
            var yRot = switch ((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot()) {
                case 0 -> 180;
                case 90 -> -90;
                case 180 -> 0;
                case 270 -> 90;
                default ->
                        throw new IllegalStateException("Unexpected value: " + (int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            };
            if (Attribute.isActive(state)) {
                return builder
                        .modelFile(activeBlockModel)
                        .rotationY(yRot).build();
            } else {
                return builder
                        .modelFile(blockModel)
                        .rotationY(yRot).build();
            }
        }, BlockStateHelper.FLUID_LOGGED);
    }
}
