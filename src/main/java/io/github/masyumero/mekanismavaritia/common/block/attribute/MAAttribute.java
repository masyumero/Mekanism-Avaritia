package io.github.masyumero.mekanismavaritia.common.block.attribute;

import io.github.masyumero.mekanismavaritia.api.tier.IMATier;
import io.github.masyumero.mekanismavaritia.api.tier.MATier;
import mekanism.common.block.attribute.Attribute;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public interface MAAttribute extends Attribute {
    @Nullable
    @SuppressWarnings("unchecked")
    static <TIER extends IMATier> TIER getTier(Block block, Class<TIER> tierClass) {
        MAAttributeTier<TIER> attr = Attribute.get(block, MAAttributeTier.class);
        return attr == null ? null : attr.tier();
    }

    @Nullable
    static MATier getMATier(Block block) {
        MAAttributeTier<?> attr = Attribute.get(block, MAAttributeTier.class);
        return attr == null ? null : attr.tier().getMATier();
    }
}
