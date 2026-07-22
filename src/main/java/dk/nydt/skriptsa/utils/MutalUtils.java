package dk.nydt.skriptsa.utils;

import dk.nydt.skriptsa.storage.DBManager;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MutalUtils {

    private static JSONObject getPlayerData(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        try {
            JSONParser parser = new JSONParser();

            return (JSONObject) parser.parse(
                    IOUtils.toString(
                            new URL("https://api.superawesome.dk/players/" + name),
                            StandardCharsets.UTF_8
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Long getPlayerPoints(String name) {
        JSONObject json = getPlayerData(name);

        if (json == null || json.get("playerPoints") == null) {
            return -1L;
        }

        return ((Number) json.get("playerPoints")).longValue();
    }

    public static Long getRatings(String name) {
        JSONObject json = getPlayerData(name);

        if (json == null || json.get("rate") == null) {
            return -1L;
        }

        return ((Number) json.get("rate")).longValue();
    }

    public static Long getVipDays(String name) {
        JSONObject json = getPlayerData(name);

        if (json == null || json.get("vipDays") == null) {
            return -1L;
        }

        return ((Number) json.get("vipDays")).longValue();
    }

    public static String getStaffRole(String name) {
        JSONObject json = getPlayerData(name);

        if (json == null) {
            return null;
        }

        return (String) json.get("role");
    }

    /*
     * RATE-FUNKTIONER
     */

    private static JSONArray getRateData(String name) {
        JSONObject json = getPlayerData(name);

        if (json == null || !(json.get("rates") instanceof JSONArray)) {
            return null;
        }

        return (JSONArray) json.get("rates");
    }

    public static Long getPositiveRateCount(String name) {
        return getRateCount(name, "positive");
    }

    public static Long getNegativeRateCount(String name) {
        return getRateCount(name, "negative");
    }

    private static Long getRateCount(String name, String requiredType) {
        JSONArray rates = getRateData(name);

        if (rates == null) {
            return -1L;
        }

        long count = 0L;

        for (Object object : rates) {
            if (!(object instanceof JSONObject)) {
                continue;
            }

            JSONObject rate = (JSONObject) object;

            if (requiredType.equals(rate.get("type"))) {
                count++;
            }
        }

        return count;
    }

    public static String[] getRateNames(String name) {
        return getRateNames(name, null);
    }

    public static String[] getPositiveRateNames(String name) {
        return getRateNames(name, "positive");
    }

    public static String[] getNegativeRateNames(String name) {
        return getRateNames(name, "negative");
    }

    private static String[] getRateNames(String name, String requiredType) {
        JSONArray rates = getRateData(name);

        if (rates == null) {
            return new String[0];
        }

        List<String> names = new ArrayList<>();

        for (Object object : rates) {
            if (!(object instanceof JSONObject)) {
                continue;
            }

            JSONObject rate = (JSONObject) object;

            if (requiredType != null && !requiredType.equals(rate.get("type"))) {
                continue;
            }

            Object username = rate.get("username");

            if (username instanceof String) {
                names.add((String) username);
            }
        }

        return names.toArray(new String[0]);
    }

    public static boolean isPlayerVIP(String name) {
        return getVipDays(name) > 0L;
    }

    public static boolean isPlayerStaff(String name) {
        return !Objects.equals(getStaffRole(name), "player");
    }

    public static boolean isPlayerServerbooster(String name) {
        return DBManager.hasBoost(name);
    }
}