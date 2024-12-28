package tv.trashless.trashutils.inventories;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class Items {
    public static ItemStack GuiFiller() {
        return createBasicItem(" ", Material.BLACK_STAINED_GLASS_PANE);
    }

    public static ItemStack GuiPagePrevious() {
        return createHeadItem("Previous page", "http://textures.minecraft.net/texture/e35e42fc7060c223acc965f7c5996f272644af40a4723a372f5903f8e9f188e7");
    }

    public static ItemStack GuiPageNext() {
        return createHeadItem("Next page", "http://textures.minecraft.net/texture/aee0f82fb33f6cfa5169b9f5eafe4dc1c73618c9783b131adada411d8f605505");
    }

    private static ItemStack createBasicItem(String name, Material material) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setItemName(" ");
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack createItemWithDesc(String name, Material material, List<String> description) {
        ItemStack stack = createBasicItem(name, material);
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(description);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack createHeadItem(String name, String textureUrl) {
        ItemStack head = createBasicItem(name, Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID()); // Get a new player profile
        PlayerTextures textures = profile.getTextures();

        URL urlObject;
        try {
            urlObject = new URL(textureUrl); // The URL to the skin
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Invalid URL", exception);
        }

        textures.setSkin(urlObject);
        profile.setTextures(textures); // Set the textures back to the profile
        headMeta.setOwnerProfile(profile); // Set the owning player of the head to the player profile
        head.setItemMeta(headMeta);
        return head;
    }
}
