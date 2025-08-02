package io.github.masyumero.mekanismavaritia.mixin.avaritia;

import committee.nova.mods.avaritia.common.net.C2SElytraSpeedUpPacket;
import io.github.masyumero.mekanismavaritia.common.content.gear.mekasuit.ModuleInfiniteElytraUnit;
import io.github.masyumero.mekanismavaritia.common.registry.MAModules;
import io.github.masyumero.mekanismavaritia.common.util.MAModuleUtil;
import mekanism.api.gear.IModule;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = C2SElytraSpeedUpPacket.class,remap = false)
public class MixinC2SElytraSpeedUpPacket {

    @Inject(method = "run",at = @At(value = "INVOKE", target = "Lnet/minecraftforge/network/NetworkEvent$Context;enqueueWork(Ljava/lang/Runnable;)Ljava/util/concurrent/CompletableFuture;"))
    private void runInject(Supplier<NetworkEvent.Context> ctx, CallbackInfo ci) {
        ServerPlayer player = ctx.get().getSender();
        IModule<ModuleInfiniteElytraUnit> infiniteElytraUnit = MAModuleUtil.getModule(player.getItemBySlot(EquipmentSlot.CHEST), MAModules.INFINITY_ELYTRA_UNIT);
        if (player == null) return;
        if (infiniteElytraUnit != null && infiniteElytraUnit.isEnabled() && player.isFallFlying()) {
            player.serverLevel().addFreshEntity(new FireworkRocketEntity(player.serverLevel(), Items.AIR.getDefaultInstance(), player));
        }
    }
}
