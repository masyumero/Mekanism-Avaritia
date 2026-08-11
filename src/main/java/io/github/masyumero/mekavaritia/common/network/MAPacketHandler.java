package io.github.masyumero.mekavaritia.common.network;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.common.network.to_server.MAPacketGuiInteract;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.common.network.BasePacketHandler;
import net.minecraftforge.network.simple.SimpleChannel;

public class MAPacketHandler extends BasePacketHandler {

    private final SimpleChannel netHandler = createChannel(MAUtils.rl(MekanismAvaritia.MODID), MekanismAvaritia.instance.versionNumber);

    @Override
    protected SimpleChannel getChannel() {
        return netHandler;
    }

    @Override
    public void initialize() {
        registerClientToServer(MAPacketGuiInteract.class, MAPacketGuiInteract::decode);
    }
}
