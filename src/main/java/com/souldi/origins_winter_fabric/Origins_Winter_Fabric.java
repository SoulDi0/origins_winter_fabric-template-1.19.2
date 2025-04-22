package com.souldi.origins_winter_fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class Origins_Winter_Fabric implements ModInitializer {
	public static final String MOD_ID = "origins_winter_fabric";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final Item DARK_ELF_ICON = new Item(new FabricItemSettings());
	public static final Item SNOW_ELF = new Item(new FabricItemSettings());
	public static final Item FOREST_ELF = new Item(new FabricItemSettings());
	public static final Item WHITE_DWARF = new Item(new FabricItemSettings());
	public static final Item GRAY_DWARF = new Item(new FabricItemSettings());
	public static final Item GOLDEN_DWARF = new Item(new FabricItemSettings());

	// Ссылка на FairyWings для использования в FairyClientRegistry


	@Override
	public void onInitialize() {
		LOGGER.info("Origins Winter Fabric mod is initializing!");

		// Инициализация GeckoLib (общая для клиента и сервера)
		GeckoLib.initialize();

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(modContainer -> {
			ResourceManagerHelper.registerBuiltinResourcePack(
					new Identifier(MOD_ID, "origins_data"),
					modContainer,
					ResourcePackActivationType.ALWAYS_ENABLED
			);
		});

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "dark_elf_icon"), DARK_ELF_ICON);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "snow_elf"), SNOW_ELF);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "forest_elf"), FOREST_ELF);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "white_dwarf"), WHITE_DWARF);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "gray_dwarf"), GRAY_DWARF);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "golden_dwarf"), GOLDEN_DWARF);

		CommandRegistry.init();


		LOGGER.info("Custom icons, items, armor and commands registered!");
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}