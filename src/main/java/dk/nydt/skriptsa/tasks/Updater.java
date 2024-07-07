package dk.nydt.skriptsa.tasks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dk.nydt.skriptsa.SkriptSA;
import dk.nydt.skriptsa.events.ServerBoost;
import dk.nydt.skriptsa.events.ServerUnboost;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class Updater {
    private int updateInterval;
    private String serverName;

    private JSONArray previousBoosts;
    private JSONArray currentBoosts;
    public Updater() {
        this.updateInterval = SkriptSA.getConfiguration().updateInterval;
        this.serverName = SkriptSA.getConfiguration().serverName.toLowerCase();
    }

    public Updater(int updateInterval, String serverName) {
        this.updateInterval = updateInterval;
        this.serverName = serverName.toLowerCase();
    }

    public Updater setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
        return this;
    }

    public Updater setServerName(String serverName) {
        this.serverName = serverName.toLowerCase();
        return this;
    }

    public void getData() {
        String url = "https://api.superawesome.dk/servers/" + serverName;
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(IOUtils.toString(new URL(url), StandardCharsets.UTF_8));
            SkriptSA.setJson(json);
            checkBoost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBoost() {
        JSONObject json = SkriptSA.getJson();
        currentBoosts = (JSONArray) json.get("serverBoosts");

        if (previousBoosts == null) {
            previousBoosts = currentBoosts;
        }

        if (currentBoosts.size() > previousBoosts.size()) {
            for(Object boost : currentBoosts) {
                JSONObject boostObject = (JSONObject) boost;
                if (!previousBoosts.toString().contains(boostObject.toString())) {
                    Bukkit.getScheduler().runTaskAsynchronously(SkriptSA.getInstance(), () -> {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(boostObject.get("username").toString());
                        Bukkit.getServer().getPluginManager().callEvent(new ServerBoost(player, currentBoosts.size()));
                    });
                }
            }
        } else if (currentBoosts.size() < previousBoosts.size()){
            for(Object boost : previousBoosts) {
                JSONObject boostObject = (JSONObject) boost;
                if (!currentBoosts.toString().contains(boostObject.toString())) {
                    Bukkit.getScheduler().runTaskAsynchronously(SkriptSA.getInstance(), () -> {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(boostObject.get("username").toString());
                        Bukkit.getServer().getPluginManager().callEvent(new ServerUnboost(player, currentBoosts.size()));
                    });
                }
            }
        }
        previousBoosts = currentBoosts;
    }

    public void run() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(SkriptSA.getInstance(), this::getData, 0, updateInterval * 20L);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTasks(SkriptSA.getInstance());
    }

    public void restart() {
        stop();
        run();
    }

}