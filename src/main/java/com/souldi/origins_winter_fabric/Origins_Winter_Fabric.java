package com.souldi.origins_winter_fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Origins_Winter_Fabric implements ModInitializer {
	public static final String MOD_ID = "origins_winter_fabric";

	// Логгер мода
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Создаем предмет для иконки Темного эльфа
	public static final Item DARK_ELF_ICON = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static final Item SNOW_ELF = new Item(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		LOGGER.info("Origins Winter Fabric mod is initializing!");

		// Регистрируем ресурспак с данными
		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer -> {
			ResourceManagerHelper.registerBuiltinResourcePack(
					new Identifier(MOD_ID, "origins_data"),
					modContainer,
					ResourcePackActivationType.ALWAYS_ENABLED
			);
		});

		// Регистрируем иконку как предмет
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "dark_elf_icon"), DARK_ELF_ICON);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "snow_elf"), SNOW_ELF);
		LOGGER.info("Custom icons registered!");
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}