package dk.nydt.skriptsa.utils;

import dk.nydt.skriptsa.SkriptSA;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class MutalUtils {

    private static JSONObject getPlayerData(String name) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(IOUtils.toString(new URL("https://api.superawesome.dk/players/" + name), StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long getRatings(String name) {
        JSONObject json = getPlayerData(name);
        if (json == null || json.get("rate") == null) {
            return (long) -1;
        }
        return (Long) json.get("rate");
    }

    public static Long getVipDays(String name) {
        JSONObject json = getPlayerData(name);
        if (json == null || json.get("vipDays") == null) {
            return (long) -1;
        }
        return (Long) json.get("vipDays");
    }

    public static String getStaffRole(String name) {
        JSONObject json = getPlayerData(name);
        if (json == null) {
            return null;
        }
        return (String) json.get("role");
    }

    public static boolean isPlayerVIP(String name) {
        return getVipDays(name) > 0L;
    }

    public static boolean isPlayerStaff(String name) {
        return !(Objects.equals(getStaffRole(name), "player"));
    }
}
