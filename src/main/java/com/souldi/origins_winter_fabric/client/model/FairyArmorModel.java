package com.souldi.origins_winter_fabric.client.model;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import com.souldi.origins_winter_fabric.item.FairyArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FairyArmorModel extends GeoModel<FairyArmorItem> {
    @Override public Identifier getModelResource(FairyArmorItem object) {return Origins_Winter_Fabric.id("geo/armor/fairy_armor.geo.json");}
    @Override public Identifier getTextureResource(FairyArmorItem obj)  {return Origins_Winter_Fabric.id("textures/armor/fairy_armor.png");}
    @Override public Identifier getAnimationResource(FairyArmorItem obj){return Origins_Winter_Fabric.id("animations/armor/fairy_armor.animation.json");}
}