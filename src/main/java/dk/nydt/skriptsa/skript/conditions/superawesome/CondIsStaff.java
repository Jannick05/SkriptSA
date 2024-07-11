package dk.nydt.skriptsa.skript.conditions.superawesome;

import ch.njol.skript.conditions.base.PropertyCondition;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;


public class CondIsStaff extends PropertyCondition<OfflinePlayer> {

    static {
        register(CondIsStaff.class, PropertyType.BE, " staff", "offlineplayers");
    }

    @Override
    public boolean check(OfflinePlayer offlinePlayer) {
        return MutalUtils.isPlayerStaff(offlinePlayer.getName());
    }

    @Override
    protected String getPropertyName() {
        return " staff";
    }
}
