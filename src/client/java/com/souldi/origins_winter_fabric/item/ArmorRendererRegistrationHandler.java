package com.souldi.origins_winter_fabric.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Вспомогательный класс для регистрации рендереров брони
 * с использованием рефлексии, если стандартные методы не работают
 */
@Environment(EnvType.CLIENT)
public class ArmorRendererRegistrationHandler {

    /**
     * Регистрирует рендерер брони напрямую через рефлексию
     * Это обходной путь, если стандартные методы GeckoLib не работают
     */
    public static <T extends Item & GeoItem> void registerArmorRenderer(T item, GeoArmorRenderer<T> renderer) {
        try {
            // Ищем поле ARMOR_RENDERERS в классе GeoArmorRenderer
            Field renderersField = GeoArmorRenderer.class.getDeclaredField("ARMOR_RENDERERS");
            renderersField.setAccessible(true);

            // Получаем значение поля (должно быть Map)
            Map<Class<?>, GeoArmorRenderer<?>> renderers = (Map<Class<?>, GeoArmorRenderer<?>>) renderersField.get(null);

            // Если поле равно null, создаем новую Map
            if (renderers == null) {
                renderers = new HashMap<>();
                renderersField.set(null, renderers);
            }

            // Добавляем наш рендерер в карту
            renderers.put(item.getClass(), renderer);

            System.out.println("[Origins Winter Fabric] Успешно зарегистрировали рендерер для " + item.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.println("[Origins Winter Fabric] Не удалось зарегистрировать рендерер брони: " + e.getMessage());
            e.printStackTrace();
        }
    }
}