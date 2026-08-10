package io.github.masyumero.mekavaritia.client.events;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.client.gui.machine.GuiElectricNeutronCollectorMachine;
import io.github.masyumero.mekavaritia.client.gui.machine.GuiMAFactory;
import io.github.masyumero.mekavaritia.common.registry.MAContainerTypes;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import mekanism.api.gear.IModuleHelper;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismAvaritia.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    private ClientRegistration() {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterEvent event) {
        event.register(Registries.MENU, helper -> {
            ClientRegistrationUtil.registerScreen(MAContainerTypes.ELECTRIC_NEUTRON_COLLECTOR, GuiElectricNeutronCollectorMachine::new);
            ClientRegistrationUtil.registerScreen(MAContainerTypes.FACTORY, GuiMAFactory::new);
        });
    }

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        IModuleHelper moduleHelper = IModuleHelper.INSTANCE;
        moduleHelper.addMekaSuitModuleModels(MekanismAvaritia.rl("models/entity/mekasuit_modules.obj"));
        moduleHelper.addMekaSuitModuleModelSpec("infinityelytra", MAModules.INFINITY_ELYTRA_UNIT, EquipmentSlot.CHEST, LivingEntity::isFallFlying);
    }

}
