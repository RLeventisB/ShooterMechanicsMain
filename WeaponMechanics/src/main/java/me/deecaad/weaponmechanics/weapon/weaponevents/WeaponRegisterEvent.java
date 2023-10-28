package me.deecaad.weaponmechanics.weapon.weaponevents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WeaponRegisterEvent extends WeaponEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    public WeaponRegisterEvent(String weaponTitle, ItemStack weaponStack)
    {
        super(weaponTitle, weaponStack, null, null);
    }
    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
