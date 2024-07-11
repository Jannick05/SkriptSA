package dk.nydt.skriptsa.skript.conditions.superawesome;

import ch.njol.skript.conditions.base.PropertyCondition;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;

public class CondIsVip extends PropertyCondition<OfflinePlayer> {

    static {
        register(CondIsVip.class, PropertyType.BE, " vip", "offlineplayers");
    }

    @Override
    public boolean check(OfflinePlayer offlinePlayer) {
        return MutalUtils.isPlayerVIP(offlinePlayer.getName());
    }

    @Override
    protected String getPropertyName() {
        return " vip";
    }
}
