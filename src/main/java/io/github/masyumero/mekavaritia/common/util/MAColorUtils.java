package io.github.masyumero.mekavaritia.common.util;

public class MAColorUtils {
    public static int red(int color) {
        return color >> 16 & 255;
    }

    public static int green(int color) {
        return color >> 8 & 255;
    }

    public static int blue(int color) {
        return color & 255;
    }

    public static int[] getRGBColor(int color) {
        return new int[] {red(color), green(color), blue(color)};
    }

    public static int getInterpolatedColor(int[] color1, int[] color2, float ratio) {
        int red1 = color1[0];
        int green1 = color1[1];
        int blue1 = color1[2];

        int red2 = color2[0];
        int green2 = color2[1];
        int blue2 = color2[2];

        int red = (int) (red1 + ratio * (red2 - red1));
        int green = (int) (green1 + ratio * (green2 - green1));
        int blue = (int) (blue1 + ratio * (blue2 - blue1));

        return (red << 16) | (green << 8) | blue;
    }

    public static int getInterpolatedColor(float ratio, int[] startColor, int[] lastColor, int[]... moreColors) {
        int totalColors = 2 + moreColors.length;
        int[][] colors = new int[totalColors][];
        colors[0] = startColor;
        colors[totalColors - 1] = lastColor;
        System.arraycopy(moreColors, 0, colors, 1, moreColors.length);

        float clamped = Math.max(0.0f, Math.min(1.0f, ratio));
        float scaled = clamped * (totalColors - 1);
        int index = (int) Math.floor(scaled);

        if (index >= totalColors - 1) {
            int[] last = colors[totalColors - 1];
            return (last[0] << 16) | (last[1] << 8) | last[2];
        }

        float localRatio = scaled - index;
        int[] c1 = colors[index];
        int[] c2 = colors[index + 1];

        int red = (int) (c1[0] + localRatio * (c2[0] - c1[0]));
        int green = (int) (c1[1] + localRatio * (c2[1] - c1[1]));
        int blue = (int) (c1[2] + localRatio * (c2[2] - c1[2]));

        return (red << 16) | (green << 8) | blue;
    }
}
