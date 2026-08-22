package io.github.masyumero.mekavaritia.api;

import io.github.masyumero.mekavaritia.api.tier.MAAlloyTier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import org.jetbrains.annotations.NotNull;

@AutoRegisterCapability
public interface IMAAlloyInteraction {

    void onMAAlloyInteraction(Player player, ItemStack itemStack, @NotNull MAAlloyTier tier);
}
