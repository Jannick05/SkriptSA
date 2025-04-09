package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprPlayerPoints extends SimplePropertyExpression<OfflinePlayer, Long> {

    static {
        register(ExprPlayerPoints.class, Long.class, "playerpoints", "offlineplayer");
    }

    @Override
    protected String getPropertyName() {
        return "playerpoints";
    }

    @Override
    public @Nullable Long convert(OfflinePlayer offlinePlayer) {
        return MutalUtils.getPlayerPoints(offlinePlayer.getName());
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }
}
