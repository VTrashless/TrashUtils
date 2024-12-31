package tv.trashless.trashutils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.Health;

import java.util.HashMap;

public class HealthSettingsGUI {
    private static final int ROW = 9;
    private static final int SIZE = 5*ROW;
    private static final Inventory INVENTORY = Bukkit.createInventory(null, SIZE, "§3§lHealth§9§lSettings");
    private static final HashMap<Integer, ItemStack> ITEM_SLOTS = new HashMap<>();

    public static Inventory current() {
        return INVENTORY;
    }

    public static Inventory update(Player player) {
        for (int i = 0; i < SIZE; i++) {
            INVENTORY.setItem(i, Items.GUI_BACKGROUND());
        }

        ITEM_SLOTS.put(ROW + 1, Items.GUI_MAX_HEALTH(player));
        ITEM_SLOTS.put(2*ROW + 1, Items.GUI_RESTORE_HEALTH());

        ITEM_SLOTS.put(ROW + 3, Items.GUI_SHARED_HEALTH());
        if (Health.isShared()) ITEM_SLOTS.put(2*ROW + 3, Items.GUI_ENABLED());
        else ITEM_SLOTS.put(2*ROW + 3, Items.GUI_DISABLED());

        ITEM_SLOTS.put(ROW + 5, Items.GUI_NATURAL_REGEN());
        if (Health.isNaturalRegen()) ITEM_SLOTS.put(2*ROW + 5, Items.GUI_ENABLED());
        else ITEM_SLOTS.put(2*ROW + 5, Items.GUI_DISABLED());

        ITEM_SLOTS.put(ROW + 7, Items.GUI_REGEN());
        if (Health.isRegen()) ITEM_SLOTS.put(2*ROW + 7, Items.GUI_ENABLED());
        else ITEM_SLOTS.put(2*ROW + 7, Items.GUI_DISABLED());

        ITEM_SLOTS.put(4*ROW + 4, Items.GUI_GO_BACK());

        ITEM_SLOTS.keySet().forEach(integer -> INVENTORY.setItem(integer, getItemForSlot(integer)));
        return INVENTORY;
    }

    public static ItemStack getItemForSlot(int slot) {
        return ITEM_SLOTS.get(slot);
    }
}
