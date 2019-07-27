package io.github.mrsperry.mcutils.menu;

import io.github.mrsperry.mcutils.menu.items.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;

public class Menu {
    // The player that opened this menu
    private Player player;
    // The title of the menu
    private String title;
    // The number of slots in the menu (truncated to a factor of 9)
    private int slots;
    // The items to be added to the inventory
    private HashSet<MenuItem> items;
    // The actual inventory of the menu
    private Inventory inventory;

    Menu(Player player, String title, int slots, HashSet<MenuItem> items) {
        this.player = player;
        this.title = title;
        this.items = items;

        // Set the slots to the minimum of 9 if they are below it
        if (slots < 9) {
            slots = 9;
        }

        // Truncate the number of slots to a factor of 9
        int remainder = slots % 9;
        if (remainder != 0) {
            slots -= remainder;
        }
        this.slots = slots;

        // Create the inventory
        this.inventory = Bukkit.createInventory(null, this.slots, this.title);
        // Set all menu items to their positions in the inventory
        for (MenuItem item : this.items) {
            item.setMenu(this);
            this.inventory.setItem(item.getSlot(), item.getItem());
        }

        // Open the inventory
        player.openInventory(this.inventory);
    }

    /**
     * Closes the inventory for the player that opened it
     */
    public void close() {
        if (this.inventory.getViewers().contains(this.player)) {
            this.player.closeInventory();
        }
    }

    /**
     * Gets a menu item based on the slot
     * @param slot The slot to look at
     * @return The menu item at the slot or null if one wasn't found
     */
    public MenuItem getMenuItem(int slot) {
        for (MenuItem item : this.items) {
            if (item.getSlot() == slot) {
                return item;
            }
        }

        return null;
    }

    /**
     * Updates a slot with a new menu item
     * @param item The replacement menu item
     */
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
