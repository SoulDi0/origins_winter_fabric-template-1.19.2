package com.souldi.origins_winter_fabric.client;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import com.souldi.origins_winter_fabric.client.renderer.WinterFairyWingsRenderer;
import com.souldi.origins_winter_fabric.item.WinterFairyWingsItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

@Environment(EnvType.CLIENT)
public class Origins_Winter_FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Origins_Winter_Fabric.LOGGER.info("Initializing Origins Winter Fabric client...");

        // Регистрация рендерера для брони
        GeoArmorRenderer.registerArmorRenderer(WinterFairyWingsRenderer::new,
                Origins_Winter_Fabric.WINTER_FAIRY_WINGS);
    }
}