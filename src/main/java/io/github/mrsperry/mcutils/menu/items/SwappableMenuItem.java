package io.github.mrsperry.mcutils.menu.items;

import com.google.common.collect.Lists;

import io.github.mrsperry.mcutils.menu.MenuEventContext;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class SwappableMenuItem extends MenuItem {
    private int index;
    private ArrayList<ItemStack> items;

    public SwappableMenuItem(int slot, ItemStack item, Consumer<MenuEventContext> callback, ItemStack... items) {
        super(slot, item, callback);

        this.index = 0;
        this.items = Lists.newArrayList(item);
        this.items.addAll(Arrays.asList(items));
    }

    @Override
    public void onClick(MenuEventContext context) {
        this.swap();
        super.onClick(context);
    }

    public void swap() {
        int index = this.index + 1;
        if (index >= this.items.size()) {
            index = 0;
        }

        super.setItem(this.items.get(index));
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
        super.setItem(this.items.get(index));
    }

    public int getIndex() {
        return this.index;
    }

    public ItemStack getCurrentItem() {
        return this.items.get(this.index);
    }
}
