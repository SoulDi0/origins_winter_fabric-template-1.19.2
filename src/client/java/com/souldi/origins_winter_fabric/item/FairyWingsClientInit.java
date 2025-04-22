package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import software.bernie.geckolib.animatable.client.RenderProvider;

import java.util.function.Consumer;

/**
 * Регистрация рендереров GeckoLib на стороне клиента
 */
@Environment(EnvType.CLIENT)
public class FairyWingsClientInit {

    /**
     * Инициализирует клиентскую часть мода, регистрируя все рендереры
     */
    public static void init() {
        // Создаем рендерер
        FairyWingsRenderer renderer = new FairyWingsRenderer();

        // Стандартный способ - через Consumer API
        try {
            Origins_Winter_Fabric.FAIRY_WINGS.createRenderer(consumer -> {
                ((Consumer<Object>) consumer).accept(new RenderProvider() {
                    @Override
                    public BuiltinModelItemRenderer getCustomRenderer() {
                        // Для брони этот метод обычно возвращает null
                        return null;
                    }
                });
            });
            System.out.println("[Origins Winter Fabric] Рендерер крыльев феи зарегистрирован");
        } catch (Exception e) {
            System.err.println("[Origins Winter Fabric] Ошибка при регистрации рендерера: " + e.getMessage());
        }

        // Резервный способ - напрямую через рефлексию
        try {
            ArmorRendererRegistrationHandler.registerArmorRenderer(
                    Origins_Winter_Fabric.FAIRY_WINGS,
                    renderer
            );
            System.out.println("[Origins Winter Fabric] Рендерер крыльев феи зарегистрирован (запасной метод)");
        } catch (Exception e) {
            System.err.println("[Origins Winter Fabric] Ошибка при запасной регистрации: " + e.getMessage());
        }
    }
}