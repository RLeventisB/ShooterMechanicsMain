package me.deecaad.core.mechanics.defaultmechanics;

import me.deecaad.core.file.SerializeData;
import me.deecaad.core.file.SerializerException;
import me.deecaad.core.mechanics.CastData;
import me.deecaad.core.utils.VectorUtil;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PushMechanic extends Mechanic
{

    private double speed;
    private double verticalMultiplier;
    private boolean add;

    /**
     * Default constructor for serializer.
     */
    public PushMechanic()
    {
    }

    public PushMechanic(double speed, double verticalMultiplier, boolean add)
    {
        this.speed = speed;
        this.verticalMultiplier = verticalMultiplier;
        this.add = add;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getVerticalMultiplier()
    {
        return verticalMultiplier;
    }

    @Override
    protected void use0(CastData cast)
    {

        // We must have an entity to push
        if (cast.getTarget() == null)
            return;

        Vector velocity = cast.getTargetLocation().subtract(cast.getSourceLocation()).toVector();

        // When the target location is the same as the source location, we get
        // an empty vector.
        if (VectorUtil.isEmpty(velocity))
            return;

        velocity.setY(velocity.getY() * verticalMultiplier);
        if (velocity.length() == 0)
            return;
        velocity.normalize().multiply(speed);
        
        if (this.add)
        {
            Vector targetVelocity = cast.getTarget().getVelocity();
            targetVelocity.add(velocity);
            cast.getTarget().setVelocity(targetVelocity);
        }
        else
        {
            cast.getTarget().setVelocity(velocity);
        }
    }

    @Override
    public String getKeyword()
    {
        return "Push";
    }

    @Override
    public @Nullable String getWikiLink()
    {
        return "https://cjcrafter.gitbook.io/mechanics/mechanics/push";
    }

    @NotNull
    @Override
    public Mechanic serialize(@NotNull SerializeData data) throws SerializerException
    {
        double speed = data.of("Speed").assertExists().getDouble();
        double verticalMultiplier = data.of("Vertical_Multiplier").getDouble(1.0);
        boolean add = data.of("Add").getBool(true);

        return applyParentArgs(data, new PushMechanic(speed, verticalMultiplier, add));
    }
}
