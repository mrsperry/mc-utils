package io.github.mrsperry.mcutils.worlds;

import org.bukkit.World;

public class WorldCreator {
    public static World createWorld(String name) {
        World world = WorldCreator.createWorld(name);
        org.bukkit.bWorldCreator creator = new WorldCreator(name);

    }
}
