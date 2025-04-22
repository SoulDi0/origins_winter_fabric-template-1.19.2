package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

@Environment(EnvType.CLIENT)
public class FairyWingsRenderer extends GeoArmorRenderer<FairyWingsItem> {

    public FairyWingsRenderer() {
        super(new FairyWingsModel());
    }

    @Override
    public Identifier getTextureLocation(FairyWingsItem object) {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "textures/item/armor/fairy_wings.png");
    }
}