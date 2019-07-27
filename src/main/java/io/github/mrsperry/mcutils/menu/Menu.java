package io.github.mrsperry.mcutils.menu;

import io.github.mrsperry.mcutils.menu.items.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;

public class Menu {
    private Player player;
    private String title;
    private int slots;
    private HashSet<MenuItem> items;
    private Inventory inventory;

    Menu(Player player, String title, int slots, HashSet<MenuItem> items) {
        this.player = player;
        this.title = title;
        this.items = items;

        if (slots < 9) {
            slots = 9;
        }

        int remainder = slots % 9;
        if (remainder != 0) {
            slots -= remainder;
        }
        this.slots = slots;

        this.inventory = Bukkit.createInventory(null, this.slots, this.title);
        for (MenuItem item : this.items) {
            item.setMenu(this);
            this.inventory.setItem(item.getSlot(), item.getItem());
        }

        player.openInventory(this.inventory);
    }

    public void close() {
        if (this.inventory.getViewers().contains(this.player)) {
            this.player.closeInventory();
        }
    }

    public MenuItem getMenuItem(int slot) {
        for (MenuItem item : this.items) {
            if (item.getSlot() == slot) {
                return item;
            }
        }

        return null;
    }

    public void updateSlot(MenuItem item) {
        this.inventory.setItem(item.getSlot(), item.getItem());
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSlots() {
        return this.slots;
    }

    public HashSet<MenuItem> getItems() {
        return this.items;
    }
}
