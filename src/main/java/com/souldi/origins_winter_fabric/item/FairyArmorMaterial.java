package com.souldi.origins_winter_fabric.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class FairyArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DUR = {13, 15, 16, 11};
    private static final int[] PROT     = {2, 5, 6, 2};

    @Override public int getDurability(ArmorItem.Type slot){return BASE_DUR[slot.getEquipmentSlot().getEntitySlotId()] * 25;}
    @Override public int getProtection(ArmorItem.Type slot){return PROT[slot.getEquipmentSlot().getEntitySlotId()];}
    @Override public int getEnchantability()               {return 15;}
    @Override public SoundEvent getEquipSound()            {return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;}
    @Override public Ingredient getRepairIngredient()      {return Ingredient.fromTag(ItemTags.LAPIS_ORES);} // vanilla tag
    @Override public String getName()                      {return "fairy";}
    @Override public float getToughness()                  {return 1.0f;}
    @Override public float getKnockbackResistance()        {return 0f;}
}