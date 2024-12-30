package tv.trashless.trashutils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class HealthSettingsGUI {
    private static final int SIZE = 5 * 9;
    private static final Inventory INVENTORY = Bukkit.createInventory(null, SIZE, "§b§lHealth§3§lSettings");
    private static final HashMap<Integer, ItemStack> SLOT_ITEM = new HashMap<>();

    public static Inventory current() {
        return INVENTORY;
    }

    public static Inventory update(Player player) {
        for (int i = 0; i < SIZE; i++) {
            INVENTORY.setItem(i, Items.GUI_GENERIC_BACKGROUND());
        }

        SLOT_ITEM.put(19, Items.GUI_SETTINGS_HEALTH_MAX_HEALTH(player));
        SLOT_ITEM.put(21, Items.GUI_SETTINGS_HEALTH_SHARED_HEALTH());
        SLOT_ITEM.put(23, Items.GUI_SETTINGS_HEALTH_NATURAL_REGENERATION());
        SLOT_ITEM.put(25, Items.GUI_SETTINGS_HEALTH_REGENERATION());
        SLOT_ITEM.put(10, Items.GUI_GENERIC_INCREASE());
        SLOT_ITEM.put(28, Items.GUI_GENERIC_DECREASE());
        SLOT_ITEM.put(40, Items.GUI_GENERIC_GO_BACK());

        SLOT_ITEM.keySet().forEach(integer -> INVENTORY.setItem(integer, getItemForSlot(integer)));
        return INVENTORY;
    }

    public static int getSize() {
        return SIZE;
    }

    public static ItemStack getItemForSlot(int slot) {
        return SLOT_ITEM.get(slot);
    }
}
