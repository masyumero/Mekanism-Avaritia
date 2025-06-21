package io.github.masyumero.mekanismavaritia.common.registry;

import io.github.masyumero.mekanismavaritia.MekanismAvaritia;
import io.github.masyumero.mekanismavaritia.MekanismAvaritiaLang;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.registration.impl.CreativeTabRegistryObject;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class MATab {
    public static void register(IEventBus eventBus) {
        TAB.register(eventBus);
    }

    public static final CreativeTabDeferredRegister TAB = new CreativeTabDeferredRegister(MekanismAvaritia.MODID, MATab::addToExistingTabs);
    public static final CreativeTabRegistryObject MEKANISM_AVARITIA_EXTRAS_TAB = TAB.registerMain(MekanismAvaritiaLang.TAB, MAItem.INFINITE_ALLOY, builder ->
            builder.displayItems((displayParameters, output) -> {
                CreativeTabDeferredRegister.addToDisplay(MABlock.BLOCK, output);
                CreativeTabDeferredRegister.addToDisplay(MAItem.ITEM, output);
            })
    );

    private static void addToExistingTabs(BuildCreativeModeTabContentsEvent event) {
    }
}
