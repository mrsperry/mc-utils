package io.github.mrsperry.mcutils.menu;

import io.github.mrsperry.mcutils.menu.items.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashSet;

public class Menu {
    private Inventory inventory;
    private HashSet<MenuItem> items;
    private ArrayList<Player> viewers;

    public Menu(String title, int slots, HashSet<MenuItem> items) {
        this.items = items;
        this.viewers = new ArrayList<>();

        if (slots < 9) {
            slots = 9;
        }

        int remainder = slots % 9;
        if (remainder != 0) {
            slots -= remainder;
        }

        this.inventory = Bukkit.createInventory(null, slots, title);
        for (MenuItem item : this.items) {
            item.setMenu(this);
            this.inventory.setItem(item.getSlot(), item.getItem());
        }
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
        this.viewers.add(player);
    }

    public void close(Player player) {
        player.closeInventory();
        this.viewers.remove(player);
    }

    public void updateSlot(MenuItem item) {
        this.inventory.setItem(item.getSlot(), item.getItem());
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public HashSet<MenuItem> getItems() {
        return this.items;
    }

    public ArrayList<Player> getViewers() {
        return this.viewers;
    }
}
