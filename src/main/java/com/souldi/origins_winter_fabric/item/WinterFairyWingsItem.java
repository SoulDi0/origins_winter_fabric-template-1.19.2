package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WinterFairyWingsItem extends ArmorItem implements GeoAnimatable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    public WinterFairyWingsItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 20, state -> {
            state.getController().setAnimation(IDLE_ANIMATION);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object o) {
        return 0;
    }

    // Для получения пути к модели в других классах
    public Identifier getModelResource() {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "geo/item/winter_fairy_wings.geo.json");
    }

    // Для получения пути к текстуре в других классах
    public Identifier getTextureResource() {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "textures/item/winter_fairy_wings.png");
    }

    // Для получения пути к анимации в других классах
    public Identifier getAnimationResource() {
        return new Identifier(Origins_Winter_Fabric.MOD_ID, "animations/item/winter_fairy_wings.animation.json");
    }
}