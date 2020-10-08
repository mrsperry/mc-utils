package io.github.mrsperry.mcutils.events.BlockDestroyEvent;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BlockDestroyEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public BlockDestroyEvent(Event parent, Block block, BlockBreakReason reason) {

    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
