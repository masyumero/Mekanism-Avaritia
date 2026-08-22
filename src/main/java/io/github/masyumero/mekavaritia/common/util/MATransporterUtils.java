package io.github.masyumero.mekavaritia.common.util;

import io.github.masyumero.mekavaritia.api.tier.MATier;
import io.github.masyumero.mekavaritia.common.content.network.transmitter.MALogisticalTransporter;
import mekanism.api.text.EnumColor;
import mekanism.api.tier.BaseTier;
import mekanism.common.content.network.transmitter.LogisticalTransporterBase;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.util.TransporterUtils;
import net.minecraft.core.Direction;

import java.util.List;

public class MATransporterUtils {

    public static final List<EnumColor> colors = TransporterUtils.colors;

    public static void incrementColor(MALogisticalTransporter tile) {
        EnumColor color = tile.getColor();
        if (color == null) {
            tile.setColor(colors.get(0));
        } else {
            int index = colors.indexOf(color);
            if (index == colors.size() - 1) {
                tile.setColor(null);
            } else {
                tile.setColor(colors.get(index + 1));
            }
        }
    }

    public static float[] getStackPosition(LogisticalTransporterBase transporter, TransporterStack stack, float partial) {
        Direction side = stack.getSide(transporter);
        float progress = ((float)stack.progress + partial) / 100.0F - 0.5F;
        return new float[]{0.5F + (float)side.getStepX() * progress, 0.25F + (float)side.getStepY() * progress, 0.5F + (float)side.getStepZ() * progress};
    }

    public static int getColorFromBaseTier(BaseTier tier) {
        if (!(tier.ordinal() < 4)) {
            return MATier.PRISMATIC.getRgbSupplier().getAsInt();
        }
        return switch (tier) {
            case ADVANCED -> MATier.FLARE.getRgbSupplier().getAsInt();
            case ELITE -> MATier.NEURAL.getRgbSupplier().getAsInt();
            case ULTIMATE -> MATier.ETERNAL.getRgbSupplier().getAsInt();
            default -> MATier.PRISMATIC.getRgbSupplier().getAsInt(); //BASIC & CREATIVE
        };
    }
}
