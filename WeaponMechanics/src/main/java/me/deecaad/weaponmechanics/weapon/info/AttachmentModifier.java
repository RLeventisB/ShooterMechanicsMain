package me.deecaad.weaponmechanics.weapon.info;

import me.deecaad.core.file.SerializeData;
import me.deecaad.core.file.Serializer;
import me.deecaad.core.file.SerializerException;
import me.deecaad.weaponmechanics.weapon.trigger.Circumstance;
import me.deecaad.weaponmechanics.weapon.trigger.TriggerType;
import me.deecaad.weaponmechanics.wrappers.EntityWrapper;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static me.deecaad.weaponmechanics.WeaponMechanics.getConfigurations;

public class AttachmentModifier implements Serializer<AttachmentModifier>
{
    public static HashMap<String, String[]> modifyTree;
    public List<AttachmentEntry> mainHandAttachments, offHandAttachments;
    private boolean evenSpread;

    /**
     * Default constructor for serializer
     */
    public AttachmentModifier()
    {
    }
    public AttachmentModifier(List<AttachmentEntry> attachmentMapMain, List<AttachmentEntry> attachmentMapOff)
    {
        this.mainHandAttachments = attachmentMapMain;
        this.offHandAttachments = attachmentMapOff;
    }
    public String ModifyWeaponTitleIfValid(EntityWrapper entityWrapper, String weaponTitle, EquipmentSlot slot, TriggerType triggerType)
    {
        if (slot == EquipmentSlot.HAND)
        {
            for (AttachmentEntry entry : mainHandAttachments)
            {
                if (entry.circumstance.isTrue(entityWrapper))
                {
                    return entry.weaponAttachment;
                }
            }
        }
        else
        {
            for (AttachmentEntry entry : offHandAttachments)
            {
                if (entry.circumstance.isTrue(entityWrapper))
                {
                    return entry.weaponAttachment;
                }
            }
        }
        return weaponTitle;
    }
    public static ModifiedWeaponResult isModifiedWeaponTitle(final String weaponTitleToCheck, final EquipmentSlot slot, final EntityWrapper entityWrapper)
    {
        List<String> originalCandidates = new ArrayList<>();
        for (String key : modifyTree.keySet())
        {
            if (Arrays.stream(modifyTree.get(key)).anyMatch(weaponTitleToCheck::contains))
            {
                originalCandidates.add(key);
            }
        }

        if (originalCandidates.isEmpty())
            return new ModifiedWeaponResult(false, null, null);
        for (String candidate : originalCandidates)
        {
            AttachmentModifier modifier = getConfigurations().getObject(candidate + ".Shoot.Attachment_Modifier", AttachmentModifier.class);
            for (AttachmentEntry entry : (slot == EquipmentSlot.HAND ? modifier.mainHandAttachments : modifier.offHandAttachments))
            {
                if (Objects.equals(entry.weaponAttachment, weaponTitleToCheck))
                {
                    return new ModifiedWeaponResult(true, candidate, entry);
                }
            }
        }
        return new ModifiedWeaponResult(false, null, null);
    }
    @Override
    public String getKeyword()
    {
        return "Attachment_Modifier";
    }
    @Override
    @NotNull
    public AttachmentModifier serialize(@NotNull SerializeData data) throws SerializerException
    {
        List<String[]> mainHandMap = data.ofList("Main_Hand")
                .addArgument(Circumstance.CircumstanceType.class, true)
                .addArgument(String.class, true)
                .assertExists().assertList().get();

        List<String[]> offHandMap = data.ofList("Off_Hand")
                .addArgument(Circumstance.CircumstanceType.class, true)
                .addArgument(String.class, true)
                .assertExists().assertList().get();

        List<AttachmentEntry> mainHandAttachments = new ArrayList<>(), offHandAttachments = new ArrayList<>();
        for (String[] text : mainHandMap)
        {
            mainHandAttachments.add(new AttachmentEntry(new Circumstance.CircumstanceData(Circumstance.CircumstanceType.valueOf(text[0]), false), text[1]));
        }
        for (String[] text : offHandMap)
        {
            offHandAttachments.add(new AttachmentEntry(new Circumstance.CircumstanceData(Circumstance.CircumstanceType.valueOf(text[0]), false), text[1]));
        }
        return new AttachmentModifier(mainHandAttachments, offHandAttachments);
    }

    public record AttachmentEntry(Circumstance.CircumstanceData circumstance, String weaponAttachment)
    {

    }
    public record ModifiedWeaponResult(boolean isModified, String originalWeapon, AttachmentEntry entry)
    {

    }
}
