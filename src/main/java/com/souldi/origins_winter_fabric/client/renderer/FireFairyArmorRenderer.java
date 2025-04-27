package com.souldi.origins_winter_fabric.client.renderer;

import com.souldi.origins_winter_fabric.client.model.FireFairyArmorModel;
import com.souldi.origins_winter_fabric.item.FireFairyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FireFairyArmorRenderer extends GeoArmorRenderer<FireFairyArmorItem> {
    public FireFairyArmorRenderer(){super(new FireFairyArmorModel());}
}