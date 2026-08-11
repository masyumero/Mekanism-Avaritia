package io.github.masyumero.mekavaritia;

import com.mojang.logging.LogUtils;
import io.github.masyumero.mekavaritia.common.MATags;
import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.integration.MAAddons;
import io.github.masyumero.mekavaritia.common.network.MAPacketHandler;
import io.github.masyumero.mekavaritia.common.recipe.MARecipeType;
import io.github.masyumero.mekavaritia.common.registry.*;
import mekanism.api.MekanismIMC;
import mekanism.common.base.IModModule;
import mekanism.common.lib.Version;
import meranha.mekaweapons.MekaWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MekanismAvaritia.MODID)
public class MekanismAvaritia implements IModModule {

    public static final String MODID = "mekanism_avaritia";
    public static final String MODNAME = "Mekanism:Avaritia";
    public static final Logger LOGGER = LogUtils.getLogger();

    public final Version versionNumber;

    private final MAPacketHandler packetHandler;

    public static MekanismAvaritia instance;

    @SuppressWarnings("removal")
    public MekanismAvaritia(FMLJavaModLoadingContext context) {
        instance = this;
        IEventBus modEventBus = context.getModEventBus();
        ModContainer modContainer = context.getContainer();
        versionNumber = new Version(modContainer);

        modEventBus.addListener(this::imcQueue);
        modEventBus.addListener(this::commonSetup);
        LoadConfig.registerConfigs(ModLoadingContext.get());
        MAItems.register(modEventBus);
        MABlocks.register(modEventBus);
        MAInfuseTypes.register(modEventBus);
        MAGases.register(modEventBus);
        MARecipeSerializers.register(modEventBus);
        MARecipeType.register(modEventBus);
        MATileEntityTypes.register(modEventBus);
        MAContainerTypes.register(modEventBus);
        MATab.register(modEventBus);
        MAModules.register(modEventBus);

        packetHandler = new MAPacketHandler();
    }

    public static MAPacketHandler packetHandler() {
        return instance.packetHandler;
    }

    @SuppressWarnings("removal")
    public static ResourceLocation rl(String path){
        return new ResourceLocation(MekanismAvaritia.MODID, path);
    }

    private void imcQueue(InterModEnqueueEvent event) {
        MekanismIMC.addModulesToAll(MAModules.INFINITY_ENERGY_UNIT, MAModules.COSMIC_UNIT);
        MekanismIMC.addMekaSuitModules(MAModules.INFINITY_ELYTRA_UNIT);
        MekanismIMC.addMekaSuitHelmetModules(MAModules.NEBULIGHT_UNIT);
        MekanismIMC.addMekaSuitBodyarmorModules(MAModules.CELESTIAL_UNIT);
        MekanismIMC.addMekaSuitPantsModules(MAModules.STARFEAST_UNIT);
        MekanismIMC.addMekaSuitBootsModules(MAModules.LIGHTSPEED_UNIT);
        MekanismIMC.addMekaToolModules(MAModules.INFINITY_EXCAVATION_ESCALATION_UNIT, MAModules.INFINITY_ATTACK_AMPLIFICATION_UNIT);
        if (MAAddons.MEKAWEAPONS.isLoaded()) {
            MekaWeapons.addModules(MekaWeapons.ADD_MEKATANA_MODULES, MAModules.INFINITY_ENERGY_UNIT, MAModules.COSMIC_STRIKE_UNIT);
            MekaWeapons.addModules(MekaWeapons.ADD_MEKABOW_MODULES, MAModules.INFINITY_ENERGY_UNIT, MAModules.CELESTIAL_SHOT_UNIT, MAModules.INFINITY_DAMAGE_UNIT);
            MekaWeapons.addModules(MekaWeapons.ADD_MEKAGUN_MODULES, MAModules.INFINITY_ENERGY_UNIT, MAModules.INFINITY_DAMAGE_UNIT);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        packetHandler.initialize();
        MATags.init();
    }

    @Override
    public Version getVersion() {
        return versionNumber;
    }

    @Override
    public String getName() {
        return "Avaritia";
    }

    @Override
    public void resetClient() {}
}
