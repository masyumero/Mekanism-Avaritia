package io.github.masyumero.mekavaritia.common.capabilities;

import io.github.masyumero.mekavaritia.api.IMAAlloyInteraction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MACapabilities {

    private MACapabilities() {}

    public static final Capability<IMAAlloyInteraction> MA_ALLOY_INTERACTION = CapabilityManager.get(new CapabilityToken<>() {});
}

