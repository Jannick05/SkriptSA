package dk.nydt.skriptsa;

import ch.njol.skript.Skript;
import dk.nydt.skriptsa.config.Config;
import dk.nydt.skriptsa.storage.DBManager;
import dk.nydt.skriptsa.storage.objects.Boosts;
import dk.nydt.skriptsa.tasks.Updater;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class SkriptSA extends JavaPlugin {
    @Getter
    private static SkriptSA instance;
    @Getter
    private static Config configuration;
    @Getter @Setter
    private static JSONObject json;
    @Getter
    private static Updater updater;
    @Getter
    private static DBManager dbManager;

    @Override
    public void onEnable() {
        instance = this;

        configuration = ConfigManager.create(Config.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });

        dbManager = new DBManager();

        // Load the data from the API
        updater = new Updater();
        updater.setData(); // Get the data once before starting the task
        updater.run(); // Start the task

        if(getServer().getPluginManager().getPlugin("Skript") != null) try {
            Skript.registerAddon(this).loadClasses("dk.nydt.skriptsa", "skript");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the addon.", e);
        }

    }

    @Override
    public void onDisable() {
        dbManager.saveBoost();
        dbManager.close();
    }
}