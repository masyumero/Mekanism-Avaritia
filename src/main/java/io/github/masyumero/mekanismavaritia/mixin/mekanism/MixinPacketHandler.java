package io.github.masyumero.mekanismavaritia.mixin.mekanism;

import io.github.masyumero.mekanismavaritia.common.network.to_server.MAPacketGuiInteract;
import mekanism.common.network.BasePacketHandler;
import mekanism.common.network.PacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PacketHandler.class, remap = false)
public abstract class MixinPacketHandler extends BasePacketHandler {
    @Inject(method = "initialize", at = @At("HEAD"))
    private void initialize(CallbackInfo ci) {
        registerClientToServer(MAPacketGuiInteract.class, MAPacketGuiInteract::decode);
    }
}
