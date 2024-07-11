package dk.nydt.skriptsa.skript.expressions.utilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dk.nydt.skriptsa.utils.SkullUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprGetHead extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprGetHead.class, ItemStack.class, ExpressionType.SIMPLE, "get head %string%");
    }

    private Expression<String> texture;

    @Override
    protected @Nullable ItemStack[] get(Event event) {
        String localTexture = texture.getSingle(event);
        if(localTexture == null) {
            return null;
        }
        return new ItemStack[] {SkullUtils.getHead(localTexture, true)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean b) {
        return "Get head from texture";
    }

    @Override @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        texture = (Expression<String>) expressions[0];

        return true;
    }
}
