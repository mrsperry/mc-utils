package io.github.mrsperry.mcutils.types;

import com.google.common.collect.Lists;

import org.bukkit.Material;

import java.util.ArrayList;

public class CropTypes {
    private static ArrayList<Material> harvestable = Lists.newArrayList(
        Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.BEETROOTS, Material.NETHER_WART
    );

    private static ArrayList<Material> breakable = Lists.newArrayList(
        Material.MELON,  Material.PUMPKIN,      Material.COCOA_BEANS,    Material.SUGAR_CANE,  Material.BAMBOO,
        Material.CACTUS, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM
    );

    private static ArrayList<Material> clickable = Lists.newArrayList(
        Material.SWEET_BERRY_BUSH
    );

    public static ArrayList<Material> getAllTypes() {
        ArrayList<Material> types = CropTypes.harvestable;
        types.addAll(CropTypes.breakable);
        types.addAll(CropTypes.clickable);

        return types;
    }

    public static ArrayList<Material> getHarvestableTypes() {
        return CropTypes.harvestable;
    }

    public static ArrayList<Material> getBreakableTypes() {
        return CropTypes.breakable;
    }
    public static ArrayList<Material> getClickableTypes() {
        return CropTypes.clickable;
    }
}
