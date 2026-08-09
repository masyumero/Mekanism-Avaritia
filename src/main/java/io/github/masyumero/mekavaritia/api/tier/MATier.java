package io.github.masyumero.mekavaritia.api.tier;

import lombok.Getter;
import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum MATier implements StringRepresentable, SupportsColorMap {
    PRISMATIC("prismatic", new int[]{40, 173, 255}, MapColor.COLOR_MAGENTA),
    FLARE("flare", new int[]{255, 0, 0}, MapColor.COLOR_RED),
    NEURAL("neural", new int[]{50, 50, 50}, MapColor.COLOR_BLACK),
    ETERNAL("eternal", new int[]{0, 255, 255}, MapColor.COLOR_CYAN),;

    private static final MATier[] TIERS = values();

    private final String name;
    @Getter
    private final MapColor mapColor;
    private TextColor textColor;
    private int[] rgbCode;

    MATier(String name, int[] rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        setColorFromAtlas(rgbCode);
    }

    public String getSimpleName() {
        return name;
    }

    public String getLowerName() {
        return getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    public int[] getRgbCode() {
        return rgbCode;
    }

    @Override
    public void setColorFromAtlas(int[] color) {
        this.rgbCode = color;
        this.textColor = TextColor.fromRgb(rgbCode[0] << 16 | rgbCode[1] << 8 | rgbCode[2]);
    }

    public TextColor getColor() {
        return this.textColor;
    }

    @NotNull
    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static MATier byIndexStatic(int index) {
        return MathUtils.getByIndexMod(TIERS, index);
    }
}