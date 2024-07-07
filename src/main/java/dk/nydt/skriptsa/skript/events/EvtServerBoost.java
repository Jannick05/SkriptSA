package dk.nydt.skriptsa.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import dk.nydt.skriptsa.events.ServerBoost;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EvtServerBoost extends SkriptEvent {
    static {
        Skript.registerEvent("serverboost", EvtServerBoost.class, ServerBoost.class, "[SkriptSA ][on ]serverboost");

        EventValues.registerEventValue(ServerBoost.class, OfflinePlayer.class, new Getter<OfflinePlayer, ServerBoost>() {
            public OfflinePlayer get(ServerBoost event) {
                return event.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ServerBoost.class, Integer.class, new Getter<Integer, ServerBoost>() {
            public Integer get(ServerBoost event) {
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
        return "on serverboost";
    }
}

