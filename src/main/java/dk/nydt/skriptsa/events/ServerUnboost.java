package dk.nydt.skriptsa.events;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerUnboost extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final OfflinePlayer player;
    @Getter
    private final int boost;
    private Boolean cancelled = Boolean.FALSE;

    public ServerUnboost(OfflinePlayer player, int boost) {
        this.player = player;
        this.boost = boost;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
