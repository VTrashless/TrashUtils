package tv.trashless.trashutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Collection;

public class HealthSettings {
    private static boolean shared;
    private static boolean naturalRegen;
    private static boolean regen;

    /**
     * TODO:    - Load settings from config file
     */

    public static void load() {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> setMaxHealth(onlinePlayer, 20));
        shared = false;
        naturalRegen = true;
        regen = true;
    }

    public static double getHealth(Player player) {
        return player.getHealth();
    }

    public static void setHealth(Player player, double newHealth) {
        player.setHealth(Math.min(newHealth, HealthSettings.getHealth(player)));
    }

    public static void setHealth(Collection<? extends Player> players, double newHealth) {
        for (Player player : players) player.setHealth(Math.min(newHealth, HealthSettings.getHealth(player)));
    }

    public static double getMaxHealth(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    public static void setMaxHealth(Player player, double newMaxHealth) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
        player.sendHealthUpdate();
    }

    public static void setMaxHealth(Collection<? extends Player> players, double newMaxHealth) {
        for (Player player : players) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
            player.sendHealthUpdate();
        }
    }

    public static void increaseMaxHealth(Player player, int increment) {
        HealthSettings.setMaxHealth(player, HealthSettings.getMaxHealth(player) + 1);
    }

    public static void increaseMaxHealth(Collection<? extends Player> players, int increment) {
        for (Player player : players) HealthSettings.setMaxHealth(player, HealthSettings.getMaxHealth(player) + increment);
    }

    public static void decreaseMaxHealth(Player player, int decrement) {
        HealthSettings.setMaxHealth(player, HealthSettings.getMaxHealth(player) - decrement);
    }

    public static void decreaseMaxHealth(Collection<? extends Player> players, int decrement) {
        for (Player player : players) HealthSettings.setMaxHealth(player, HealthSettings.getMaxHealth(player) - decrement);
    }

    public static boolean isShared() {
        return shared;
    }

    public static void setShared(boolean shared) {
        HealthSettings.shared = shared;
    }

    public static boolean isNaturalRegen() {
        return naturalRegen;
    }

    public static void setNaturalRegen(boolean naturalRegen) {
        HealthSettings.naturalRegen = naturalRegen;
    }

    public static boolean isRegen() {
        return regen;
    }

    public static void setRegen(boolean regen) {
        HealthSettings.regen = regen;
    }
}
