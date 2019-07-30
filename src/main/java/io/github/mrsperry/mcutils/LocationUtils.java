package io.github.mrsperry.mcutils;

import org.bukkit.Location;

import java.util.Random;

public class LocationUtils {
    private static Random random = new Random();

    /**
     * Gives a new location within the offset amount
     * @param location The origin location
     * @param amount The amount of space the new location can move on the X, Y and Z axis
     * @return The new random location
     */
    public static Location randomize(Location location, double amount) {
        return LocationUtils.randomize(location, amount, amount, amount);
    }

    /**
     * Gives a new location within the offset amounts
     * @param location The origin location
     * @param xAmount The amount of space the new location can move of the X axis
     * @param yAmount The amount of space the new location can move of the Y axis
     * @param zAmount The amount of space the new location can move of the Z axis
     * @return The new random location
     */
    public static Location randomize(Location location, double xAmount, double yAmount, double zAmount) {
        Random random = LocationUtils.random;
        return new Location(location.getWorld(),
            location.getX() - xAmount + (random.nextDouble() * (xAmount * 2)),
            location.getY() - yAmount + (random.nextDouble() * (yAmount * 2)),
            location.getZ() - zAmount + (random.nextDouble() * (zAmount * 2)));
    }
}
