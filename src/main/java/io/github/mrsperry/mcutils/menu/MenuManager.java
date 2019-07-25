package io.github.mrsperry.mcutils.menu;

import io.github.mrsperry.mcutils.menu.items.MenuItem;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MenuManager implements Listener {
    private JavaPlugin plugin;
    private HashMap<String, Menu> menus;

    public MenuManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.menus = new HashMap<>();

        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public Menu createNewMenu(String id, String title, int slots, HashSet<MenuItem> items) {
        Menu menu = new Menu(title, slots, items);
        this.menus.put(id, menu);

        return menu;
    }

    public void switchMenu(Player player, String id) {
        Menu menu = this.menus.getOrDefault(id, null);

        if (menu != null) {
            menu.open(player);
        } else {
            this.plugin.getLogger().warning("Could not switch to menu: " + id);
        }
    }

    public Menu getViewingMenu(Player player) {
        for (Menu menu : this.menus.values()) {
            if (menu.getViewers().contains(player)) {
                return menu;
            }
        }

        return null;
    }

    public Menu getMenu(String id) {
        return this.menus.getOrDefault(id, null);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        for (Menu menu : this.menus.values()) {
            if (menu.getInventory() == event.getInventory()) {
                event.setCancelled(true);
                event.setResult(Event.Result.DENY);

                for (MenuItem item : menu.getItems()) {
                    if (item.getSlot() == event.getSlot()) {
                        // Create a runnable to handle the click event 1 tick later as there are issues using inventory methods with this event
                        new BukkitRunnable() {
                            public void run() {
                                item.onClick(new MenuEventContext((Player) event.getWhoClicked(), menu, item));
                            }
                        }.runTaskLater(this.plugin, 1);
                        return;
                    }
                }
            }
        }
    }
}
