package com.souldi.origins_winter_fabric.registry;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import com.souldi.origins_winter_fabric.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModItems {
    private static final FabricItemSettings DEF = new FabricItemSettings();

    // icon items used by Origins GUI
    public static final Item DARK_ELF_ICON = new Item(DEF);
    public static final Item SNOW_ELF      = new Item(DEF);
    public static final Item FOREST_ELF    = new Item(DEF);
    public static final Item WHITE_DWARF   = new Item(DEF);
    public static final Item GRAY_DWARF    = new Item(DEF);
    public static final Item GOLDEN_DWARF  = new Item(DEF);
    public static final Item FIRE_FAIRY    = new Item(DEF);
    public static final Item WINTER_FAIRY  = new Item(DEF);
    public static final Item FLOWER_FAIRY  = new Item(DEF);
    public static final Item RED_REAPER    = new Item(DEF);
    public static final Item DEATH_REAPER  = new Item(DEF);
    public static final Item DEMON         = new Item(DEF);
    public static final Item CATMORPH      = new Item(DEF);
    public static final Item DOG           = new Item(DEF);
    public static final Item FOXY          = new Item(DEF);

    // animated armour
    private static final FairyArmorMaterial MAT = new FairyArmorMaterial();
    public static final Item FAIRY_CHESTPLATE = new FairyArmorItem(MAT, ArmorItem.Type.CHESTPLATE, DEF);

    private static final WinterFairyArmorMaterial W_MAT = new WinterFairyArmorMaterial();
    public static final Item WINTER_FAIRY_CHESTPLATE = new WinterFairyArmorItem(W_MAT, ArmorItem.Type.CHESTPLATE, DEF);

    // Добавленный Fire Fairy комплект
    private static final FireFairyArmorMaterial F_MAT = new FireFairyArmorMaterial();
    public static final Item FIRE_FAIRY_CHESTPLATE = new FireFairyArmorItem(F_MAT, ArmorItem.Type.CHESTPLATE, DEF);



    public static void register() {
        recordIt("dark_elf_icon", DARK_ELF_ICON);
        recordIt("snow_elf", SNOW_ELF);
        recordIt("forest_elf", FOREST_ELF);
        recordIt("white_dwarf", WHITE_DWARF);
        recordIt("gray_dwarf", GRAY_DWARF);
        recordIt("golden_dwarf", GOLDEN_DWARF);
        recordIt("fire_fairy", FIRE_FAIRY);
        recordIt("winter_fairy", WINTER_FAIRY);
        recordIt("flower_fairy", FLOWER_FAIRY);
        recordIt("red_reaper", RED_REAPER);
        recordIt("death_reaper", DEATH_REAPER);
        recordIt("demon", DEMON);
        recordIt("catmorph", CATMORPH);
        recordIt("dog", DOG);
        recordIt("foxy", FOXY);
        recordIt("fairy_chestplate", FAIRY_CHESTPLATE);
        recordIt("winter_fairy_chestplate", WINTER_FAIRY_CHESTPLATE);
        recordIt("fire_fairy_chestplate", FIRE_FAIRY_CHESTPLATE);
    }

    private static void recordIt(String id, Item item) {
        Registry.register(Registries.ITEM, Origins_Winter_Fabric.id(id), item);
    }
}