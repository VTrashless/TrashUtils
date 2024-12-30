package tv.trashless.trashutils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.utils.HealthSettings;

import java.util.HashMap;

public class HealthSettingsGUI {
    private static final int ROW = 9;
    private static final int SIZE = 5*ROW;
    private static final Inventory INVENTORY = Bukkit.createInventory(null, SIZE, "§b§lHealth§3§lSettings");
    private static final HashMap<Integer, ItemStack> SLOT_ITEM = new HashMap<>();

    public static Inventory current() {
        return INVENTORY;
    }

    public static Inventory update(Player player) {
        for (int i = 0; i < SIZE; i++) {
            INVENTORY.setItem(i, Items.GUI_GENERIC_BACKGROUND());
        }

        SLOT_ITEM.put(ROW + 1, Items.GUI_SETTINGS_HEALTH_MAX_HEALTH(player));
        SLOT_ITEM.put(2*ROW + 1, Items.GUI_GENERIC_INCREASE());
        SLOT_ITEM.put(3*ROW + 1, Items.GUI_GENERIC_DECREASE());

        SLOT_ITEM.put(ROW + 3, Items.GUI_SETTINGS_HEALTH_SHARED_HEALTH());
        if (HealthSettings.isSharedHealth()) SLOT_ITEM.put(2*ROW + 3, Items.GUI_GENERIC_ENABLED());
        else SLOT_ITEM.put(2*ROW + 3, Items.GUI_GENERIC_DISABLED());

        SLOT_ITEM.put(ROW + 5, Items.GUI_SETTINGS_HEALTH_NATURAL_REGENERATION());
        if (HealthSettings.isNaturalRegeneration()) SLOT_ITEM.put(2*ROW + 5, Items.GUI_GENERIC_ENABLED());
        else SLOT_ITEM.put(2*ROW + 5, Items.GUI_GENERIC_DISABLED());

        SLOT_ITEM.put(ROW + 7, Items.GUI_SETTINGS_HEALTH_REGENERATION());
        if (HealthSettings.isRegeneration()) SLOT_ITEM.put(2*ROW + 7, Items.GUI_GENERIC_ENABLED());
        else SLOT_ITEM.put(2*ROW + 7, Items.GUI_GENERIC_DISABLED());

        SLOT_ITEM.put(4*ROW + 4, Items.GUI_GENERIC_GO_BACK());

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
