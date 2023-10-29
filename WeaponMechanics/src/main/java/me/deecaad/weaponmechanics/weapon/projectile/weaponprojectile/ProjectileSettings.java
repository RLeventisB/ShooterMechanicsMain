package me.deecaad.weaponmechanics.weapon.projectile.weaponprojectile;

import me.deecaad.core.file.SerializeData;
import me.deecaad.core.file.Serializer;
import me.deecaad.core.file.SerializerException;
import me.deecaad.core.file.serializers.ItemSerializer;
import me.deecaad.core.utils.ReflectionUtil;
import me.deecaad.weaponmechanics.utils.NumberHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class ProjectileSettings implements Serializer<ProjectileSettings>, Cloneable {

    private EntityType projectileDisguise;
    private Object disguiseData;

    private Number gravity;
    private Number gravityDelayTicks;
    private Number dragDelayTicks;
    private boolean removeAtMinimumSpeed;
    private Number minimumSpeed;
    private boolean removeAtMaximumSpeed;
    private Number maximumSpeed;

    private Number decrease;
    private Number decreaseInWater;
    private Number decreaseWhenRainingOrSnowing;

    private Number damageMultiplier;
    private boolean disableEntityCollisions;
    private boolean applyDragHorizontally;
    private boolean maximizeYWhenDragIsHorizontal;
    private boolean dragPositiveYWhenDragIsHorizontal;
    private Number maximumAliveTicks;
    private Number maximumTravelDistance;
    private Number size;

    /**
     * Empty constructor to be used as serializer
     */
    public ProjectileSettings() {
    }

    public ProjectileSettings(EntityType projectileDisguise, Object disguiseData, Number gravity, boolean removeAtMinimumSpeed, Number minimumSpeed, boolean removeAtMaximumSpeed, Number maximumSpeed, Number decrease, Number decreaseInWater, Number decreaseWhenRainingOrSnowing, boolean disableEntityCollisions, Number maximumAliveTicks, Number maximumTravelDistance, Number size, Number gravityDelayTicks, Number dragDelayTicks, boolean applyDragHorizontally, boolean maximizeYWhenDragIsHorizontal, boolean dragPositiveYWhenDragIsHorizontal, Number damageMultiplier) {
        this.projectileDisguise = projectileDisguise;
        this.disguiseData = disguiseData;
        this.gravity = gravity;
        this.removeAtMinimumSpeed = removeAtMinimumSpeed;
        this.minimumSpeed = minimumSpeed;
        this.removeAtMaximumSpeed = removeAtMaximumSpeed;
        this.maximumSpeed = maximumSpeed;
        this.decrease = decrease;
        this.decreaseInWater = decreaseInWater;
        this.decreaseWhenRainingOrSnowing = decreaseWhenRainingOrSnowing;
        this.disableEntityCollisions = disableEntityCollisions;
        this.maximumAliveTicks = maximumAliveTicks;
        this.maximumTravelDistance = maximumTravelDistance;
        this.gravityDelayTicks = gravityDelayTicks;
        this.dragDelayTicks = dragDelayTicks;
        this.size = size;
        this.applyDragHorizontally = applyDragHorizontally;
        this.maximizeYWhenDragIsHorizontal = maximizeYWhenDragIsHorizontal;
        this.dragPositiveYWhenDragIsHorizontal = dragPositiveYWhenDragIsHorizontal;
        this.damageMultiplier = damageMultiplier;
    }

    /**
     * @return the entity type this projectile should be disguised as
     */
    @Nullable
    public EntityType getProjectileDisguise() {
        return this.projectileDisguise;
    }

    public void setProjectileDisguise(EntityType projectileDisguise) {
        this.projectileDisguise = projectileDisguise;
    }

    /**
     * Only certain entities need this. For example falling block, entity item and so on.
     * FALLING_BLOCK -> Material
     * ENTITY_ITEM -> ItemStack
     * FIREWORK -> ItemStack with FireworkMeta
     *
     * @return the item stack which may be used when spawning projectile disguise
     */
    @Nullable
    public Object getDisguiseData() {
        return disguiseData;
    }

    public void setDisguiseData(Object disguiseData) {
        this.disguiseData = disguiseData;
    }

    /**
     * @return gravity of projectile
     */
    public double getGravity() {
        return gravity.doubleValue();
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     * @return minimum speed of projectile
     */
    public double getMinimumSpeed() {
        return minimumSpeed.doubleValue();
    }

    public void setMinimumSpeed(double minimumSpeed) {
        this.minimumSpeed = minimumSpeed;
    }

    public boolean isYCappedOnHorizontalDrag() {
        return this.maximizeYWhenDragIsHorizontal;
    }

    public void setYCappedOnHorizontalDrag(boolean yCappedOnHorizontalDrag) {
        this.maximizeYWhenDragIsHorizontal = yCappedOnHorizontalDrag;
    }
    public boolean isPositiveYDraggedWhenDragIsHorizontal() {
        return this.dragPositiveYWhenDragIsHorizontal;
    }

    public void setPositiveYDraggedWhenDragIsHorizontal(boolean dragPositiveYWhenDragIsHorizontal) {
        this.dragPositiveYWhenDragIsHorizontal = dragPositiveYWhenDragIsHorizontal;
    }

    public boolean isDragHorizontal() {
        return this.applyDragHorizontally;
    }

    public void setDragHorizontal(boolean applyDragHorizontally) {
        this.applyDragHorizontally = applyDragHorizontally;
    }
    /**
     * @return whether to remove projectile when minimum speed is reached
     */
    public boolean isRemoveAtMinimumSpeed() {
        return this.removeAtMinimumSpeed;
    }

    public void setRemoveAtMinimumSpeed(boolean removeAtMinimumSpeed) {
        this.removeAtMinimumSpeed = removeAtMinimumSpeed;
    }

    public int getGravityDelayTicks() {
        return this.gravityDelayTicks.intValue();
    }

    public void setGravityDelayTicks(int gravityDelayTicks) {
        this.gravityDelayTicks = gravityDelayTicks;
    }

    public int getDragDelayTicks() {
        return this.dragDelayTicks.intValue();
    }

    public void setDragDelayTicks(int dragDelayTicks) {
        this.dragDelayTicks = dragDelayTicks;
    }

    /**
     * @return maximum speed of projectile
     */
    public double getMaximumSpeed() {
        return maximumSpeed.doubleValue();
    }

    public void setMaximumSpeed(double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    /**
     * @return whether to remove projectile when maximum speed is reached
     */
    public boolean isRemoveAtMaximumSpeed() {
        return this.removeAtMaximumSpeed;
    }

    public void setRemoveAtMaximumSpeed(boolean removeAtMaximumSpeed) {
        this.removeAtMaximumSpeed = removeAtMaximumSpeed;
    }

    /**
     * @return base speed decreasing
     */
    public double getDecrease() {
        return decrease.doubleValue();
    }

    public void setDecrease(double decrease) {
        this.decrease = decrease;
    }

    /**
     * @return speed decreasing in water
     */
    public double getDecreaseInWater() {
        return decreaseInWater.doubleValue();
    }

    public void setDecreaseInWater(double decreaseInWater) {
        this.decreaseInWater = decreaseInWater;
    }

    /**
     * @return speed decreasing when raining or snowing
     */
    public double getDecreaseWhenRainingOrSnowing() {
        return decreaseWhenRainingOrSnowing.doubleValue();
    }

    public void setDecreaseWhenRainingOrSnowing(double decreaseWhenRainingOrSnowing) {
        this.decreaseWhenRainingOrSnowing = decreaseWhenRainingOrSnowing;
    }

    /**
     * @return whether to skip entity collision checks
     */
    public boolean isDisableEntityCollisions() {
        return disableEntityCollisions;
    }

    public void setDisableEntityCollisions(boolean disableEntityCollisions) {
        this.disableEntityCollisions = disableEntityCollisions;
    }

    /**
     * @return the maximum amount of ticks projectile can be alive
     */
    public int getMaximumAliveTicks() {
        return maximumAliveTicks.intValue();
    }

    public void setMaximumAliveTicks(int maximumAliveTicks) {
        this.maximumAliveTicks = maximumAliveTicks;
    }

    /**
     * @return the maximum travel distance of projectile, -1 if not used
     */
    public double getMaximumTravelDistance() {
        return maximumTravelDistance.intValue();
    }

    public void setMaximumTravelDistance(double maximumTravelDistance) {
        this.maximumTravelDistance = maximumTravelDistance;
    }

    /**
     * @return the projectile size, 0.1 if not used
     */
    public double getSize() {
        return size.doubleValue();
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String getKeyword() {
        return "Projectile_Settings";
    }

    @Override
    @NotNull
    public ProjectileSettings serialize(@NotNull SerializeData data) throws SerializerException {

        String type = data.of("Type").assertExists().get().toString().trim().toUpperCase(Locale.ROOT);
        boolean isInvisible = type.equals("INVISIBLE");

        Object disguiseData = null;
        EntityType projectileType = null;

        if (!isInvisible) {
            projectileType = data.of("Type").assertExists().getEnum(EntityType.class);
            ItemStack projectileItem = data.of("Projectile_Item_Or_Block").serialize(new ItemSerializer());
            if ((projectileType == EntityType.DROPPED_ITEM
                    || projectileType == EntityType.FALLING_BLOCK)
                    && projectileItem == null) {

                throw data.exception(null, "When using " + projectileType + ", you MUST use Projectile_Item_Or_Block");
            }

            if (projectileItem != null) {
                if (projectileType == EntityType.FIREWORK && !(projectileItem.getItemMeta() instanceof FireworkMeta)) {

                    throw data.exception(null, "When using " + projectileType + ", the item must be a firework",
                            SerializerException.forValue(projectileItem));
                }

                if (projectileType == EntityType.FALLING_BLOCK) {
                    disguiseData = projectileItem.getType();
                } else {
                    disguiseData = projectileItem;
                }

                if (projectileType != EntityType.DROPPED_ITEM
                        && projectileType != EntityType.FALLING_BLOCK
                        && projectileType != EntityType.FIREWORK
                        && projectileType != EntityType.ARMOR_STAND
                        && (ReflectionUtil.getMCVersion() >= 19 && projectileType != EntityType.ITEM_DISPLAY && projectileType != EntityType.BLOCK_DISPLAY)) {

                    throw data.exception(null, "When using " + projectileType + ", you CAN'T use Projectile_Item_Or_Block",
                            SerializerException.forValue(projectileItem));
                }
            }
        }

        Number gravity = NumberHelper.divideBy(data.of("Gravity").getNumber(10.0), 200.0);
        Number minimumSpeed = NumberHelper.divideBy(data.of("Minimum.Speed").assertPositive().getNumber(-20.0), 20.0);
        boolean removeAtMinimumSpeed = data.of("Minimum.Remove_Projectile_On_Speed_Reached").getBool(false);
        Number maximumSpeed = NumberHelper.divideBy(data.of("Maximum.Speed").assertPositive().getNumber(-20.0), 20.0);
        boolean removeAtMaximumSpeed = data.of("Maximum.Remove_Projectile_On_Speed_Reached").getBool(false);
        Number decrease = data.of("Drag.Base").assertRange(0.0, 3.0).getNumber(0.99);
        Number decreaseInWater = data.of("Drag.In_Water").assertRange(0.0, 3.0).getNumber(0.96);
        Number decreaseWhenRainingOrSnowing = data.of("Drag.When_Raining_Or_Snowing").getNumber(0.98);
        boolean disableEntityCollisions = data.of("Disable_Entity_Collisions").getBool(false);
        Number maximumAliveTicks = data.of("Maximum_Alive_Ticks").assertPositive().getNumber(600);
        Number gravityDelayTicks = data.of("Gravity_Delay_Ticks").assertPositive().getNumber(0);
        Number dragDelayTicks = data.of("Drag.Delay").assertPositive().getNumber(0);
        Number maximumTravelDistance = data.of("Maximum_Travel_Distance").assertPositive().getNumber(-1.0);
        Number size = data.of("Size").assertPositive().getNumber(0.1);
        boolean applyDragHorizontally = data.of("Drag.OnlyHorizontally").getBool(false);
        boolean maximizeYWhenDragIsHorizontal = data.of("Drag.ZeroYWhenHorizontal").getBool(false);
        boolean dragPositiveYWhenDragIsHorizontal = data.of("Drag.DragPositiveYWhenHorizontal").getBool(true);
        Number damageMultiplier = data.of("Damage_Multiplier").getNumber(1.0);
        return new ProjectileSettings(projectileType, disguiseData, gravity, removeAtMinimumSpeed, minimumSpeed, removeAtMaximumSpeed, maximumSpeed, decrease, decreaseInWater, decreaseWhenRainingOrSnowing, disableEntityCollisions, maximumAliveTicks, maximumTravelDistance, size, gravityDelayTicks, dragDelayTicks, applyDragHorizontally, maximizeYWhenDragIsHorizontal, dragPositiveYWhenDragIsHorizontal, damageMultiplier);
    }

    @Override
    public ProjectileSettings clone() {
        try {
            return (ProjectileSettings) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Number getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(Number damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
}