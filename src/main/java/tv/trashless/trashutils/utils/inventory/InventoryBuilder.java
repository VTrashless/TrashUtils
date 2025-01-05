package tv.trashless.trashutils.utils.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryBuilder {
    protected InventoryHolder holder;
    protected int size;
    protected String title;
    protected final List<ItemStack> items = new ArrayList<>();

    public InventoryBuilder setHolder(InventoryHolder holder) {
        this.holder = holder;
        return this;
    }

    public InventoryBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public InventoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public InventoryBuilder setBackgroundItem(ItemStack item) {
        for (int i = 0; i < size; i++) {
            items.add(item);
        }
        return this;
    }

    public InventoryBuilder setItem(int slot, ItemStack item) {
        items.set(slot, item);
        return this;
    }

    public InventoryBuilder setConditionalItem(int slot, boolean condition, ItemStack conditionMet, ItemStack conditionNotMet) {
        if (condition) items.set(slot, conditionMet);
        else items.set(slot, conditionNotMet);
        return this;
    }

    public Inventory build() {
        Inventory inventory = Bukkit.createInventory(holder, size, title);
        inventory.setStorageContents(items.toArray(new ItemStack[0]));
        return inventory;
    }
}
