package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

@Environment(EnvType.CLIENT)
public class FairyWingsModel extends GeoModel<FairyWingsItem> {
    @Override
    public Identifier getModelResource(FairyWingsItem object) {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "geo/fairy_wings.geo.json");
    }

    @Override
    public Identifier getTextureResource(FairyWingsItem object) {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "textures/item/armor/fairy_wings.png");
    }

    @Override
    public Identifier getAnimationResource(FairyWingsItem animatable) {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "animations/fairy_wings.animation.json");
    }
}