package com.souldi.origins_winter_fabric.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FairyWingsItem extends ArmorItem implements GeoItem {
    // Кеш для анимаций
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // ВАЖНО: Этот Supplier обязательно нужен для GeckoLib
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public FairyWingsItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
        // Регистрируем этот предмет для анимаций
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    // Регистрация контроллеров анимации
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 20, animationState -> {
            animationState.getController().setAnimation(RawAnimation.begin().thenLoop("animation.fairy_wings.idle"));
            return PlayState.CONTINUE;
        }));
    }

    // Получение кеша анимаций
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // ВАЖНО: Этот метод обязателен для GeoItem
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        // Будет реализован в клиентском коде
    }

    // КРИТИЧЕСКИ ВАЖНО: Этот метод должен возвращать непустой Supplier
    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }
}