package io.github.mrsperry.mcutils.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class Menu {
    UUID id;
    String title;
    int size;
    Map<Integer, MenuItem> items;
    Menu history;

    public Menu() {
        this("Menu", 9);
    }

    public Menu(String title) {
        this(title, 9);
    }

    public Menu(String title, int size) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    public Menu withTitle(String title) {
        this.title = title;
        return this;
    }

    public Menu withSize(int size) {

        if(size % 9 == 0 && size > 0) {
            this.size = size;
        } else {
            this.size = 9;
            Bukkit.getLogger().warning("MENU " + this.title  + ":" + this.id + " - Invalid menu size " + size + ", reverting to default.");
        }

        return this;
    }

    public Menu addMenuItem(MenuItem item, int slot) {
        this.items.put(slot, item);
        return this;
    }

    public void open(Player player) {

    }

    public void close(Player player) {

    }
}
