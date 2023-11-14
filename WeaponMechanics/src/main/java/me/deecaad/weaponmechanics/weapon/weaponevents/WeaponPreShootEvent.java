package me.deecaad.weaponmechanics.weapon.weaponevents;

import me.deecaad.weaponmechanics.weapon.trigger.TriggerType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Called before a {@link WeaponShootEvent}.
 */
public class WeaponPreShootEvent extends WeaponEvent implements Cancellable
{

    private static final HandlerList HANDLERS = new HandlerList();
    private TriggerType triggerType;
    private boolean isCancelled;

    public WeaponPreShootEvent(String weaponTitle, ItemStack weaponItem, LivingEntity weaponUser, EquipmentSlot hand, TriggerType triggerType)
    {
        super(weaponTitle, weaponItem, weaponUser, hand);
        this.triggerType = triggerType;
    }

    @Override
    public boolean isCancelled()
    {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled)
    {
        isCancelled = cancelled;
    }

    @Override
    @NotNull
    public HandlerList getHandlers()
    {
        return HANDLERS;
    }

    public static HandlerList getHandlerList()
    {
        return HANDLERS;
    }
    public TriggerType getTriggerType()
    {
        return triggerType;
    }
    public void setTriggerType(TriggerType triggerType)
    {
        this.triggerType = triggerType;
    }
}
