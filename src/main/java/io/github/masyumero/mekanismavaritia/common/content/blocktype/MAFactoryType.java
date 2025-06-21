package io.github.masyumero.mekanismavaritia.common.content.blocktype;

import fr.iglee42.evolvedmekanism.jei.EMJEI;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import io.github.masyumero.mekanismavaritia.common.registry.MABlockType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.text.IHasTranslationKey;
import mekanism.client.jei.MekanismJEIRecipeType;
import mekanism.common.MekanismLang;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;

import java.util.Locale;
import java.util.function.Supplier;

@NothingNullByDefault
public enum MAFactoryType implements IHasTranslationKey {
    ALLOYING("alloying", "factory.mekanism.alloying", () -> MABlockType.ALLOYER, () -> EMBlocks.ALLOYER),
    SMELTING("smelting", MekanismLang.SMELTING.getTranslationKey(), () -> MABlockType.ENERGIZED_SMELTER, () -> MekanismBlocks.ENERGIZED_SMELTER),
    ENRICHING("enriching", MekanismLang.ENRICHING.getTranslationKey(), () -> MABlockType.ENRICHMENT_CHAMBER, () -> MekanismBlocks.ENRICHMENT_CHAMBER),
    CRUSHING("crushing", MekanismLang.CRUSHING.getTranslationKey(), () -> MABlockType.CRUSHER, () -> MekanismBlocks.CRUSHER),
    COMPRESSING("compressing", MekanismLang.COMPRESSING.getTranslationKey(), () -> MABlockType.OSMIUM_COMPRESSOR, () -> MekanismBlocks.OSMIUM_COMPRESSOR),
    COMBINING("combining", MekanismLang.COMBINING.getTranslationKey(), () -> MABlockType.COMBINER, () -> MekanismBlocks.COMBINER),
    PURIFYING("purifying", MekanismLang.PURIFYING.getTranslationKey(), () -> MABlockType.PURIFICATION_CHAMBER, () -> MekanismBlocks.PURIFICATION_CHAMBER),
    INJECTING("injecting", MekanismLang.INJECTING.getTranslationKey(), () -> MABlockType.CHEMICAL_INJECTION_CHAMBER, () -> MekanismBlocks.CHEMICAL_INJECTION_CHAMBER),
    INFUSING("infusing", MekanismLang.INFUSING.getTranslationKey(), () -> MABlockType.METALLURGIC_INFUSER, () -> MekanismBlocks.METALLURGIC_INFUSER),
    SAWING("sawing", MekanismLang.SAWING.getTranslationKey(), () -> MABlockType.PRECISION_SAWMILL, () -> MekanismBlocks.PRECISION_SAWMILL);


    private final String registryNameComponent;
    private final String translationKey;
    private final Supplier<MAMachine.MAFactoryMachine<?>> baseMachine;
    private final Supplier<BlockRegistryObject<?, ?>> baseBlock;

    MAFactoryType(String registryNameComponent, String translationKey, Supplier<MAMachine.MAFactoryMachine<?>> baseMachine, Supplier<BlockRegistryObject<?, ?>> baseBlock) {
        this.registryNameComponent = registryNameComponent;
        this.translationKey = translationKey;
        this.baseMachine = baseMachine;
        this.baseBlock = baseBlock;
    }

    public MekanismJEIRecipeType<?> getRecipeType(MAFactoryType factoryType) {
        return switch (factoryType) {
            case ALLOYING -> EMJEI.ALLOYING;
            case SMELTING -> MekanismJEIRecipeType.SMELTING;
            case ENRICHING -> MekanismJEIRecipeType.ENRICHING;
            case CRUSHING -> MekanismJEIRecipeType.CRUSHING;
            case COMPRESSING -> MekanismJEIRecipeType.COMPRESSING;
            case COMBINING -> MekanismJEIRecipeType.COMBINING;
            case PURIFYING -> MekanismJEIRecipeType.PURIFYING;
            case INJECTING -> MekanismJEIRecipeType.INJECTING;
            case INFUSING -> MekanismJEIRecipeType.METALLURGIC_INFUSING;
            case SAWING -> MekanismJEIRecipeType.SAWING;
        };
    }

    public String getRegistryNameComponent() {
        return registryNameComponent;
    }

    public String getRegistryNameComponentCapitalized() {
        String name = getRegistryNameComponent();
        return name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
    }

    public MAMachine.MAFactoryMachine<?> getBaseMachine() {
        return baseMachine.get();
    }

    public BlockRegistryObject<?, ?> getBaseBlock() {
        return baseBlock.get();
    }

    @Override
    public String getTranslationKey() {
        return translationKey;
    }
}