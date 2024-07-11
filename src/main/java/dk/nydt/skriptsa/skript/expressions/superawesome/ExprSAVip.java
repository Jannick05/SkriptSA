package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprSAVip extends SimplePropertyExpression<OfflinePlayer, Long> {
    static {
        register(ExprSAVip.class, Long.class, "vip days", "offlineplayer");
    }
    @Override
    protected String getPropertyName() {
        return "vip days";
    }

    @Override
    public @Nullable Long convert(OfflinePlayer offlinePlayer) {
        return MutalUtils.getVipDays(offlinePlayer.getName());
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }
}
