package dk.nydt.skriptsa.skript.events.superawesome;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import dk.nydt.skriptsa.events.ServerUnboost;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EvtServerUnboost extends SkriptEvent {
    static {
        Skript.registerEvent("serverunboost", EvtServerUnboost.class, ServerUnboost.class, "[SkriptSA ][on ]serverunboost");

        EventValues.registerEventValue(ServerUnboost.class, OfflinePlayer.class, new Getter<OfflinePlayer, ServerUnboost>() {
            public OfflinePlayer get(ServerUnboost event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ServerUnboost.class, Integer.class, new Getter<Integer, ServerUnboost>() {
            public Integer get(ServerUnboost event) {
                return event.getBoost();
            }
        }, 0);
    }
    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "on serverunboost";
    }
}

