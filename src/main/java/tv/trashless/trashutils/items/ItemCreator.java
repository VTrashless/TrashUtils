package tv.trashless.trashutils.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class ItemCreator {

    //HELPER METHODS
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

    static ItemStack createBasicItem(String name, Material material) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setItemName(" ");
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return stack;
    }

    static ItemStack createPotionItem(String name, PotionType type) {
        ItemStack stack = createBasicItem(name, Material.POTION);
        PotionMeta meta = (PotionMeta) stack.getItemMeta();
        meta.setBasePotionType(type);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        stack.setItemMeta(meta);
        return stack;
    }

    static ItemStack createHeadItem(String name, String textureUrl) {
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
