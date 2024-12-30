package tv.trashless.trashutils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import tv.trashless.trashutils.utils.HealthSettings;
import tv.trashless.trashutils.utils.Phrases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class Items {
    //GENERIC
    public static ItemStack GUI_GENERIC_BACKGROUND() {
        return createBasicItem(" ", Material.BLACK_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_GENERIC_ENABLED() {
        return createBasicItem("§aEnabled", Material.LIME_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_GENERIC_DISABLED() {
        return createBasicItem("§cDisabled", Material.RED_STAINED_GLASS_PANE);
    }

    public static ItemStack GUI_GENERIC_GO_BACK() {
        return createBasicItem("§eGo Back", Material.COMPASS);
    }

    public static ItemStack GUI_GENERIC_INCREASE() {
        return createHeadItem("§aIncrease",
                "http://textures.minecraft.net/texture/6c48ddfdcd6d98a1b0aa3c71e8dad4edde732a68b2b0a5ab142600dca7587c32");
    }

    public static ItemStack GUI_GENERIC_DECREASE() {
        return createHeadItem("§cDecrease",
                "http://textures.minecraft.net/texture/6f05afec2a6ec675cd5505a8f44bb6a4d556935689528321ead4edef685f2d10");
    }

    public static ItemStack GUI_GENERIC_PAGE_PREVIOUS() {
        return createHeadItem("Previous page",
                "http://textures.minecraft.net/texture/e35e42fc7060c223acc965f7c5996f272644af40a4723a372f5903f8e9f188e7");
    }

    public static ItemStack GUI_GENERIC_PAGE_NEXT() {
        return createHeadItem("Next page",
                "http://textures.minecraft.net/texture/aee0f82fb33f6cfa5169b9f5eafe4dc1c73618c9783b131adada411d8f605505");
    }

    public static ItemStack GUI_GENERIC_CONFIRM() {
        return createBasicItem("§2Confirm", Material.GREEN_CONCRETE);
    }

    public static ItemStack GUI_GENERIC_CANCEL() {
        return createBasicItem("§cCancel", Material.BARRIER);
    }

    //HEALTH SETTINGS
    public static ItemStack GUI_SETTINGS_HEALTH_MAX_HEALTH(Player player) {
        return createPotionItem("§fMax. Health: §b" + HealthSettings.getMaxHealthForPlayer(player), PotionType.HEALING);
    }

    public static ItemStack GUI_SETTINGS_HEALTH_SHARED_HEALTH() {
        return addItemDescription(createBasicItem("§fShared health: §b" + HealthSettings.isSharedHealth(), Material.SCULK_SENSOR),
                List.of("§7" + Phrases.TOGGLE_SETTING));
    }

    public static ItemStack GUI_SETTINGS_HEALTH_NATURAL_REGENERATION() {
        return addItemDescription(createBasicItem("§fNatural Regeneration: §b" + HealthSettings.isNaturalRegeneration(), Material.CARROT),
                List.of("§7" + Phrases.TOGGLE_SETTING));
    }

    public static ItemStack GUI_SETTINGS_HEALTH_REGENERATION() {
        return addItemDescription(createBasicItem("§fRegeneration: §b" + HealthSettings.isRegeneration(), Material.GOLDEN_APPLE),
                List.of("§7" + Phrases.TOGGLE_SETTING));
    }

    public static ItemStack renameItem(ItemStack stack, String newName) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(newName);
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack addItemDescription(ItemStack stack, List<String> description) {
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(description);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack createBasicItem(String name, Material material) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setItemName(" ");
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack createPotionItem(String name, PotionType type) {
        ItemStack stack = createBasicItem(name, Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setBasePotionType(type);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack createHeadItem(String name, String textureUrl) {
        ItemStack stack = createBasicItem(name, Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.fromString("92864445-51c5-4c3b-9039-517c9927d1b4")); // Get a new player profile
        PlayerTextures textures = profile.getTextures();

        URL urlObject;
        try {
            urlObject = new URL(textureUrl); // The URL to the skin
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Invalid URL", exception);
        }

        textures.setSkin(urlObject);
        profile.setTextures(textures); // Set the textures back to the profile
        meta.setOwnerProfile(profile); // Set the owning player of the head to the player profile
        stack.setItemMeta(meta);
        return stack;
    }
}
