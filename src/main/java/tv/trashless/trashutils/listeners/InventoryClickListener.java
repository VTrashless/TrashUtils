package tv.trashless.trashutils.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tv.trashless.trashutils.gui.Signs;
import tv.trashless.trashutils.inventories.FoodLevelSettingsGUI;
import tv.trashless.trashutils.inventories.HealthSettingsGUI;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.FoodLevel;
import tv.trashless.trashutils.utils.Health;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        Player whoClicked = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem().clone();

        assert clickedInventory != null;
        if (clickedInventory.equals(HealthSettingsGUI.current())) {
            event.setCancelled(true);

            if (clickedItem.equals(Items.GUI_MAX_HEALTH(whoClicked))) {
                Signs.INPUT_NEW_MAX_HEALTH().open(whoClicked);
                return;
            }

            if (clickedItem.equals(Items.GUI_RESTORE_HEALTH())) {
                whoClicked.setHealth(Health.getMax(whoClicked));
            }

            if (clickedItem.equals(Items.GUI_SHARED_HEALTH())) Health.setShared(!Health.isShared());

            if (clickedItem.equals(Items.GUI_NATURAL_REGEN())) {
                Health.setNaturalRegen(!Health.isNaturalRegen());
                if (Health.isNaturalRegen()) Health.setRegen(true);
            }

            if (clickedItem.equals(Items.GUI_REGEN())) {
                Health.setRegen(!Health.isRegen());
                if (!Health.isRegen()) Health.setNaturalRegen(false);
            }

            whoClicked.openInventory(HealthSettingsGUI.update(whoClicked));
        }

        if (clickedInventory.equals(FoodLevelSettingsGUI.current())) {
            event.setCancelled(true);

            if (clickedItem.equals(Items.GUI_MAX_FOOD_LEVEL(whoClicked))) {
                Signs.INPUT_NEW_MAX_FOOD_LEVEL().open(whoClicked);
                return;
            }

            if (clickedItem.equals(Items.GUI_RESTORE_FOOD_LEVEL())) {
                //whoClicked.sendHealthUpdate(whoClicked.getHealth(), FoodLevelSettings.getMaxFoodLevel(whoClicked), 0f);
            }

            if (clickedItem.equals(Items.GUI_SHARED_FOOD_LEVEL())) FoodLevel.setShared(!FoodLevel.isShared());

            if (clickedItem.equals(Items.GUI_FOOD_LEVEL_DECAY())) FoodLevel.setDecay(!FoodLevel.isDecay());

            whoClicked.openInventory(FoodLevelSettingsGUI.update(whoClicked));
        }
    }
}
