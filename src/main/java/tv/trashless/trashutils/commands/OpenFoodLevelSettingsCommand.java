package tv.trashless.trashutils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.trashless.trashutils.inventories.Inventories;

public class OpenFoodLevelSettingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            player.openInventory(Inventories.updateFoodLevelSettingsInventory());
        }
        return true;
    }
}
