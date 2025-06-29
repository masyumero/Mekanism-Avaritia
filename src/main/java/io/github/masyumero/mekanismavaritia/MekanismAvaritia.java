package io.github.masyumero.mekanismavaritia;

import com.mojang.logging.LogUtils;
import io.github.masyumero.mekanismavaritia.common.config.LoadConfig;
import io.github.masyumero.mekanismavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekanismavaritia.common.registry.*;
import mekanism.api.MekanismIMC;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MekanismAvaritia.MODID)
public class MekanismAvaritia {

    public static final String MODID = "mekanismavaritia";
    public static final String MODNAME = "MekanismAvaritia";

    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public MekanismAvaritia() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::imcQueue);
        LoadConfig.registerConfigs(ModLoadingContext.get());
        MAItems.ITEM.register(modEventBus);
        MABlocks.BLOCK.register(modEventBus);
        MAInfuseTypes.INFUSE_TYPES.register(modEventBus);
//        MAGases.GASES.register(modEventBus);
        MARecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        MARecipeType.RECIPE_TYPES.register(modEventBus);
        MATileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        MAContainerTypes.CONTAINER_TYPES.register(modEventBus);
        MATab.TAB.register(modEventBus);
        MAModules.MODULES.createAndRegister(modEventBus);
    }

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path){
        return new ResourceLocation(MekanismAvaritia.MODID, path);
    }

    private void imcQueue(InterModEnqueueEvent event) {
        MekanismIMC.addModulesToAll(MAModules.INFINITY_ENERGY_UNIT);
        MekanismIMC.addMekaToolModules(MAModules.COSMIC_UNIT);
    }
}
