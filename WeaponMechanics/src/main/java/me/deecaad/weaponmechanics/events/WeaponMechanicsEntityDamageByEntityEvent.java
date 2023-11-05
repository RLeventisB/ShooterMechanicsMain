package me.deecaad.weaponmechanics.events;

import com.google.common.base.Function;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * WeaponMechanics event so plugins can detect custom events fired by WeaponMechanics
 */
public class WeaponMechanicsEntityDamageByEntityEvent extends EntityDamageByEntityEvent
{
    public final String weaponTitle;
    public final boolean isHeadshot;
    public final boolean isExplosion;
    public final ItemStack itemStack;
    public WeaponMechanicsEntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, double damage, String weaponTitle, boolean isHeadshot, boolean isExplosion, ItemStack itemStack)
    {
        super(damager, damagee, cause, damage);
        this.weaponTitle = weaponTitle;
        this.isHeadshot = isHeadshot;
        this.isExplosion = isExplosion;
        this.itemStack = itemStack;
    }

    public WeaponMechanicsEntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions, String weaponTitle, boolean isHeadshot, boolean isExplosion, ItemStack itemStack)
    {
        super(damager, damagee, cause, modifiers, modifierFunctions);
        this.weaponTitle = weaponTitle;
        this.isHeadshot = isHeadshot;
        this.isExplosion = isExplosion;
        this.itemStack = itemStack;
    }
}
