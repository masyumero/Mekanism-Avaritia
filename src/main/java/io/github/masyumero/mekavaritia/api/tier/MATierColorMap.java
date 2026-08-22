package io.github.masyumero.mekavaritia.api.tier;

import io.github.masyumero.mekavaritia.common.util.MAColorUtils;

import mekanism.api.functions.FloatSupplier;

import java.util.function.IntSupplier;

public class MATierColorMap {

    private static FloatSupplier spot = () -> {
        float spot = (float) ((System.currentTimeMillis() / 25) % 100) / 100;
        if (spot > 0.5) {
            spot = 1 - spot;
        }
        return spot * 2;
    };

    public static IntSupplier prismaticColor = () -> MAColorUtils.getInterpolatedColor(new int[]{40, 173, 255}, new int[]{30, 140, 255}, spot.getAsFloat());
    public static IntSupplier flareColor = () -> MAColorUtils.getInterpolatedColor(new int[]{255, 60, 0}, new int[]{255, 70, 0}, spot.getAsFloat());
    public static IntSupplier neuralColor = () -> MAColorUtils.getInterpolatedColor(new int[]{50, 50, 50}, new int[]{0, 0, 0}, spot.getAsFloat());
    public static IntSupplier eternalColor = () -> MAColorUtils.getInterpolatedColor(spot.getAsFloat(), new int[]{255, 153, 153}, new int[]{255, 255, 153},
            new int[]{153, 255, 153}, new int[]{153, 255, 255}, new int[]{153, 153, 255}, new int[]{255, 153, 255});
}