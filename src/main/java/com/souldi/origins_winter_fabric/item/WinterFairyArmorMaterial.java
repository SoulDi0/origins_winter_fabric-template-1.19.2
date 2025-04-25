package com.souldi.origins_winter_fabric.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/** Same stats as normal fairy, just a different texture namespace */
public class WinterFairyArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DUR = {13, 15, 16, 11};
    private static final int[] PROT     = {3, 6, 7, 3}; // a bit warmer 😉

    @Override public int getDurability(ArmorItem.Type slot){return BASE_DUR[slot.getEquipmentSlot().getEntitySlotId()] * 25;}
    @Override public int getProtection(ArmorItem.Type slot){return PROT[slot.getEquipmentSlot().getEntitySlotId()];}
    @Override public int getEnchantability()               {return 18;}
    @Override public SoundEvent getEquipSound()            {return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;}
    @Override public Ingredient getRepairIngredient()      {return Ingredient.fromTag(ItemTags.WOOL);}
    @Override public String getName()                      {return "winter_fairy";}  // matches texture path
    @Override public float getToughness()                  {return 1.5f;}
    @Override public float getKnockbackResistance()        {return 0f;}
}