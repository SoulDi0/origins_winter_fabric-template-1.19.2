package com.souldi.origins_winter_fabric.client.model;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import com.souldi.origins_winter_fabric.item.FireFairyArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FireFairyArmorModel extends GeoModel<FireFairyArmorItem> {
    @Override public Identifier getModelResource(FireFairyArmorItem o){return Origins_Winter_Fabric.id("geo/armor/fire_fairy_armor.geo.json");}
    @Override public Identifier getTextureResource(FireFairyArmorItem o){return Origins_Winter_Fabric.id("textures/armor/fire_fairy_armor.png");}
    @Override public Identifier getAnimationResource(FireFairyArmorItem o){return Origins_Winter_Fabric.id("animations/armor/fire_fairy_armor.animation.json");}
}