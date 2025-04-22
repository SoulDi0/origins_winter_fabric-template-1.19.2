package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Добавляем Fairy Wings
    public static final FairyWingsItem FAIRY_WINGS = new FairyWingsItem(
            FairyWingsArmorMaterial.INSTANCE,
            ArmorItem.Type.CHESTPLATE,
            new FabricItemSettings().maxCount(1)
    );

    // Другие предметы могут быть добавлены здесь

    public static void registerItems() {
        // Регистрируем Fairy Wings
        Registry.register(
                Registries.ITEM,
                new Identifier(Origins_Winter_Fabric.MOD_ID, "fairy_wings"),
                FAIRY_WINGS
        );

        // Другие регистрации предметов
    }
}