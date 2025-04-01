package dk.nydt.skriptsa.skript.conditions.superawesome;

import ch.njol.skript.conditions.base.PropertyCondition;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;

public class CondIsServerbooster extends PropertyCondition<OfflinePlayer> {

    static {
        register(CondIsServerbooster.class, PropertyType.BE, " serverbooster", "offlineplayers");
    }

    @Override
    public boolean check(OfflinePlayer offlinePlayer) {
        return MutalUtils.isPlayerServerbooster(offlinePlayer.getName());
    }

    @Override
    protected String getPropertyName() {
        return " serverbooster";
    }
}