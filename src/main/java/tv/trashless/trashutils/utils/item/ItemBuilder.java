package tv.trashless.trashutils.utils.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {
    protected Material type;
    protected int stackSize = 1;
    protected String itemName;
    protected String displayName;
    protected List<String> lore;

    public ItemBuilder() {}

    public ItemBuilder(ItemStack stack) {
        this.type = stack.getType();
        this.stackSize = stack.getAmount();
        ItemMeta meta = stack.getItemMeta();
        itemName = meta.getItemName();
        displayName = meta.getDisplayName();
        lore = meta.getLore();
    }

    public ItemBuilder setType(Material type) {
        this.type = type;
        return this;
    }

    public ItemBuilder setStackSize(int stackSize) {
        this.stackSize = stackSize;
        return this;
    }

    public ItemBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStack build() {
        ItemStack stack = new ItemStack(type, stackSize);
        ItemMeta meta = stack.getItemMeta();
        meta.setItemName(itemName);
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
}
