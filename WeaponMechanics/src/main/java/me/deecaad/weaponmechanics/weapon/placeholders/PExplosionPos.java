package me.deecaad.weaponmechanics.weapon.placeholders;

import javax.annotation.Nullable;

import me.deecaad.core.placeholder.PlaceholderData;
import me.deecaad.core.placeholder.PlaceholderHandler;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class PExplosionPos extends PlaceholderHandler
{
    public static Vector explosionPos;

    public PExplosionPos() {
        super("explosion_pos");
    }

    @Nullable
    public String onRequest(@NotNull PlaceholderData data) {
        return explosionPos.getX() + " " + explosionPos.getY() + " " + explosionPos.getZ();
    }
}
