package com.souldi.origins_winter_fabric.client.renderer;

import com.souldi.origins_winter_fabric.client.model.WinterFairyArmorModel;
import com.souldi.origins_winter_fabric.item.WinterFairyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class WinterFairyArmorRenderer extends GeoArmorRenderer<WinterFairyArmorItem> {
    public WinterFairyArmorRenderer(){super(new WinterFairyArmorModel());}
}