package io.github.mrsperry.mcutils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemParser {
    public static ItemStack parseItem(ConfigurationSection key) {
        Material material = Material.AIR;
        int amount = 0;
        String name = material.name();
        List<String> lore = new ArrayList<String>();
        HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

        if (key.isString(key.getName() + ".material")) {
            String materialString = key.getString(key.getName() + ".material");
            try {
                material = Material.valueOf(materialString.toUpperCase().replace(" ", "_"));
            } catch (Exception ex) {
                Bukkit.getLogger().warning("Could not parse material: " + materialString);
            }
        }

        if (key.isInt(key.getName() + ".amount")) {
            String itemAmount = key.getString(key.getName() + ".amount");
            try {
                amount = Integer.parseInt(itemAmount);
            } catch (Exception ex) {
                Bukkit.getLogger().warning("Could not parse item amount: " + itemAmount);
            }
        }

        if (key.isString(key.getName() + ".name")) {
            try {
                name = key.getString(key.getName() + ".name").replace("`", "\u00A7");
            } catch (Exception ex) {
                Bukkit.getLogger().info("Could not set item name: " + name);
            }
        }

        if (key.isList(key.getName() + ".lore")) {
            for (String line : key.getStringList(key.getName() + ".lore")) {
                try {
                    lore.add(line.replace("`", "\u00A7"));
                } catch (Exception ex) {
                    Bukkit.getLogger().warning("Could not add lore: " + line);
                }
            }
        }

        if (key.isList(key.getName() + ".enchantments")) {
            for (String enchantString : key.getStringList(key.getName() + ".enchantments")) {
                String[] split = enchantString.split(":");

                int level = 0;
                if (split.length == 2) {
                    try {
                        level = Integer.parseInt(split[1]);
                    } catch (Exception ex) {
                        Bukkit.getLogger().warning("Could not parse enchantment level: " + split[1]);
                    }
                }

                Enchantment enchantment = null;
                try {
                    enchantment = Enchantment.getByName(split[0]);
                } catch (Exception ex) {
                    Bukkit.getLogger().warning("Could not parse enchantment: " + enchantString);
                }

                enchantments.put(enchantment, level);
            }
        }

        return new ItemBuilder(material).setAmount(amount).setName(name).setLore(lore).setEnchantments(enchantments).build();
    }

    public static List<ItemStack> parseItems(ConfigurationSection section) {
        List<ItemStack> items = new ArrayList<ItemStack>();
        for (String key : section.getKeys(false)) {
            items.add(parseItem(section.getConfigurationSection(key)));
        }
        return items;
    }
}
