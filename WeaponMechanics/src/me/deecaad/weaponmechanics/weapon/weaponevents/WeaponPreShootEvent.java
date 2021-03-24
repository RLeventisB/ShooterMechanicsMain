package me.deecaad.weaponmechanics.weapon.weaponevents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * This class outlines the event before shooting calculations and projectile
 * spawning takes place. This event is great for cancelling shooting.
 */
public class WeaponPreShootEvent extends WeaponEvent implements Cancellable {

    private boolean isCancelled;

    public WeaponPreShootEvent(String weaponTitle, ItemStack weaponItem, LivingEntity weaponUser) {
        super(weaponTitle, weaponItem, weaponUser);
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
