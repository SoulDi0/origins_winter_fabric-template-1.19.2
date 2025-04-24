package com.souldi.origins_winter_fabric.client.renderer;

import com.souldi.origins_winter_fabric.item.WinterFairyWingsItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WinterFairyWingsModel extends GeoModel<WinterFairyWingsItem> {
    @Override
    public Identifier getModelResource(WinterFairyWingsItem object) {
        return object.getModelResource();
    }

    @Override
    public Identifier getTextureResource(WinterFairyWingsItem object) {
        return object.getTextureResource();
    }

    @Override
    public Identifier getAnimationResource(WinterFairyWingsItem object) {
        return object.getAnimationResource();
    }
}