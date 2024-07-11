package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

public class ExprSARole extends SimplePropertyExpression<OfflinePlayer, String> {
    static {
        register(ExprSARole.class, String.class, "role", "offlineplayer");
    }
    @Override
    protected String getPropertyName() {
        return "role";
    }

    @Override
    public @Nullable String convert(OfflinePlayer offlinePlayer) {
        return MutalUtils.getStaffRole(offlinePlayer.getName());
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
