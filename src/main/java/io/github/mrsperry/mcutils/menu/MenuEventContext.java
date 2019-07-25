package io.github.mrsperry.mcutils.menu;

import io.github.mrsperry.mcutils.menu.items.MenuItem;

import org.bukkit.entity.Player;

public class MenuEventContext {
    private Player player;
    private Menu menu;
    private MenuItem item;

    public MenuEventContext(Player player, Menu menu, MenuItem item) {
        this.player = player;
        this.menu = menu;
        this.item = item;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public MenuItem getItem() {
        return this.item;
    }
}
