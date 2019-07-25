package io.github.mrsperry.mcutils.menu.items;

import io.github.mrsperry.mcutils.menu.MenuEventContext;

import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;


public class StaticMenuItem extends MenuItem {
    public StaticMenuItem(int slot, ItemStack item, Consumer<MenuEventContext> callback) {
        super(slot, item, callback);
    }
}
