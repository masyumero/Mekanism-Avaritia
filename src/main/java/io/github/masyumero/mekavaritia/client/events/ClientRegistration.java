package io.github.masyumero.mekavaritia.client.events;

import io.github.masyumero.mekavaritia.MekanismAvaritia;
import io.github.masyumero.mekavaritia.client.gui.machine.GuiElectricNeutronCollectorMachine;
import io.github.masyumero.mekavaritia.client.gui.machine.GuiMAFactory;
import io.github.masyumero.mekavaritia.client.render.transmitter.*;
import io.github.masyumero.mekavaritia.common.registry.MABlocks;
import io.github.masyumero.mekavaritia.common.registry.MAContainerTypes;
import io.github.masyumero.mekavaritia.common.registry.MAModules;
import io.github.masyumero.mekavaritia.common.registry.MATileEntityTypes;
import io.github.masyumero.mekavaritia.common.tile.transmitter.TileEntityMALogisticalTransporter;
import io.github.masyumero.mekavaritia.common.util.MAUtils;
import mekanism.api.gear.IModuleHelper;
import mekanism.api.text.EnumColor;
import mekanism.client.ClientRegistrationUtil;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.item.TransmitterTypeDecorator;
import mekanism.common.util.WorldUtils;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = MekanismAvaritia.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistration {
    private ClientRegistration() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // universal cable
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderMAUniversalCable::new, MATileEntityTypes.PRISMATIC_UNIVERSAL_CABLE,
                MATileEntityTypes.FLARE_UNIVERSAL_CABLE, MATileEntityTypes.NEURAL_UNIVERSAL_CABLE, MATileEntityTypes.ETERNAL_UNIVERSAL_CABLE);
        // logistical transporter
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderMALogisticalTransporter::new, MATileEntityTypes.PRISMATIC_LOGISTICAL_TRANSPORTER,
                MATileEntityTypes.FLARE_LOGISTICAL_TRANSPORTER, MATileEntityTypes.NEURAL_LOGISTICAL_TRANSPORTER, MATileEntityTypes.ETERNAL_LOGISTICAL_TRANSPORTER);
        // mechanical pipe
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderMAMechanicalPipe::new, MATileEntityTypes.PRISMATIC_MECHANICAL_PIPE,
                MATileEntityTypes.FLARE_MECHANICAL_PIPE, MATileEntityTypes.NEURAL_MECHANICAL_PIPE, MATileEntityTypes.ETERNAL_MECHANICAL_PIPE);
        // pressurized tube
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderMAPressurizedTube::new, MATileEntityTypes.PRISMATIC_PRESSURIZED_TUBE,
                MATileEntityTypes.FLARE_PRESSURIZED_TUBE, MATileEntityTypes.NEURAL_PRESSURIZED_TUBE, MATileEntityTypes.ETERNAL_PRESSURIZED_TUBE);
        // thermodynamic conductor
        ClientRegistrationUtil.bindTileEntityRenderer(event, RenderMAThermodynamicConductor::new, MATileEntityTypes.PRISMATIC_THERMODYNAMIC_CONDUCTOR,
                MATileEntityTypes.FLARE_THERMODYNAMIC_CONDUCTOR, MATileEntityTypes.NEURAL_THERMODYNAMIC_CONDUCTOR, MATileEntityTypes.ETERNAL_THERMODYNAMIC_CONDUCTOR);
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Post event) {
        TextureAtlas map = event.getAtlas();
        RenderMALogisticalTransporter.onStitch(map);
        RenderMAMechanicalPipe.onStitch();
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
        moduleHelper.addMekaSuitModuleModels(MAUtils.rl("models/entity/mekasuit_modules.obj"));
        moduleHelper.addMekaSuitModuleModelSpec("infinityelytra", MAModules.INFINITY_ELYTRA_UNIT, EquipmentSlot.CHEST, LivingEntity::isFallFlying);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        ClientRegistrationUtil.registerBlockColorHandler(event, (state, world, pos, tintIndex) -> {
                    if (tintIndex == 1 && pos != null) {
                        TileEntityMALogisticalTransporter transporter = WorldUtils.getTileEntity(TileEntityMALogisticalTransporter.class, world, pos);
                        if (transporter != null) {
                            EnumColor renderColor = transporter.getTransmitter().getColor();
                            if (renderColor != null) {
                                return MekanismRenderer.getColorARGB(renderColor, 1);
                            }
                        }
                    }
                    return -1;
                }, MABlocks.PRISMATIC_LOGISTICAL_TRANSPORTER, MABlocks.FLARE_LOGISTICAL_TRANSPORTER, MABlocks.NEURAL_LOGISTICAL_TRANSPORTER,
                MABlocks.ETERNAL_LOGISTICAL_TRANSPORTER);
    }

    @SubscribeEvent
    public static void registerItemDecorations(RegisterItemDecorationsEvent event) {
        TransmitterTypeDecorator.registerDecorators(event, MABlocks.PRISMATIC_PRESSURIZED_TUBE, MABlocks.FLARE_PRESSURIZED_TUBE,
                MABlocks.NEURAL_PRESSURIZED_TUBE, MABlocks.ETERNAL_PRESSURIZED_TUBE, MABlocks.PRISMATIC_THERMODYNAMIC_CONDUCTOR,
                MABlocks.FLARE_THERMODYNAMIC_CONDUCTOR, MABlocks.NEURAL_THERMODYNAMIC_CONDUCTOR, MABlocks.ETERNAL_THERMODYNAMIC_CONDUCTOR,
                MABlocks.PRISMATIC_UNIVERSAL_CABLE, MABlocks.FLARE_UNIVERSAL_CABLE, MABlocks.NEURAL_UNIVERSAL_CABLE, MABlocks.ETERNAL_UNIVERSAL_CABLE);
    }
}
