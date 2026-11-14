package io.github.masyumero.mekavaritia.mixin.mekanism;

import io.github.masyumero.mekavaritia.api.mixin.IMixinLogisticalTransporterBase;
import mekanism.common.content.network.InventoryNetwork;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.network.transmitter.Transmitter;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraftforge.items.IItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = LogisticalTransporterBase.class, remap = false)
public abstract class MixinLogisticalTransporterBase extends Transmitter<IItemHandler, InventoryNetwork, LogisticalTransporterBase> implements IMixinLogisticalTransporterBase {

    public MixinLogisticalTransporterBase(TileEntityTransmitter transmitterTile, TransmissionType... transmissionTypes) {
        super(transmitterTile, transmissionTypes);
    }

    @Shadow
    protected abstract void entityEntering(TransporterStack stack, int progress);

    @Shadow
    protected abstract boolean recalculate(int stackId, TransporterStack stack, BlockPos from);

    @Unique
    public void mekavaritia$getEntering(TransporterStack stack, int progress) {
        entityEntering(stack, progress);
    }

    @Unique
    public boolean mekavaritia$getRecalculate(int stackId, TransporterStack stack, BlockPos from) {
        return recalculate(stackId, stack, from);
    }
}
