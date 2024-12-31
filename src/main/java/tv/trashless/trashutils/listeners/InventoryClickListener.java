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
import tv.trashless.trashutils.inventories.FoodLevelSettingsGUI;
import tv.trashless.trashutils.inventories.HealthSettingsGUI;
import tv.trashless.trashutils.items.Items;
import tv.trashless.trashutils.utils.FoodLevel;
import tv.trashless.trashutils.utils.Health;

import java.util.Collections;

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
                SignGUI gui = SignBuilders.INPUT()
                        .setHandler((player, result) -> {
                            String input = result.getLineWithoutColor(2);

                            if (!input.isEmpty()) {
                                double newMaxHealth = Double.parseDouble(input);
                                if (Health.isShared()) Health.setMax(Bukkit.getOnlinePlayers(), newMaxHealth);
                                else Health.setMax(player, newMaxHealth);
                            }

                            return Collections.emptyList();
                        })
                        .build();
                gui.open(whoClicked);
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
                SignGUI gui = SignBuilders.INPUT()
                        .setHandler((player, result) -> {
                            String input = result.getLineWithoutColor(2);

                            if (!input.isEmpty()) {
                                int newMaxFoodLevel = Integer.parseInt(input);
                                FoodLevel.setMax(player, newMaxFoodLevel);
                            }

                            return Collections.emptyList();
                        })
                        .build();
                gui.open(whoClicked);
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
