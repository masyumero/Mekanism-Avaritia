package io.github.masyumero.mekavaritia.common.item;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import lombok.Getter;
import mekanism.api.text.TextComponentUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
public class MATieredItem extends Item {

    private final MATier tier;

    public MATieredItem(MATier tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        TextColor color = TextColor.fromRgb(tier.getRgbSupplier().getAsInt());
        return TextComponentUtil.build(color, super.getName(stack));
    }
}
