package tv.trashless.trashutils.listeners;

import de.rapha149.signgui.SignGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.gui.SignBuilders;
import tv.trashless.trashutils.inventories.Inventories;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.FoodLevelSettings;
import tv.trashless.trashutils.utils.HealthSettings;

import java.util.Collections;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        Player whoClicked = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem().clone();

        assert clickedInventory != null;
        if (clickedInventory.equals(Inventories.currentHealthSettingsInventory())) {
            event.setCancelled(true);

            if (clickedItem.equals(Items.GUI_MAX_HEALTH(whoClicked))) {
                SignGUI gui = SignBuilders.INPUT()
                        .setHandler((player, result) -> {
                            String input = result.getLineWithoutColor(2);

                            if (!input.isEmpty()) {
                                double newMaxHealth = Double.parseDouble(input);
                                if (HealthSettings.isShared()) HealthSettings.setMaxHealth(Bukkit.getOnlinePlayers(), newMaxHealth);
                                else HealthSettings.setMaxHealth(player, newMaxHealth);
                            }

                            return Collections.emptyList();
                        })
                        .build();
                gui.open(whoClicked);
                return;
            }

            if (clickedItem.equals(Items.GUI_RESTORE_HEALTH())) {
                whoClicked.setHealth(HealthSettings.getMaxHealth(whoClicked));
            }

            if (clickedItem.equals(Items.GUI_SHARED_HEALTH())) HealthSettings.setShared(!HealthSettings.isShared());

            if (clickedItem.equals(Items.GUI_NATURAL_REGEN())) {
                HealthSettings.setNaturalRegen(!HealthSettings.isNaturalRegen());
                if (HealthSettings.isNaturalRegen()) HealthSettings.setRegen(true);
            }

            if (clickedItem.equals(Items.GUI_REGEN())) {
                HealthSettings.setRegen(!HealthSettings.isRegen());
                if (!HealthSettings.isRegen()) HealthSettings.setNaturalRegen(false);
            }

            whoClicked.openInventory(Inventories.updateHealthSettingsInventory(whoClicked));
        }

        if (clickedInventory.equals(Inventories.currentFoodLevelSettingsInventory())) {
            event.setCancelled(true);

            if (clickedItem.equals(Items.GUI_RESTORE_FOOD_LEVEL())) {
                whoClicked.setFoodLevel(20);
            }

            if (clickedItem.equals(Items.GUI_SHARED_FOOD_LEVEL())) FoodLevelSettings.setShared(!FoodLevelSettings.isShared());

            whoClicked.openInventory(Inventories.updateFoodLevelSettingsInventory());
        }
    }
}
