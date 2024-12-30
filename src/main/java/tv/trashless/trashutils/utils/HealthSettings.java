package tv.trashless.trashutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HealthSettings {
    private static final HashMap<Player, Double> MAX_HEALTH_FOR_PLAYER = new HashMap<>();
    private static boolean sharedHealth;
    private static boolean naturalRegeneration;
    private static boolean regeneration;

    /**
     * TODO:    - Load settings from config file
     */
    public HealthSettings() {
        Bukkit.getOnlinePlayers().forEach(HealthSettings::getMaxHealthForPlayer);
        sharedHealth = false;
        naturalRegeneration = true;
        regeneration = true;
    }

    public static double getMaxHealthForPlayer(Player player) {
        return MAX_HEALTH_FOR_PLAYER.getOrDefault(player, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    public static void setMaxHealthForPlayer(Player player, double newMaxHealth) {
        HealthSettings.MAX_HEALTH_FOR_PLAYER.put(player, newMaxHealth);
    }

    public static void setMaxHealthForAll(double newMaxHealth) {
        Bukkit.getOnlinePlayers().forEach(player -> HealthSettings.MAX_HEALTH_FOR_PLAYER.put(player, newMaxHealth));
    }

    public static void increaseMaxHealthForPlayer(Player player) {
        double newMaxHealth = HealthSettings.getMaxHealthForPlayer(player) + 1;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
        HealthSettings.setMaxHealthForPlayer(player, newMaxHealth);
    }

    public static void increaseMaxHealthForAll() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            double newMaxHealth = HealthSettings.getMaxHealthForPlayer(player) + 1;
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
            HealthSettings.setMaxHealthForPlayer(player, newMaxHealth);
        });
    }

    public static void decreaseMaxHealthForPlayer(Player player) {
        double newMaxHealth = HealthSettings.getMaxHealthForPlayer(player) - 1;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
        HealthSettings.setMaxHealthForPlayer(player, newMaxHealth);
    }

    public static void decreaseMaxHealthForAll() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            double newMaxHealth = HealthSettings.getMaxHealthForPlayer(player) - 1;
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
            HealthSettings.setMaxHealthForPlayer(player, newMaxHealth);
        });
    }

    public static boolean isSharedHealth() {
        return sharedHealth;
    }

    public static void setSharedHealth(boolean sharedHealth) {
        HealthSettings.sharedHealth = sharedHealth;
    }

    public static boolean isNaturalRegeneration() {
        return naturalRegeneration;
    }

    public static void setNaturalRegeneration(boolean naturalRegeneration) {
        HealthSettings.naturalRegeneration = naturalRegeneration;
    }

    public static boolean isRegeneration() {
        return regeneration;
    }

    public static void setRegeneration(boolean regeneration) {
        HealthSettings.regeneration = regeneration;
    }
}
