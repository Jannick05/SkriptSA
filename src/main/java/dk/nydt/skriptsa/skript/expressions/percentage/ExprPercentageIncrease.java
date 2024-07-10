package dk.nydt.skriptsa.skript.expressions.percentage;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprPercentageIncrease extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ExprPercentageIncrease.class, Double.class, ExpressionType.SIMPLE, "(stining fra|increase from) %double% (til|to) %double%");
    }

    private Expression<Double> number;
    private Expression<Double> number2;

    @Override
    protected @Nullable Double[] get(Event event) {
        Double localNumber = number.getSingle(event);
        Double localNumber2 = number2.getSingle(event);
        if (localNumber2 == null || localNumber == null) {
            return null;
        }

        Double newNumber = localNumber2 - localNumber;

        return new Double[] {(newNumber / localNumber) * 100};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Calculation of percentage increase";
    }

    @Override @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        number = (Expression<Double>) expressions[0];
        number2 = (Expression<Double>) expressions[1];

        return true;
    }
}
