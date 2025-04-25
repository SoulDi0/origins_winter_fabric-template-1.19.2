package com.souldi.origins_winter_fabric.client.model;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import com.souldi.origins_winter_fabric.item.WinterFairyArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WinterFairyArmorModel extends GeoModel<WinterFairyArmorItem> {
    @Override public Identifier getModelResource(WinterFairyArmorItem o){return Origins_Winter_Fabric.id("geo/armor/winter_fairy_armor.geo.json");}
    @Override public Identifier getTextureResource(WinterFairyArmorItem o){return Origins_Winter_Fabric.id("textures/armor/winter_fairy_armor.png");}
    @Override public Identifier getAnimationResource(WinterFairyArmorItem o){return Origins_Winter_Fabric.id("animations/armor/winter_fairy_armor.animation.json");}
}