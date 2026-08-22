package io.github.masyumero.mekavaritia.mixin.mekanism;

import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterStack;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = LogisticalTransporterBase.class, remap = false)
public interface InvokerLogisticalTransporterBase {

    @Invoker("recalculate")
    boolean recalculate(int stackId, TransporterStack stack, BlockPos from);

    @Invoker("entityEntering")
    void entityEntering(TransporterStack stack, int progress);
}
