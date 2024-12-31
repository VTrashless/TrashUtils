package tv.trashless.trashutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Health {
    private static boolean shared;
    private static boolean naturalRegen;
    private static boolean regen;

    /**
     * TODO:    - Load settings from config file
     */
    public Health() {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> setMax(onlinePlayer, 20));
        shared = false;
        naturalRegen = true;
        regen = true;
    }

    public static double get(Player player) {
        return player.getHealth();
    }

    public static void set(Player player, double newHealth) {
        player.setHealth(Math.min(newHealth, Health.get(player)));
    }

    public static double getMax(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    public static void setMax(Player player, double newMaxHealth) {
        if (player == null) Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            onlinePlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
            onlinePlayer.sendHealthUpdate();
        });
        else {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(newMaxHealth);
            player.sendHealthUpdate();
        }
    }

    public static void increaseMax(Player player, int increment) {
        if (player == null) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                Health.setMax(onlinePlayer, Health.getMax(onlinePlayer) + increment);
            });
        } else {
            Health.setMax(player, Health.getMax(player) + 1);
        }
    }

    public static void decreaseMax(Player player, int decrement) {
        if (player == null) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                Health.setMax(onlinePlayer, Health.getMax(onlinePlayer) - decrement);
            });
        } else {
            Health.setMax(player, Health.getMax(player) - 1);
        }
    }

    public static boolean isShared() {
        return shared;
    }

    public static void setShared(boolean shared) {
        Health.shared = shared;
    }

    public static boolean isNaturalRegen() {
        return naturalRegen;
    }

    public static void setNaturalRegen(boolean naturalRegen) {
        Health.naturalRegen = naturalRegen;
    }

    public static boolean isRegen() {
        return regen;
    }

    public static void setRegen(boolean regen) {
        Health.regen = regen;
    }
}
