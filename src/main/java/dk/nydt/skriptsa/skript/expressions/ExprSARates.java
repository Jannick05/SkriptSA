package dk.nydt.skriptsa.skript.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;

import org.jetbrains.annotations.Nullable;

public class ExprSARates extends SimplePropertyExpression<OfflinePlayer, Long> {

    static {
        register(ExprSARates.class, Long.class, "rates", "offlineplayer");
    }

    @Override
    protected String getPropertyName() {
        return "rates";
    }

    @Override
    public @Nullable Long convert(OfflinePlayer offlinePlayer) {
        return MutalUtils.getRatings(offlinePlayer.getName());
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }
}
