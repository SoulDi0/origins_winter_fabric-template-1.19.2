package com.souldi.origins_winter_fabric.client.renderer;

import com.souldi.origins_winter_fabric.client.model.FairyArmorModel;
import com.souldi.origins_winter_fabric.item.FairyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FairyArmorRenderer extends GeoArmorRenderer<FairyArmorItem> {
    public FairyArmorRenderer() {super(new FairyArmorModel());}
}