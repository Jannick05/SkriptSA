package dk.nydt.skriptsa.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

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
