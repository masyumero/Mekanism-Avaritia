package io.github.masyumero.mekavaritia.api.mixin;

import mekanism.common.content.transporter.TransporterStack;
import net.minecraft.core.BlockPos;

public interface IMixinLogisticalTransporterBase {
    void mekavaritia$getEntering(TransporterStack stack, int progress);

    boolean mekavaritia$getRecalculate(int stackId, TransporterStack stack, BlockPos from);
}
