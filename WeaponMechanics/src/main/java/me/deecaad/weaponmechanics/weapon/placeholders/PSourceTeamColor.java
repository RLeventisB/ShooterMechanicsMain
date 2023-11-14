package me.deecaad.weaponmechanics.weapon.placeholders;

import me.deecaad.core.mechanics.CastData;
import me.deecaad.core.placeholder.PlaceholderData;
import me.deecaad.core.placeholder.PlaceholderHandler;
import me.deecaad.weaponmechanics.Tools;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PSourceTeamColor extends PlaceholderHandler
{

    public PSourceTeamColor()
    {
        super("source_team_color");
    }

    @Nullable
    @Override
    public String onRequest(@NotNull PlaceholderData data)
    {
        if (data instanceof CastData castData)
        {
            Team team = Tools.getTeam(castData.getSource());
            if (team == null)
                return "";
            if (team.getColor().isFormat())
            {
                return "";
            }

            return "<#" + Integer.toHexString(team.getColor().asBungee().getColor().getRGB()).substring(2, 8) + ">";
        }
        return "";
    }
}
