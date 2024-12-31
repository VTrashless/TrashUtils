package tv.trashless.trashutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class FoodLevel {
    private final static HashMap<Player, Integer> MAX_VALUES = new HashMap<>();
    private final static HashMap<Player, Integer> VALUES = new HashMap<>();
    private static boolean shared;
    private static boolean decay;

    /**
     * TODO:    - Load settings from config file
     */
    public FoodLevel() {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> setMax(onlinePlayer, 20));
        VALUES.putAll(MAX_VALUES);
        shared = false;
        decay = true;
    }

    /*
    public static int getFoodLevel(Player player) {

    }
     */

    public static int getMax(Player player) {
        return MAX_VALUES.get(player);
    }

    public static void setMax(Player player, int newMaxFoodLevel) {
        if (player == null) Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
            MAX_VALUES.put(onlinePlayer, newMaxFoodLevel);
        });
        else {
            MAX_VALUES.put(player, newMaxFoodLevel);
        }
    }

    public static void increaseMax(Player player, int increment) {
        if (player == null) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                setMax(onlinePlayer, getMax(onlinePlayer) + increment);
            });
        } else {
            setMax(player, getMax(player) + increment);
        }
    }

    public static void decreaseMax(Player player, int decrement) {
        if (player == null) {
            Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                setMax(onlinePlayer, getMax(onlinePlayer) - decrement);
            });
        } else {
            setMax(player, getMax(player) - decrement);
        }
    }

    public static boolean isShared() {
        return shared;
    }

    public static void setShared(boolean shared) {
        FoodLevel.shared = shared;
    }

    public static boolean isDecay() {
        return decay;
    }

    public static void setDecay(boolean decay) {
        FoodLevel.decay = decay;
    }
}
