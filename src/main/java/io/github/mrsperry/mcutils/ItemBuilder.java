package io.github.mrsperry.mcutils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder() {
        this(Material.STONE);
    }

    public ItemBuilder(Material material) {
        if (material == Material.AIR) {
            Bukkit.getLogger().warning("Tried to build an item stack with air!");
            return;
        }
        this.item = new ItemStack(material, 1);
        this.meta = this.item.getItemMeta();
        this.meta.setLore(new ArrayList<String>());
    }

    public ItemBuilder setMaterial(Material material) {
        this.item.setType(material);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(short data) {
        this.item.setDurability(data);
        return this;
    }

    public ItemBuilder setName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }

    public ItemBuilder addLore(String loreLine) {
        List<String> temp = this.meta.getLore();
        temp.add(loreLine);
        return this.setLore(temp);
    }

    public ItemBuilder setEnchantments(HashMap<Enchantment, Integer> enchantments) {
        for (Enchantment enchant : enchantments.keySet()) {
            this.meta.addEnchant(enchant, enchantments.get(enchant), true);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
