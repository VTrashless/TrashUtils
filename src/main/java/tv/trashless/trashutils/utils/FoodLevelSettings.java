package tv.trashless.trashutils.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelSettings implements Listener {
    private static boolean shared;

    /**
     * TODO:    - Load settings from config file
     */

    public static void load() {
        shared = false;
    }

    public static boolean isShared() {
        return shared;
    }

    public static void setShared(boolean shared) {
        FoodLevelSettings.shared = shared;
    }

    @EventHandler
    public void onChange(FoodLevelChangeEvent event) {
        if (FoodLevelSettings.isShared()) {
            Bukkit.getOnlinePlayers().forEach(player -> player.setFoodLevel(event.getFoodLevel()));
            return;
        }
        event.setFoodLevel(event.getFoodLevel());
    }
}
