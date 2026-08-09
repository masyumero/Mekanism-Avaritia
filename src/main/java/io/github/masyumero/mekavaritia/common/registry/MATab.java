package io.github.masyumero.mekavaritia.common.registry;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.MekanismAvaritiaLang;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class MATab {
    public static void register(IEventBus eventBus) {
        TAB.register(eventBus);
    }

    public static final CreativeTabDeferredRegister TAB = new CreativeTabDeferredRegister(MekanismAvaritia.MODID, MATab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_AVARITIA_TAB = TAB.registerMain(MekanismAvaritiaLang.TAB, MAItems.INFINITY_ALLOY, builder ->
            builder.displayItems((displayParameters, output) -> {
                CreativeTabDeferredRegister.addToDisplay(MABlocks.BLOCK, output);
                CreativeTabDeferredRegister.addToDisplay(MAItems.ITEM, output);
            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
