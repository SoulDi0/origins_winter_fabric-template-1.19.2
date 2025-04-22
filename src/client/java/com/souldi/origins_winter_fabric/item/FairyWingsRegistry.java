package com.souldi.origins_winter_fabric.item;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import software.bernie.geckolib.animatable.client.RenderProvider;

import java.util.function.Consumer;

/**
 * Класс для регистрации рендереров предметов GeckoLib на клиентской стороне
 */
@Environment(EnvType.CLIENT)
public class FairyWingsRegistry {

    /**
     * Регистрирует рендереры для всех предметов
     */
    public static void registerRenderers() {
        // Регистрируем рендерер для крыльев феи
        Origins_Winter_Fabric.FAIRY_WINGS.createRenderer(consumer -> {
            // Приведение типа Object к Consumer<Object>
            ((Consumer<Object>) consumer).accept(new RenderProvider() {
                private final FairyWingsRenderer renderer = new FairyWingsRenderer();

                @Override
                public BuiltinModelItemRenderer getCustomRenderer() {
                    return null;
                }
            });
        });
    }
}