package io.github.mrsperry.mcutils;

import com.google.common.collect.Lists;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class EntityTypes {
    private static ArrayList<EntityType> hostile = Lists.newArrayList(
            EntityType.BLAZE,          EntityType.CAVE_SPIDER,     EntityType.CREEPER,
            EntityType.ELDER_GUARDIAN, EntityType.ENDER_DRAGON,    EntityType.ENDERMAN,
            EntityType.EVOKER,         EntityType.GHAST,           EntityType.GIANT,
            EntityType.GUARDIAN,       EntityType.HUSK,            EntityType.ILLUSIONER,
            EntityType.MAGMA_CUBE,     EntityType.PIG_ZOMBIE,      EntityType.SHULKER,
            EntityType.SILVERFISH,     EntityType.SILVERFISH,      EntityType.SKELETON,
            EntityType.SLIME,          EntityType.SPIDER,          EntityType.STRAY,
            EntityType.VEX,            EntityType.VINDICATOR,      EntityType.WITCH,
            EntityType.WITHER,         EntityType.WITHER_SKELETON, EntityType.ZOMBIE,
            EntityType.ZOMBIE_VILLAGER
    );

    private static ArrayList<EntityType> neutral = Lists.newArrayList(
            EntityType.BAT,            EntityType.COW,             EntityType.CHICKEN,
            EntityType.DONKEY,         EntityType.HORSE,           EntityType.IRON_GOLEM,
            EntityType.LLAMA,          EntityType.MULE,            EntityType.MUSHROOM_COW,
            EntityType.OCELOT,         EntityType.PARROT,          EntityType.PIG,
            EntityType.POLAR_BEAR,     EntityType.RABBIT,          EntityType.SHEEP,
            EntityType.SNOWMAN,        EntityType.SQUID
    );

    public static ArrayList<EntityType> getAllTypes() {
        ArrayList<EntityType> types = hostile;
        types.addAll(neutral);
        return types;
    }

    public static ArrayList<EntityType> getHostileTypes() {
        return hostile;
    }

    public static ArrayList<EntityType> getNeutralTypes() {
        return neutral;
    }
}
