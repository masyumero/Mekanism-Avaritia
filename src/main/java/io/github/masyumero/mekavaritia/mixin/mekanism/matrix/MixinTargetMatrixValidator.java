package io.github.masyumero.mekavaritia.mixin.mekanism.matrix;

import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixMultiblockData;
import com.jerry.mekanism_extras.common.content.matrix.ExtraMatrixValidator;
import io.github.masyumero.mekavaritia.api.mixin.IMixinMatrixMultiblockData;
import io.github.masyumero.mekavaritia.api.mixin.IMixinMatrixValidator;
import io.github.masyumero.mekavaritia.api.mixin.impl.ImplMixinMatrixValidator;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionCell;
import io.github.masyumero.mekavaritia.common.tile.multiblock.TileEntityMAInductionProvider;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.content.matrix.MatrixMultiblockData;
import mekanism.common.content.matrix.MatrixValidator;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.lib.multiblock.MultiblockData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

public class MixinTargetMatrixValidator {

    @Mixin(value = {
            MatrixValidator.class,
            ExtraMatrixValidator.class
    }, remap = false)
    public static abstract class MixinMatrixValidator extends CuboidStructureValidator<MultiblockData> implements IMixinMatrixValidator {

        @Unique
        private final List<TileEntityMAInductionCell> mekavaritia$cells = new ArrayList<>();
        @Unique
        private final List<TileEntityMAInductionProvider> mekavaritia$providers = new ArrayList<>();

        @Inject(method = "validateInner", at = @At(value = "TAIL"), cancellable = true)
        private void validetaInnerInject(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(ImplMixinMatrixValidator.mekavaritia$validateInner(state, chunkMap, pos, world, mekavaritia$cells, mekavaritia$providers));
        }

        @Override
        public FormationProtocol.FormationResult mekavaritia$postcheck(MultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
            mekavaritia$cells.forEach(((IMixinMatrixMultiblockData)structure)::mekavaritia$addCell);
            mekavaritia$providers.forEach(((IMixinMatrixMultiblockData)structure)::mekavaritia$addProvider);
            return FormationProtocol.FormationResult.SUCCESS;
        }
    }

    @Mixin(value = MatrixValidator.class, remap = false)
    public static abstract class MixinMatrixValidatorPostcheck {

        @Inject(method = "postcheck(Lmekanism/common/content/matrix/MatrixMultiblockData;Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;)Lmekanism/common/lib/multiblock/FormationProtocol$FormationResult;", at = @At("TAIL"), cancellable = true)
        private void postcheck(MatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap, CallbackInfoReturnable<FormationProtocol.FormationResult> cir) {
            cir.setReturnValue(((IMixinMatrixValidator)this).mekavaritia$postcheck(structure, chunkMap));
        }
    }

    @Mixin(value = ExtraMatrixValidator.class, remap = false)
    public static abstract class MixinExtraMatrixValidatorPostcheck {

        @Inject(method = "postcheck(Lcom/jerry/mekanism_extras/common/content/matrix/ExtraMatrixMultiblockData;Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;)Lmekanism/common/lib/multiblock/FormationProtocol$FormationResult;", at = @At("TAIL"), cancellable = true)
        private void postcheck(ExtraMatrixMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap, CallbackInfoReturnable<FormationProtocol.FormationResult> cir) {
            cir.setReturnValue(((IMixinMatrixValidator)this).mekavaritia$postcheck(structure, chunkMap));
        }
    }
}
