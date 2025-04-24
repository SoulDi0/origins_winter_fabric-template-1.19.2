package com.souldi.origins_winter_fabric.client.renderer;

import com.souldi.origins_winter_fabric.item.WinterFairyWingsItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WinterFairyWingsRenderer extends GeoArmorRenderer<WinterFairyWingsItem> {
    public WinterFairyWingsRenderer() {
        super(new WinterFairyWingsModel());
    }
}