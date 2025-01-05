package tv.trashless.trashutils.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;
import tv.trashless.trashutils.utils.FoodLevelSettings;
import tv.trashless.trashutils.utils.HealthSettings;
import tv.trashless.trashutils.utils.Phrases;
import tv.trashless.trashutils.utils.item.HeadItemBuilder;
import tv.trashless.trashutils.utils.item.ItemBuilder;
import tv.trashless.trashutils.utils.item.PotionItemBuilder;

import java.util.List;

public class Items {
    //GENERIC
    public static ItemStack GUI_BACKGROUND() {
        return new ItemBuilder()
                .setType(Material.BLACK_STAINED_GLASS_PANE)
                .setDisplayName(" ")
                .build();
    }

    public static ItemStack GUI_ENABLED() {
        return new ItemBuilder()
                .setType(Material.LIME_STAINED_GLASS_PANE)
                .setDisplayName("§aEnabled")
                .build();
    }

    public static ItemStack GUI_DISABLED() {
        return new ItemBuilder()
                .setType(Material.RED_STAINED_GLASS_PANE)
                .setDisplayName("§cDisabled")
                .build();
    }

    public static ItemStack GUI_GO_BACK() {
        return new ItemBuilder()
                .setType(Material.COMPASS)
                .setDisplayName("§eGo Back")
                .build();
    }

    public static ItemStack GUI_INCREASE() {
        return new HeadItemBuilder()
                .setTextureUrl("http://textures.minecraft.net/texture/6c48ddfdcd6d98a1b0aa3c71e8dad4edde732a68b2b0a5ab142600dca7587c32")
                .setDisplayName("§aIncrease")
                .build();
    }

    public static ItemStack GUI_DECREASE() {
        return new HeadItemBuilder()
                .setTextureUrl("http://textures.minecraft.net/texture/6f05afec2a6ec675cd5505a8f44bb6a4d556935689528321ead4edef685f2d10")
                .setDisplayName("§cDecrease")
                .build();
    }

    public static ItemStack GUI_PAGE_PREVIOUS() {
        return new HeadItemBuilder()
                .setTextureUrl("http://textures.minecraft.net/texture/e35e42fc7060c223acc965f7c5996f272644af40a4723a372f5903f8e9f188e7")
                .setDisplayName("Previous page")
                .build();
    }

    public static ItemStack GUI_PAGE_NEXT() {
        return new HeadItemBuilder()
                .setTextureUrl("http://textures.minecraft.net/texture/aee0f82fb33f6cfa5169b9f5eafe4dc1c73618c9783b131adada411d8f605505")
                .setDisplayName("Next page")
                .build();
    }

    public static ItemStack GUI_CONFIRM() {
        return new ItemBuilder()
                .setType(Material.GREEN_CONCRETE)
                .setDisplayName("§2Confirm")
                .build();
    }

    public static ItemStack GUI_CANCEL() {
        return new ItemBuilder()
                .setType(Material.BARRIER)
                .setDisplayName("§cCancel")
                .build();
    }

    //HEALTH SETTINGS
    public static ItemStack GUI_MAX_HEALTH(Player player) {
        return new HeadItemBuilder()
                .setTextureUrl("http://textures.minecraft.net/texture/eb76b4ee988572297cbd874683bee96ae3c55ce94c004e51adc82cee16cd0b0c")
                .setDisplayName("§fMax. Health: §b" + HealthSettings.getMaxHealth(player))
                .setLore(List.of("§7" + Phrases.CLICK_TO_CHANGE))
                .build();
    }

    public static ItemStack GUI_RESTORE_HEALTH() {
        return new PotionItemBuilder()
                .potion()
                .setBasePotionType(PotionType.HEALING)
                .showEffects(false)
                .setDisplayName("§fRestore health")
                .setLore(List.of("§7Click to restore health!"))
                .build();
    }

    public static ItemStack GUI_SHARED_HEALTH() {
        return new ItemBuilder()
                .setType(Material.SCULK_SENSOR)
                .setDisplayName("§fShared health: §b" + HealthSettings.isShared())
                .setLore(List.of("§7" + Phrases.TOGGLE))
                .build();
    }

    public static ItemStack GUI_NATURAL_REGEN() {
        return new ItemBuilder()
                .setType(Material.CARROT)
                .setDisplayName("§fNatural Regeneration: §b" + HealthSettings.isNaturalRegen())
                .setLore(List.of("§7" + Phrases.TOGGLE))
                .build();
    }

    public static ItemStack GUI_REGEN() {
        return new ItemBuilder()
                .setType(Material.GOLDEN_APPLE)
                .setDisplayName("§fRegeneration: §b" + HealthSettings.isRegen())
                .setLore(List.of("§7" + Phrases.TOGGLE))
                .build();
    }

    // FOOD LEVEL SETTINGS
    public static ItemStack GUI_RESTORE_FOOD_LEVEL() {
        return new ItemBuilder()
                .setType(Material.GOLDEN_CARROT)
                .setDisplayName("§fRestore food level")
                .setLore(List.of("§7Click to restore food level!"))
                .build();
    }

    public static ItemStack GUI_SHARED_FOOD_LEVEL() {
        return new ItemBuilder()
                .setType(Material.SCULK_SENSOR)
                .setDisplayName("§fShared food level: §b" + FoodLevelSettings.isShared())
                .setLore(List.of("§7" + Phrases.TOGGLE))
                .build();
    }
}
