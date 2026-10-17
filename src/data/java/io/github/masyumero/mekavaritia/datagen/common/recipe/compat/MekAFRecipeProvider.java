package io.github.masyumero.mekavaritia.datagen.common.recipe.compat;

import com.jerry.mekaf.common.content.blocktype.AdvancedFactoryType;
import com.jerry.mekaf.common.registries.AdvancedFactoryBlocks;
import com.jerry.mekmm.common.util.MoreMachineEnumUtils;

import committee.nova.mods.avaritia.init.registry.ModItems;

import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.integration.mekaf.regisrty.MAAdvancedFactoryBlocks;
import io.github.masyumero.mekavaritia.common.tier.MAFactoryTier;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import io.github.masyumero.mekavaritia.datagen.common.recipe.builder.MekDataShapedRecipeBuilder;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.Pattern;
import io.github.masyumero.mekavaritia.datagen.common.recipe.pattern.RecipePattern;

import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.tier.FactoryTier;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;
import java.util.function.Function;

public class MekAFRecipeProvider extends CompatRecipeProvider {

    private static final RecipePattern FACTORY_PATTERN = RecipePattern.createPattern(
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY),
            RecipePattern.TripleLine.of(Pattern.CONSTANT, Pattern.PREVIOUS, Pattern.CONSTANT),
            RecipePattern.TripleLine.of(Pattern.ALLOY, Pattern.CIRCUIT, Pattern.ALLOY)
    );

    public MekAFRecipeProvider(String modid) {
        super(modid);
    }

    @Override
    protected void registerRecipes(Consumer<FinishedRecipe> consumer, String basePath) {
        factory(basePath, consumer, MAFactoryTier.PRISMATIC, MATags.Items.ALLOYS_PRISMATIC, MATags.Items.PRISMATIC_CONTROL_CIRCUIT, ModItems.crystal_matrix_ingot.get(), factoryType -> AdvancedFactoryBlocks.getAdvancedFactory(FactoryTier.ULTIMATE, factoryType));
        factory(basePath, consumer, MAFactoryTier.FLARE, MATags.Items.ALLOYS_FLARE, MATags.Items.FLARE_CONTROL_CIRCUIT, ModItems.blaze_cube.get(), factoryType -> MAAdvancedFactoryBlocks.getMAAdvancedFactory(MAFactoryTier.PRISMATIC, factoryType));
        factory(basePath, consumer, MAFactoryTier.NEURAL, MATags.Items.ALLOYS_NEURAL, MATags.Items.NEURAL_CONTROL_CIRCUIT, ModItems.neutron_ingot.get(), factoryType -> MAAdvancedFactoryBlocks.getMAAdvancedFactory(MAFactoryTier.FLARE, factoryType));
        factory(basePath, consumer, MAFactoryTier.ETERNAL, MATags.Items.ALLOYS_ETERNAL, MATags.Items.ETERNAL_CONTROL_CIRCUIT, ModItems.infinity_catalyst.get(), factoryType -> MAAdvancedFactoryBlocks.getMAAdvancedFactory(MAFactoryTier.NEURAL, factoryType));
    }

    private void factory(String path, Consumer<FinishedRecipe> consumer, MAFactoryTier tier,
                         TagKey<Item> alloy, TagKey<Item> circuit, Item constant, Function<AdvancedFactoryType, BlockRegistryObject<?, ?>> previous) {
        for (AdvancedFactoryType type : MoreMachineEnumUtils.ADVANCED_FACTORY_TYPES) {
            var factory = MAAdvancedFactoryBlocks.getMAAdvancedFactory(tier, type);
            MekDataShapedRecipeBuilder.shapedRecipe(factory).pattern(FACTORY_PATTERN)
                    .key(Pattern.ALLOY, alloy)
                    .key(Pattern.CIRCUIT, circuit)
                    .key(Pattern.CONSTANT, constant)
                    .key(Pattern.PREVIOUS, previous.apply(type))
                    .build(consumer, MAUtils.rl(path + "factory/" + tier.getMATier().getLowerName() + "/" + type.getRegistryNameComponent()));
        }
    }
}
