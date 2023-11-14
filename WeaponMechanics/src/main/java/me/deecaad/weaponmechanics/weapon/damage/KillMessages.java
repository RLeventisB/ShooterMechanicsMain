package me.deecaad.weaponmechanics.weapon.damage;

import me.deecaad.core.file.SerializeData;
import me.deecaad.core.file.Serializer;
import me.deecaad.core.file.SerializerException;
import me.deecaad.core.mechanics.CastData;
import me.deecaad.core.placeholder.PlaceholderMessage;
import me.deecaad.weaponmechanics.events.WeaponMechanicsEntityDamageByEntityEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class KillMessages implements Serializer<KillMessages>
{
    public PlaceholderMessage damageText, headshotText, explosionText;
    /**
     * Default constructor for serializer
     */
    public KillMessages()
    {
    }
    public KillMessages(PlaceholderMessage damageText, PlaceholderMessage headshotText, PlaceholderMessage explosionText)
    {
        this.damageText = damageText;
        this.headshotText = headshotText;
        this.explosionText = explosionText;

        if (Objects.equals(damageText.getTemplate(), ""))
            this.damageText = null;
        if (Objects.equals(headshotText.getTemplate(), ""))
            this.headshotText = null;
        if (Objects.equals(explosionText.getTemplate(), ""))
            this.explosionText = null;
    }
    public String getModifiedDeathText(String defaultText, boolean isExplosion, boolean isHeadshot, WeaponMechanicsEntityDamageByEntityEvent wmEvent)
    {
        PlaceholderMessage message = damageText;
        if (isExplosion && explosionText != null)
        {
            message = explosionText;
        }
        if (isHeadshot && headshotText != null)
        {
            message = headshotText;
        }
        if (message != null)
        {
            CastData cast = new CastData((LivingEntity) wmEvent.getDamager(), wmEvent.weaponTitle, wmEvent.itemStack);
            cast.setTargetEntity((LivingEntity) wmEvent.getEntity());
            cast.setTargetLocation(wmEvent.getEntity().getLocation());
            return LegacyComponentSerializer.legacySection().serialize(message.replaceAndDeserialize(cast));
        }
        return defaultText;
    }
    @Override
    public String getKeyword()
    {
        return "Kill_Messages";
    }
    @Override
    @NotNull
    public KillMessages serialize(@NotNull SerializeData data) throws SerializerException
    {
        return new KillMessages(
                new PlaceholderMessage(data.of("Death_Message").get("")),
                new PlaceholderMessage(data.of("Headshot_Death_Message").get("")),
                new PlaceholderMessage(data.of("Explosion_Death_Message").get("")));
    }
}