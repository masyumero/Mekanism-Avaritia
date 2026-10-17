package io.github.masyumero.mekavaritia.common.item.block;

import io.github.masyumero.mekavaritia.client.render.RenderMAPropertiesProvider;
import io.github.masyumero.mekavaritia.common.block.MABlockEnergyCube;
import io.github.masyumero.mekavaritia.common.block.attribute.MAAttribute;
import io.github.masyumero.mekavaritia.common.capabilities.energy.item.MARateLimitEnergyHandler;
import io.github.masyumero.mekavaritia.common.config.LoadConfig;
import io.github.masyumero.mekavaritia.common.tier.MAECTier;
import mekanism.api.NBTConstants;
import mekanism.api.RelativeSide;
import mekanism.api.text.EnumColor;
import mekanism.common.MekanismLang;
import mekanism.common.capabilities.ItemCapabilityWrapper;
import mekanism.common.capabilities.energy.item.ItemStackEnergyHandler;
import mekanism.common.config.MekanismConfig;
import mekanism.common.item.interfaces.IItemSustainedInventory;
import mekanism.common.lib.transmitter.TransmissionType;
import mekanism.common.registration.impl.CreativeTabDeferredRegister;
import mekanism.common.tile.component.config.DataType;
import mekanism.common.util.EnumUtils;
import mekanism.common.util.ItemDataUtils;
import mekanism.common.util.NBTUtils;
import mekanism.common.util.StorageUtils;
import mekanism.common.util.text.EnergyDisplay;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class MAItemBlockEnergyCube extends MAItemBlockTooltip<MABlockEnergyCube> implements IItemSustainedInventory, CreativeTabDeferredRegister.ICustomCreativeTabContents {

    public MAItemBlockEnergyCube(MABlockEnergyCube block) {
        super(block);
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(RenderMAPropertiesProvider.maEnergyCube());
    }

    @NotNull
    @Override
    public MAECTier getMATier() {
        return MAAttribute.getTier(getBlock(), MAECTier.class);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        StorageUtils.addStoredEnergy(stack, tooltip, true);
        tooltip.add(MekanismLang.CAPACITY.translateColored(EnumColor.INDIGO, EnumColor.GRAY, EnergyDisplay.of(getMATier().getMaxEnergy())));
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    protected void addTypeDetails(@NotNull ItemStack stack, Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        //Don't call super so that we can exclude the stored energy from being shown as we show it in hover text
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return StorageUtils.getEnergyBarWidth(stack);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return MekanismConfig.client.energyColor.get();
    }

    @Override
    public void addItems(CreativeModeTab.Output tabOutput) {
        MAECTier tier = getMATier();
        if (LoadConfig.STORAGE_CONFIG.eternalEnergyCubeLikeCreativeEnergyCubeTank.get() && getMATier() != MAECTier.ETERNAL) {
            //Add the empty and charged variants
            tabOutput.accept(withEnergyCubeSideConfig(DataType.INPUT));
            tabOutput.accept(StorageUtils.getFilledEnergyVariant(withEnergyCubeSideConfig(DataType.OUTPUT), tier.getMaxEnergy()));
        } else {
            tabOutput.accept(StorageUtils.getFilledEnergyVariant(new ItemStack(this), tier.getMaxEnergy()));
        }
    }

    @Override
    public boolean addDefault() {
        return LoadConfig.STORAGE_CONFIG.eternalEnergyCubeLikeCreativeEnergyCubeTank.get() && getMATier() != MAECTier.ETERNAL;
    }

    private ItemStack withEnergyCubeSideConfig(DataType dataType) {
        CompoundTag sideConfig = new CompoundTag();
        for (RelativeSide side : EnumUtils.SIDES) {
            NBTUtils.writeEnum(sideConfig, NBTConstants.SIDE + side.ordinal(), dataType);
        }
        CompoundTag configNBT = new CompoundTag();
        configNBT.put(NBTConstants.CONFIG + TransmissionType.ENERGY.ordinal(), sideConfig);
        ItemStack stack = new ItemStack(this);
        ItemDataUtils.setCompound(stack, NBTConstants.COMPONENT_CONFIG, configNBT);
        return stack;
    }

    @Override
    protected void gatherCapabilities(List<ItemCapabilityWrapper.ItemCapability> capabilities, ItemStack stack, CompoundTag nbt) {
        super.gatherCapabilities(capabilities, stack, nbt);
        ItemCapabilityWrapper.ItemCapability capability = MARateLimitEnergyHandler.create(getMATier());
        int index = IntStream.range(0, capabilities.size()).filter(i -> capabilities.get(i) instanceof ItemStackEnergyHandler).findFirst().orElse(-1);
        if (index != -1) {
            //This is likely always the path that will be taken
            capabilities.set(index, capability);
        } else {
            capabilities.add(capability);
        }
    }
}