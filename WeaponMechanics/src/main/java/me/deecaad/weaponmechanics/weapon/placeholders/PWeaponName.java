package me.deecaad.weaponmechanics.weapon.placeholders;

import me.deecaad.core.placeholder.PlaceholderData;
import me.deecaad.core.placeholder.PlaceholderHandler;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PWeaponName extends PlaceholderHandler
{

    public PWeaponName()
    {
        super("weapon_item_name");
    }

    @Nullable
    @Override
    public String onRequest(@NotNull PlaceholderData data)
    {
        return ChatColor.stripColor(data.item().getItemMeta().getDisplayName());
    }
}
