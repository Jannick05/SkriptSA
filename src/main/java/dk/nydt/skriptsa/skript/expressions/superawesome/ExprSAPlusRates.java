package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprSAPlusRates extends SimplePropertyExpression<OfflinePlayer, Long> {

    static {
        register(
                ExprSAPlusRates.class,
                Long.class,
                "plus[ ]rates",
                "offlineplayer"
        );
    }

    @Override
    protected String getPropertyName() {
        return "plus rates";
    }

    @Override
    public @Nullable Long convert(OfflinePlayer offlinePlayer) {
        String name = offlinePlayer.getName();

        if (name == null) {
            return -1L;
        }

        return MutalUtils.getPositiveRateCount(name);
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }
}