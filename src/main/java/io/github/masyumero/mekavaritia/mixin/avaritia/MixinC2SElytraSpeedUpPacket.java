package io.github.masyumero.mekavaritia.mixin.avaritia;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import committee.nova.mods.avaritia.common.net.C2SElytraSpeedUpPacket;
import io.github.masyumero.mekavaritia.common.content.gear.mekasuit.ModuleInfiniteElytraUnit;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = C2SElytraSpeedUpPacket.class,remap = false)
public class MixinC2SElytraSpeedUpPacket {

    @Definition(id = "hasInfinityElytraEquipped", method = "Lcommittee/nova/mods/avaritia/util/InfinityElytraUtils;hasInfinityElytraEquipped(Lnet/minecraft/world/entity/player/Player;)Z")
    @Definition(id = "player", local = @Local(type = ServerPlayer.class, name = "player"))
    @Expression("hasInfinityElytraEquipped(player)")
    @ModifyExpressionValue(method = "lambda$run$0", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
    private boolean runInject(boolean original, @Local(name = "player") ServerPlayer player) {
        IModule<ModuleInfiniteElytraUnit> infiniteElytraUnit = MAModuleUtil.getModule(player.getItemBySlot(EquipmentSlot.CHEST), MAModules.INFINITY_ELYTRA_UNIT);
        return original || (infiniteElytraUnit != null && infiniteElytraUnit.isEnabled());
    }
}
