package io.github.mrsperry.mcutils.types;

import com.google.common.collect.Lists;

import org.bukkit.Color;

import java.util.List;

public class ColorTypes {
    private static final List<Color> colors = Lists.newArrayList(
        Color.AQUA,   Color.BLACK,  Color.BLUE,   Color.FUCHSIA, Color.GRAY,
        Color.GREEN,  Color.LIME,   Color.MAROON, Color.NAVY,    Color.OLIVE,
        Color.ORANGE, Color.PURPLE, Color.RED,    Color.SILVER,  Color.TEAL,
        Color.WHITE,  Color.YELLOW
    );

    public static List<Color> getColors() {
        return ColorTypes.colors;
    }
}
