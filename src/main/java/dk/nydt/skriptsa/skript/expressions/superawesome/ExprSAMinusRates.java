package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprSAMinusRates extends SimplePropertyExpression<OfflinePlayer, Long> {

    static {
        register(
                ExprSAMinusRates.class,
                Long.class,
                "minus[ ]rates",
                "offlineplayer"
        );
    }

    @Override
    protected String getPropertyName() {
        return "minus rates";
    }

    @Override
    public @Nullable Long convert(OfflinePlayer offlinePlayer) {
        String name = offlinePlayer.getName();

        if (name == null) {
            return -1L;
        }

        return MutalUtils.getNegativeRateCount(name);
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }
}