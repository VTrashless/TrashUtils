package tv.trashless.trashutils.utils.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class PotionItemBuilder extends ItemBuilder {
    private PotionType basePotionType;
    private final List<PotionEffect> effects = new ArrayList<>();
    private boolean showEffects;

    // setSplash, setConsumable, setLingering

    public PotionItemBuilder potion() {
        type = Material.POTION;
        return this;
    }

    public PotionItemBuilder splashPotion() {
        type = Material.SPLASH_POTION;
        return this;
    }

    public PotionItemBuilder lingeringPotion() {
        type = Material.LINGERING_POTION;
        return this;
    }

    public PotionItemBuilder setBasePotionType(PotionType basePotionType) {
        this.basePotionType = basePotionType;
        return this;
    }

    public PotionItemBuilder addEffect(PotionEffect effect) {
        effects.add(effect);
        return this;
    }

    public PotionItemBuilder showEffects(boolean showEffects) {
        this.showEffects = showEffects;
        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack stack = new ItemBuilder()
                .setType(type)
                .setStackSize(stackSize)
                .setItemName(itemName)
                .setDisplayName(displayName)
                .setLore(lore)
                .build();

        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setBasePotionType(basePotionType);
        effects.forEach(effect -> meta.addCustomEffect(effect, true));
        if (!showEffects) meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        stack.setItemMeta(meta);
        return stack;
    }
}
