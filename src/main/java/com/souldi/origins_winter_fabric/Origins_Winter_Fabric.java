package com.souldi.origins_winter_fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Origins_Winter_Fabric implements ModInitializer {
	public static final String MOD_ID = "origins_winter_fabric";

	// Логгер мода
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}