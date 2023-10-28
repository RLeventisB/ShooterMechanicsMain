package me.deecaad.core.mechanics.targeters;

import me.deecaad.core.file.SerializeData;
import me.deecaad.core.mechanics.CastData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
public class FlatCircleTargeter extends ShapeTargeter {
    private final Vector[] points;
    public FlatCircleTargeter() {
        this.points = null;
    }
    public FlatCircleTargeter(int points, double radius) {
        this.points = new Vector[points];
        for (int i = 0; i < points; i++)
        {
            double angle = (double)i / points * Math.PI * 2.0;
            this.points[i] = new Vector(Math.cos(angle) * radius, 0, Math.sin(angle) * radius);
        }
    }

    @Override
    public @NotNull Iterator<Vector> getPoints(@NotNull CastData castData)
    {
        return java.util.Arrays.stream(points).iterator();
    }

    public String getKeyword() {
        return "FlatCircle";
    }

    @NotNull
    public Targeter serialize(SerializeData serializeData) throws me.deecaad.core.file.SerializerException {
        int n = serializeData.of("Points").assertExists().getInt();
        double d = serializeData.of("Radius").assertExists().getDouble();
        return this.applyParentArgs(serializeData, new FlatCircleTargeter(n, d));
    }
}
