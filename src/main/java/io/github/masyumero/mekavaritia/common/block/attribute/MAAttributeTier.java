package io.github.masyumero.mekavaritia.common.block.attribute;

import io.github.masyumero.mekavaritia.api.tier.IMATier;
import mekanism.common.MekanismLang;
import mekanism.common.content.blocktype.BlockType;

import java.util.HashMap;
import java.util.Map;

public record MAAttributeTier<TIER extends IMATier>(TIER tier) implements MAAttribute {
    private static final Map<IMATier, BlockType> typeCache = new HashMap<>();

    public static <T extends IMATier> BlockType getPassthroughType(T tier) {
        return typeCache.computeIfAbsent(tier, t -> BlockType.BlockTypeBuilder.createBlock(MekanismLang.EMPTY).with(new MAAttribute[]{new MAAttributeTier<>(t)}).build());
    }
}
