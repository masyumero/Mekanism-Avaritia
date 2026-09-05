package io.github.masyumero.mekavaritia.mixin.mekanism.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixEnergyContainer;
import io.github.masyumero.mekavaritia.api.mixin.IMixinMatrixEnergyContainer;
import io.github.masyumero.mekavaritia.api.mixin.impl.ImplMixinMatrixEnergyContainer;
import io.github.masyumero.mekavaritia.common.tier.MAIPTier;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.energy.MachineEnergyContainer;
import mekanism.common.content.matrix.MatrixEnergyContainer;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Set;

public abstract class MixinTargetMatrixEnergyContainer {

    @NothingNullByDefault
    @Mixin(value = MatrixEnergyContainer.class, remap = false)
    public static abstract class MixinMatrixEnergyContainer implements IMixinMatrixEnergyContainer {

        @Shadow
        private FloatingLong storageCap;
        @Shadow
        private FloatingLong cachedTotal;
        @Shadow
        private FloatingLong transferCap;
        @Shadow
        @Final
        private Set<BlockPos> invalidPositions;
        @Unique
        private final Map<BlockPos, MAIPTier> mekavaritia$providers = new Object2ObjectOpenHashMap<>();
        @Unique
        private final Map<BlockPos, IEnergyContainer> mekavaritia$cells = new Object2ObjectOpenHashMap<>();

        @Override
        public void mekavaritia$addMACell(BlockPos pos, TileEntityMAInductionCell cell) {
            MachineEnergyContainer<TileEntityMAInductionCell> energyContainer = cell.getEnergyContainer();
            mekavaritia$cells.put(pos, energyContainer);
            storageCap = storageCap.plusEqual(energyContainer.getMaxEnergy());
            cachedTotal = cachedTotal.plusEqual(energyContainer.getEnergy());
        }

        @Override
        public void mekavaritia$addMAProvider(BlockPos pos, TileEntityMAInductionProvider provider) {
            mekavaritia$providers.put(pos, provider.tier);
            transferCap = transferCap.plusEqual(provider.tier.getOutput());
        }

        @Inject(method = "removeInternal", at = @At("TAIL"))
        private void removeInternalInject(BlockPos pos, CallbackInfo ci) {
            mekavaritia$removeInternal(pos);
        }

        @Override
        public void mekavaritia$removeInternal(BlockPos pos) {
            if (invalidPositions.add(pos)) {
                if (mekavaritia$providers.containsKey(pos)) {
                    //It is a provider
                    transferCap = transferCap.minusEqual(mekavaritia$providers.get(pos).getOutput());
                } else if (mekavaritia$cells.containsKey(pos)) {
                    //It is a cell
                    IEnergyContainer cellContainer = mekavaritia$cells.get(pos);
                    storageCap = storageCap.plusEqual(cellContainer.getMaxEnergy());
                    cachedTotal = cachedTotal.minusEqual(cellContainer.getEnergy());
                }
            }
        }

        @Inject(method = "invalidate", at = @At("TAIL"))
        private void invalidateInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$invalidate(mekavaritia$providers, mekavaritia$cells);
        }

        @Inject(method = "tick", at = @At("HEAD"))
        private void tickInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$tick(invalidPositions, mekavaritia$providers, mekavaritia$cells);
        }

        @Inject(method = "addEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;plusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;"))
        private void addEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$addEnergy(energy, mekavaritia$cells);
        }

        @Inject(method = "removeEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;minusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;", ordinal = 0))
        private void removeEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$removeEnergy(energy, mekavaritia$cells);
        }

        @Redirect(method = "getCells", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getCellsRedirect(Map<?, ?> instance) {
            return instance.size() + mekavaritia$cells.size();
        }

        @Redirect(method = "getProviders", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getProviderRedirect(Map<?, ?> instance) {
            return instance.size() + mekavaritia$providers.size();
        }
    }

    @NothingNullByDefault
    @Mixin(value = ExtraMatrixEnergyContainer.class, remap = false)
    public static abstract class MixinExtraMatrixEnergyContainer implements IMixinMatrixEnergyContainer {

        @Shadow
        private FloatingLong storageCap;
        @Shadow
        private FloatingLong cachedTotal;
        @Shadow
        private FloatingLong transferCap;
        @Shadow
        @Final
        private Set<BlockPos> invalidPositions;
        @Unique
        private final Map<BlockPos, MAIPTier> mekavaritia$providers = new Object2ObjectOpenHashMap<>();
        @Unique
        private final Map<BlockPos, IEnergyContainer> mekavaritia$cells = new Object2ObjectOpenHashMap<>();

        @Override
        public void mekavaritia$addMACell(BlockPos pos, TileEntityMAInductionCell cell) {
            MachineEnergyContainer<TileEntityMAInductionCell> energyContainer = cell.getEnergyContainer();
            mekavaritia$cells.put(pos, energyContainer);
            storageCap = storageCap.plusEqual(energyContainer.getMaxEnergy());
            cachedTotal = cachedTotal.plusEqual(energyContainer.getEnergy());
        }

        @Override
        public void mekavaritia$addMAProvider(BlockPos pos, TileEntityMAInductionProvider provider) {
            mekavaritia$providers.put(pos, provider.tier);
            transferCap = transferCap.plusEqual(provider.tier.getOutput());
        }

        @Inject(method = "removeInternal", at = @At("TAIL"))
        private void removeInternalInject(BlockPos pos, CallbackInfo ci) {
            mekavaritia$removeInternal(pos);
        }

        @Override
        public void mekavaritia$removeInternal(BlockPos pos) {
            if (invalidPositions.add(pos)) {
                if (mekavaritia$providers.containsKey(pos)) {
                    //It is a provider
                    transferCap = transferCap.minusEqual(mekavaritia$providers.get(pos).getOutput());
                } else if (mekavaritia$cells.containsKey(pos)) {
                    //It is a cell
                    IEnergyContainer cellContainer = mekavaritia$cells.get(pos);
                    storageCap = storageCap.plusEqual(cellContainer.getMaxEnergy());
                    cachedTotal = cachedTotal.minusEqual(cellContainer.getEnergy());
                }
            }
        }

        @Inject(method = "invalidate", at = @At("TAIL"))
        private void invalidateInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$invalidate(mekavaritia$providers, mekavaritia$cells);
        }

        @Inject(method = "tick", at = @At("HEAD"))
        private void tickInject(CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$tick(invalidPositions, mekavaritia$providers, mekavaritia$cells);
        }

        @Inject(method = "addEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;plusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;"))
        private void addEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$addEnergy(energy, mekavaritia$cells);
        }

        @Inject(method = "removeEnergy", at = @At(value = "INVOKE", target = "Lmekanism/api/math/FloatingLong;minusEqual(Lmekanism/api/math/FloatingLong;)Lmekanism/api/math/FloatingLong;", ordinal = 0))
        private void removeEnergyInject(FloatingLong energy, CallbackInfo ci) {
            ImplMixinMatrixEnergyContainer.mekavaritia$removeEnergy(energy, mekavaritia$cells);
        }

        @Redirect(method = "getCells", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getCellsRedirect(Map<?, ?> instance) {
            return instance.size() + mekavaritia$cells.size();
        }

        @Redirect(method = "getProviders", at = @At(value = "INVOKE", target = "Ljava/util/Map;size()I"))
        private int getProviderRedirect(Map<?, ?> instance) {
            return instance.size() + mekavaritia$providers.size();
        }
    }
}
