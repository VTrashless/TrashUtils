package tv.trashless.trashutils.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;
import tv.trashless.trashutils.utils.FoodLevelSettings;
import tv.trashless.trashutils.utils.HealthSettings;
import tv.trashless.trashutils.utils.Phrases;

import java.util.List;

public class Items {
    //GENERIC
    public static ItemStack GUI_BACKGROUND() {
        return ItemCreator.createBasicItem(" ", Material.BLACK_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_ENABLED() {
        return ItemCreator.createBasicItem("§aEnabled", Material.LIME_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_DISABLED() {
        return ItemCreator.createBasicItem("§cDisabled", Material.RED_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_GO_BACK() {
        return ItemCreator.createBasicItem("§eGo Back", Material.COMPASS);
    }

    public static ItemStack GUI_INCREASE() {
        return ItemCreator.createHeadItem("§aIncrease",
                "http://textures.minecraft.net/texture/6c48ddfdcd6d98a1b0aa3c71e8dad4edde732a68b2b0a5ab142600dca7587c32");
    }

    public static ItemStack GUI_DECREASE() {
        return ItemCreator.createHeadItem("§cDecrease",
                "http://textures.minecraft.net/texture/6f05afec2a6ec675cd5505a8f44bb6a4d556935689528321ead4edef685f2d10");
    }

    public static ItemStack GUI_PAGE_PREVIOUS() {
        return ItemCreator.createHeadItem("Previous page",
                "http://textures.minecraft.net/texture/e35e42fc7060c223acc965f7c5996f272644af40a4723a372f5903f8e9f188e7");
    }

    public static ItemStack GUI_PAGE_NEXT() {
        return ItemCreator.createHeadItem("Next page",
                "http://textures.minecraft.net/texture/aee0f82fb33f6cfa5169b9f5eafe4dc1c73618c9783b131adada411d8f605505");
    }

    public static ItemStack GUI_CONFIRM() {
        return ItemCreator.createBasicItem("§2Confirm", Material.GREEN_CONCRETE);
    }

    public static ItemStack GUI_CANCEL() {
        return ItemCreator.createBasicItem("§cCancel", Material.BARRIER);
    }

    //HEALTH SETTINGS
    public static ItemStack GUI_MAX_HEALTH(Player player) {
        return ItemCreator.addItemDescription(ItemCreator.createHeadItem("§fMax. Health: §b" + HealthSettings.getMaxHealth(player),
                "http://textures.minecraft.net/texture/eb76b4ee988572297cbd874683bee96ae3c55ce94c004e51adc82cee16cd0b0c"),
                List.of("§7" + Phrases.CLICK_TO_CHANGE));
    }

    public static ItemStack GUI_RESTORE_HEALTH() {
        return ItemCreator.addItemDescription(ItemCreator.createPotionItem("§fRestore health", PotionType.HEALING),
                List.of("§7Click to restore health!"));
    }

    public static ItemStack GUI_SHARED_HEALTH() {
        return ItemCreator.addItemDescription(ItemCreator.createBasicItem("§fShared health: §b" + HealthSettings.isShared(), Material.SCULK_SENSOR),
                List.of("§7" + Phrases.TOGGLE));
    }

    public static ItemStack GUI_NATURAL_REGEN() {
        return ItemCreator.addItemDescription(ItemCreator.createBasicItem("§fNatural Regeneration: §b" + HealthSettings.isNaturalRegen(), Material.CARROT),
                List.of("§7" + Phrases.TOGGLE));
    }

    public static ItemStack GUI_REGEN() {
        return ItemCreator.addItemDescription(ItemCreator.createBasicItem("§fRegeneration: §b" + HealthSettings.isRegen(), Material.GOLDEN_APPLE),
                List.of("§7" + Phrases.TOGGLE));
    }

    //FOOD LEVEL SETTINGS
    public static ItemStack GUI_RESTORE_FOOD_LEVEL() {
        return ItemCreator.addItemDescription(ItemCreator.createBasicItem("§fRestore food level", Material.GOLDEN_CARROT),
                List.of("§7Click to restore food level!"));
    }

    public static ItemStack GUI_SHARED_FOOD_LEVEL() {
        return ItemCreator.addItemDescription(ItemCreator.createBasicItem("§fShared food level: §b" + FoodLevelSettings.isShared(), Material.SCULK_SENSOR),
                List.of("§7" + Phrases.TOGGLE));
    }
}
