package io.github.masyumero.mekanismavaritia.mixin.avaritia;

import com.google.common.collect.ImmutableList;
import committee.nova.mods.avaritia.common.item.singularity.Singularity;
import committee.nova.mods.avaritia.init.registry.ModSingularities;
import io.github.masyumero.mekanismavaritia.common.registry.MASingularities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = ModSingularities.class, remap = false)
public abstract class MixinModSingularities {
    @Inject(method = "getDefaults", at = @At("RETURN"), cancellable = true)
    private static void getDefaultsInject(CallbackInfoReturnable<List<Singularity>> cir) {
        List<Singularity> original = cir.getReturnValue();
        List<Singularity> modified = new ImmutableList.Builder<Singularity>()
                .addAll(original)
                .add(MASingularities.CRYSTALLINE_ALLOY)
                .add(MASingularities.ANTIMATTER_PERRET)
                .build();
        cir.setReturnValue(modified);
    }
}
