package tv.trashless.trashutils.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.inventories.HealthSettingsGUI;
import tv.trashless.trashutils.inventories.Items;
import tv.trashless.trashutils.utils.HealthSettings;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        Player whoClicked = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem().clone();

        assert clickedInventory != null;
        if (clickedInventory.equals(HealthSettingsGUI.current())) {
            event.setCancelled(true);

            if (clickedItem.equals(Items.GUI_GENERIC_INCREASE())) {
                if (HealthSettings.isSharedHealth()) HealthSettings.increaseMaxHealthForAll();
                else HealthSettings.increaseMaxHealthForPlayer(whoClicked);
            }

            if (clickedItem.equals(Items.GUI_GENERIC_DECREASE())) {
                if (HealthSettings.isSharedHealth()) HealthSettings.decreaseMaxHealthForAll();
                else HealthSettings.decreaseMaxHealthForPlayer(whoClicked);
            }

            if (clickedItem.equals(Items.GUI_SETTINGS_HEALTH_SHARED_HEALTH())) HealthSettings.setSharedHealth(!HealthSettings.isSharedHealth());

            if (clickedItem.equals(Items.GUI_SETTINGS_HEALTH_NATURAL_REGENERATION())) {
                HealthSettings.setNaturalRegeneration(!HealthSettings.isNaturalRegeneration());
                if (HealthSettings.isNaturalRegeneration()) HealthSettings.setRegeneration(true);
            }

            if (clickedItem.equals(Items.GUI_SETTINGS_HEALTH_REGENERATION())) {
                HealthSettings.setRegeneration(!HealthSettings.isRegeneration());
                if (!HealthSettings.isRegeneration()) HealthSettings.setNaturalRegeneration(false);
            }

            whoClicked.openInventory(HealthSettingsGUI.update(whoClicked));
        }
    }
}
