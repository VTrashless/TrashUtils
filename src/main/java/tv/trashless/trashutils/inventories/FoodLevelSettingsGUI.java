package tv.trashless.trashutils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.FoodLevelSettings;

import java.util.HashMap;

public class FoodLevelSettingsGUI {
    private static final int ROW = 9;
    private static final int SIZE = 5*ROW;
    private static final Inventory INVENTORY = Bukkit.createInventory(null, SIZE, "§3§lFoodLevel§9§lSettings");
    private static final HashMap<Integer, ItemStack> ITEM_SLOTS = new HashMap<>();

    public static Inventory current() {
        return INVENTORY;
    }

    public static Inventory update(Player player) {
        for (int i = 0; i < SIZE; i++) {
            INVENTORY.setItem(i, Items.GUI_BACKGROUND());
        }

        ITEM_SLOTS.put(ROW + 2, Items.GUI_RESTORE_FOOD_LEVEL());

        ITEM_SLOTS.put(ROW + 6, Items.GUI_SHARED_FOOD_LEVEL());
        if (FoodLevelSettings.isShared()) ITEM_SLOTS.put(2*ROW + 6, Items.GUI_ENABLED());
        else ITEM_SLOTS.put(2*ROW + 6, Items.GUI_DISABLED());

        ITEM_SLOTS.put(4*ROW + 4, Items.GUI_GO_BACK());

        ITEM_SLOTS.keySet().forEach(integer -> INVENTORY.setItem(integer, getItemInSlot(integer)));
        return INVENTORY;
    }

    public static ItemStack getItemInSlot(int slot) {
        return ITEM_SLOTS.get(slot);
    }
}
