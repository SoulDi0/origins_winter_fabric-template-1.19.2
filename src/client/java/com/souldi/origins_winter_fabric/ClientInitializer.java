package com.souldi.origins_winter_fabric;

import com.souldi.origins_winter_fabric.item.FairyWingsItem;
import com.souldi.origins_winter_fabric.item.FairyWingsRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        try {
            // В GeckoLib 4.2.2 есть статическое поле ARMOR_RENDERERS в GeoArmorRenderer,
            // которое содержит Map<Class<?>, GeoArmorRenderer<?>> с рендерерами
            Field renderersField = GeoArmorRenderer.class.getDeclaredField("ARMOR_RENDERERS");
            renderersField.setAccessible(true);

            Map<Class<?>, GeoArmorRenderer<?>> renderers = (Map<Class<?>, GeoArmorRenderer<?>>) renderersField.get(null);

            // Если карта рендереров не инициализирована, создаем новую
            if (renderers == null) {
                renderers = new HashMap<>();
                renderersField.set(null, renderers);
            }

            // Добавляем наш рендерер для FairyWingsItem
            renderers.put(FairyWingsItem.class, new FairyWingsRenderer());
            System.out.println("[Origins Winter Fabric] Рендерер крыльев феи успешно зарегистрирован!");
        } catch (Exception e) {
            System.err.println("[Origins Winter Fabric] Ошибка регистрации рендерера крыльев феи: " + e.getMessage());
            e.printStackTrace();
        }
    }
}