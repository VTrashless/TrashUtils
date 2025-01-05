package tv.trashless.trashutils.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.FoodLevelSettings;
import tv.trashless.trashutils.utils.HealthSettings;
import tv.trashless.trashutils.utils.inventory.InventoryBuilder;

public class Inventories {
    private static Inventory healthSettingsInventory;
    private static Inventory foodLevelSettingsInventory;

    public static Inventory currentHealthSettingsInventory() {
        return healthSettingsInventory;
    }

    public static Inventory currentFoodLevelSettingsInventory() {
        return foodLevelSettingsInventory;
    }

    public static Inventory updateHealthSettingsInventory(Player player) {
        healthSettingsInventory = new InventoryBuilder()
                .setHolder(null)
                .setSize(45)
                .setTitle("§3§lHealth§9§lSettings")
                .setBackgroundItem(Items.GUI_BACKGROUND())
                .setItem(10, Items.GUI_MAX_HEALTH(player))
                .setItem(19, Items.GUI_RESTORE_HEALTH())
                .setItem(12, Items.GUI_SHARED_HEALTH())
                .setConditionalItem(21, HealthSettings.isShared(), Items.GUI_ENABLED(), Items.GUI_DISABLED())
                .setItem(14, Items.GUI_NATURAL_REGEN())
                .setConditionalItem(23, HealthSettings.isNaturalRegen(), Items.GUI_ENABLED(), Items.GUI_DISABLED())
                .setItem(16, Items.GUI_REGEN())
                .setConditionalItem(25, HealthSettings.isRegen(), Items.GUI_ENABLED(), Items.GUI_DISABLED())
                .setItem(40, Items.GUI_GO_BACK())
                .build();

        return healthSettingsInventory;
    }

    public static Inventory updateFoodLevelSettingsInventory() {
        foodLevelSettingsInventory = new InventoryBuilder()
                .setHolder(null)
                .setSize(45)
                .setTitle("§3§lFoodLevel§9§lSettings")
                .setBackgroundItem(Items.GUI_BACKGROUND())
                .setItem(11, Items.GUI_RESTORE_FOOD_LEVEL())
                .setItem(15, Items.GUI_SHARED_FOOD_LEVEL())
                .setConditionalItem(24, FoodLevelSettings.isShared(), Items.GUI_ENABLED(), Items.GUI_DISABLED())
                .setItem(40, Items.GUI_GO_BACK())
                .build();

        return foodLevelSettingsInventory;
    }
}
