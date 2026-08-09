package io.github.masyumero.mekavaritia.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class MATextUtils {

    public static String swapWordsToEnglishName(Object internalName) {
        return swapWords(toEnglishName(internalName));
    }

    public static String toEnglishName(Object internalName) {
        return Arrays.stream(internalName.toString().toLowerCase(Locale.ROOT).split("_")).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static String swapWords(Object internalName) {
        String[] parts = internalName.toString().trim().split("\\s+");
        if (parts.length != 2) return internalName.toString();
        return parts[1] + " " + parts[0];
    }

}
