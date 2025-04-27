package com.souldi.origins_winter_fabric;

import com.souldi.origins_winter_fabric.command.FairyWingsCommand;
import com.souldi.origins_winter_fabric.event.WingsRemovalHandler;
import com.souldi.origins_winter_fabric.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public final class Origins_Winter_Fabric implements ModInitializer {
	public static final String MOD_ID = "origins_winter_fabric";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override public void onInitialize() {
		LOG.info("Initializing Origins Winter Fabric …");
		GeckoLib.initialize();

		FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(mod ->
				ResourceManagerHelper.registerBuiltinResourcePack(id("origins_data"), mod, ResourcePackActivationType.ALWAYS_ENABLED));

		ModItems.register();
		CommandRegistry.init();
		FairyWingsCommand.init();
		WingsRemovalHandler.register();
		LOG.info("All custom icons, fairy armour & commands registered 🙂");
	}
}