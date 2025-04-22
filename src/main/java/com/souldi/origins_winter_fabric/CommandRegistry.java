package com.souldi.origins_winter_fabric;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

/**
 * Регистрирует команды мода
 */
public class CommandRegistry {
    /**
     * Инициализирует и регистрирует команды
     */
    public static void init() {
        CommandRegistrationCallback.EVENT.register(CommandRegistry::registerCommands);
        System.out.println("[Origins Winter Fabric] Команды зарегистрированы");
    }

    /**
     * Регистрирует все команды мода
     */
    private static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
                                         CommandRegistryAccess registry,
                                         CommandManager.RegistrationEnvironment environment) {

        // Регистрируем прямую команду для тестирования
        dispatcher.register(
                CommandManager.literal("ore_detect")
                        .requires(source -> source.hasPermissionLevel(2)) // Требуем права оператора (2)
                        .executes(context -> executeOreDetect(context, 10, 200))
                        .then(CommandManager.argument("radius", IntegerArgumentType.integer(1, 20))
                                .executes(context -> {
                                    int radius = IntegerArgumentType.getInteger(context, "radius");
                                    return executeOreDetect(context, radius, 200);
                                })
                        )
        );

        // Регистрируем команду для вызова через датапак
        dispatcher.register(
                CommandManager.literal("function")
                        .then(CommandManager.literal("origins_winter_fabric:call_ore_detector")
                                .executes(context -> {
                                    ServerCommandSource source = context.getSource();
                                    if (source.getPlayer() != null) {
                                        System.out.println("[Origins Winter Fabric] Вызвана функция call_ore_detector");
                                        return executeOreDetect(context, 10, 200);
                                    } else {
                                        source.sendError(Text.literal("Эту команду может использовать только игрок"));
                                        return 0;
                                    }
                                })
                        )
        );
    }

    /**
     * Выполняет обнаружение руд
     */
    private static int executeOreDetect(CommandContext<ServerCommandSource> context, int radius, int duration) {
        ServerCommandSource source = context.getSource();
        if (source.getPlayer() != null) {
            System.out.println("[Origins Winter Fabric] Выполняется обнаружение руд, радиус: " + radius);

            // Вызываем детектор руд
            OreDetector.findAndHighlightOres(source.getPlayer(), radius, duration);

            source.sendFeedback(() -> Text.literal("Обнаружение руд активировано, радиус: " + radius), false);
            return 1;
        } else {
            source.sendError(Text.literal("Эту команду может использовать только игрок"));
            return 0;
        }
    }
}
