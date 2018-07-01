package io.github.mrsperry.mcutils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.HashSet;
import java.util.Random;

public class WorldGenerator {
    private WorldEditPlugin plugin;

    public WorldGenerator() {
        try {
            this.plugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not find world edit!");
            Bukkit.getLogger().warning("Plugins that generate void worlds with schematics will throw errors!");
        }
    }

    public World createWorld(String name, Location spawn) {
        return generate(name, spawn, new HashSet<File>());
    }

    public World createWorld(String name, Location spawn, HashSet<File> schematics) {
        return generate(name, spawn, schematics);
    }

    private World generate(String name, Location spawn, HashSet<File> schematics) {
        if (Bukkit.getWorld(name) != null) {
            WorldCreator creator = new WorldCreator(name);
            creator.generator(new ChunkGenerator() {
                @Override
                public byte[] generate(World world, Random random, int x, int z) {
                    return new byte[65536];
                }
            });

            World world = creator.createWorld();
            world.setSpawnLocation(spawn);

            for (File schematic : schematics) {
                Bukkit.getLogger().info("Loading schematic \"" + schematic.getName() + "\" in world \"" + world.getName() + "\"");

                EditSession session = this.plugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(world), 100000);
                try {
                    MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session, Vector.ZERO, true);
                } catch (MaxChangedBlocksException ex) {
                    Bukkit.getLogger().warning("Schematic exceeded maximum number of blocks: " + schematic.getName());
                } catch (Exception ex) {
                    Bukkit.getLogger().severe("An error occurred while loading schematic!");
                    Bukkit.getLogger().severe("Some or all schematics may not be loaded for world: " + world.getName());
                }
            }

            return world;
        }

        return null;
    }
}
