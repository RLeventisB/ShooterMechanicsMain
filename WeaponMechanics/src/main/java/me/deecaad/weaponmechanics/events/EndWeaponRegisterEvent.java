package me.deecaad.weaponmechanics.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EndWeaponRegisterEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final int weaponCount;
    /**
     * Called when the plugin ends registering weapons.
     */
    public EndWeaponRegisterEvent(int totalWeapons)
    {
        this.weaponCount = totalWeapons;
    }

    public int getWeaponCount() { return weaponCount;}

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}