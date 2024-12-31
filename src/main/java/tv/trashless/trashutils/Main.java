package tv.trashless.trashutils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tv.trashless.trashutils.commands.OpenFoodLevelSettingsCommand;
import tv.trashless.trashutils.commands.OpenHealthSettingsCommand;
import tv.trashless.trashutils.listeners.InventoryClickListener;
import tv.trashless.trashutils.listeners.JoinListener;
import tv.trashless.trashutils.listeners.QuitListener;
import tv.trashless.trashutils.utils.FoodLevel;
import tv.trashless.trashutils.utils.Health;

public final class Main extends JavaPlugin {

    /*
    TODO:   - Config Utility Class
            - Load Health and FoodLevel from Config
            - Custom Food Level System --> Scalable
     */

    static Main instance;

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fLoading...");

        new Health();
        new FoodLevel();

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fFinished loading!");
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fEnabling plugin...");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);

        this.getCommand("healthsettings").setExecutor(new OpenHealthSettingsCommand());
        this.getCommand("foodlevelsettings").setExecutor(new OpenFoodLevelSettingsCommand());

        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fPlugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fDisabling plugin...");



        Bukkit.getConsoleSender().sendMessage(getPrefix() + "§fPlugin disabled!");
    }

    public static Main getInstance() {
        return instance;
    }

    public String getPrefix() {
        return "§8[§3Trash§9Utils§8] §r";
    }
}
