package tv.trashless.trashutils.utils.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class HeadItemBuilder extends ItemBuilder {
    private String textureUrl;

    public HeadItemBuilder setTextureUrl(String textureUrl) {
        this.textureUrl = textureUrl;
        return this;
    }

    public ItemStack build() {
        ItemStack stack = new ItemBuilder()
                .setType(Material.PLAYER_HEAD)
                .setStackSize(stackSize)
                .setItemName(itemName)
                .setDisplayName(displayName)
                .setLore(lore)
                .build();

        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.fromString("92864445-51c5-4c3b-9039-517c9927d1b4")); // Get a new player profile
        PlayerTextures textures = profile.getTextures();

        URL urlObject;
        try {
            urlObject = URI.create(textureUrl).toURL(); // The URL to the skin
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
