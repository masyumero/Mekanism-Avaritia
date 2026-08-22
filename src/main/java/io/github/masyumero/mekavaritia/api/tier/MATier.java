package io.github.masyumero.mekavaritia.api.tier;

import io.github.masyumero.mekavaritia.common.util.MAColorUtils;
import lombok.Getter;
import mekanism.api.SupportsColorMap;
import mekanism.api.math.MathUtils;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.IntSupplier;

public enum MATier implements StringRepresentable, SupportsColorMap {
    PRISMATIC("prismatic", MATierColorMap.prismaticColor, MapColor.COLOR_MAGENTA),
    FLARE("flare", MATierColorMap.flareColor, MapColor.COLOR_RED),
    NEURAL("neural", MATierColorMap.neuralColor, MapColor.COLOR_BLACK),
    ETERNAL("eternal", MATierColorMap.eternalColor, MapColor.COLOR_CYAN),;

    private static final MATier[] TIERS = values();

    private final String name;
    @Getter
    private final MapColor mapColor;
    @Getter
    private final IntSupplier rgbSupplier;
    private TextColor textColor;
    private int[] rgbCode;

    MATier(String name, IntSupplier rgbCode, MapColor mapColor) {
        this.name = name;
        this.mapColor = mapColor;
        this.rgbSupplier = rgbCode;
        setColorFromAtlas(MAColorUtils.getRGBColor(rgbCode.getAsInt()));
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