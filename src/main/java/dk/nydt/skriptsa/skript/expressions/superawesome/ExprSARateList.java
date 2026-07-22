package dk.nydt.skriptsa.skript.expressions.superawesome;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dk.nydt.skriptsa.utils.MutalUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprSARateList extends SimpleExpression<String> {

    private Expression<? extends OfflinePlayer> offlinePlayer;
    private int listType;

    static {
        Skript.registerExpression(
                ExprSARateList.class,
                String.class,
                ExpressionType.PROPERTY,

                // arg-1's rateliste / rate list
                "%offlineplayer%'[s] rate[ ]list[e]",

                // arg-1's plusratesliste / plus rates list
                "%offlineplayer%'[s] plus[ ]rate[s][ ]list[e]",

                // arg-1's minusratesliste / minus rates list
                "%offlineplayer%'[s] minus[ ]rate[s][ ]list[e]"
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(
            Expression<?>[] expressions,
            int matchedPattern,
            Kleenean isDelayed,
            ParseResult parseResult
    ) {
        offlinePlayer =
                (Expression<? extends OfflinePlayer>) expressions[0];

        listType = matchedPattern;
        return true;
    }

    @Override
    protected String[] get(Event event) {
        OfflinePlayer player = offlinePlayer.getSingle(event);

        if (player == null || player.getName() == null) {
            return new String[0];
        }

        switch (listType) {
            case 1:
                return MutalUtils.getPositiveRateNames(player.getName());

            case 2:
                return MutalUtils.getNegativeRateNames(player.getName());

            case 0:
            default:
                return MutalUtils.getRateNames(player.getName());
        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        String property;

        switch (listType) {
            case 1:
                property = "plus rates list";
                break;

            case 2:
                property = "minus rates list";
                break;

            case 0:
            default:
                property = "rate list";
                break;
        }

        return offlinePlayer.toString(event, debug) + "'s " + property;
    }
}