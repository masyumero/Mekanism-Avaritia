package io.github.masyumero.mekavaritia.common.item;

import io.github.masyumero.mekavaritia.api.IMAAlloyInteraction;
import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import io.github.masyumero.mekavaritia.common.capabilities.MACapabilities;
import mekanism.api.text.TextComponentUtil;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.CapabilityUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.WorldUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class ItemMAAlloy extends Item {

    private final MAAlloyTier tier;

    public ItemMAAlloy(MAAlloyTier tier) {
        super(new Properties());
        this.tier = tier;
    }

    @NotNull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player != null && MekanismConfig.general.transmitterAlloyUpgrade.get()) {
            Level world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            BlockEntity tile = WorldUtils.getTileEntity(world, pos);
            LazyOptional<IMAAlloyInteraction> capability = CapabilityUtils.getCapability(tile, MACapabilities.MA_ALLOY_INTERACTION, context.getClickedFace());
            if (capability.isPresent()) {
                if (!world.isClientSide) {
                    capability.orElseThrow(MekanismUtils.MISSING_CAP_ERROR).onMAAlloyInteraction(player, context.getItemInHand(), tier);
                }
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        TextColor color = TextColor.fromRgb(tier.getMATier().getRgbSupplier().getAsInt());
        return TextComponentUtil.build(color, super.getName(stack));
    }

    public MAAlloyTier getMATier() {
        return tier;
    }
}