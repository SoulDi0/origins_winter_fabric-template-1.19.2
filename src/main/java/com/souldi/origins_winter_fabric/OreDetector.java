package com.souldi.origins_winter_fabric;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Простой класс для обнаружения и подсветки руд
 */
public class OreDetector {
    // Карта блоков руд и соответствующих им цветов
    private static final Map<Block, Formatting> ORE_COLORS = new HashMap<>();

    // Храним список созданных маркеров для каждого игрока
    private static final Map<String, List<Entity>> PLAYER_MARKERS = new ConcurrentHashMap<>();

    static {
        // Заполняем карту всеми типами руд и их цветами
        ORE_COLORS.put(Blocks.IRON_ORE, Formatting.GRAY);
        ORE_COLORS.put(Blocks.DEEPSLATE_IRON_ORE, Formatting.GRAY);
        ORE_COLORS.put(Blocks.GOLD_ORE, Formatting.GOLD);
        ORE_COLORS.put(Blocks.DEEPSLATE_GOLD_ORE, Formatting.GOLD);
        ORE_COLORS.put(Blocks.DIAMOND_ORE, Formatting.AQUA);
        ORE_COLORS.put(Blocks.DEEPSLATE_DIAMOND_ORE, Formatting.AQUA);
        ORE_COLORS.put(Blocks.LAPIS_ORE, Formatting.BLUE);
        ORE_COLORS.put(Blocks.DEEPSLATE_LAPIS_ORE, Formatting.BLUE);
        ORE_COLORS.put(Blocks.REDSTONE_ORE, Formatting.RED);
        ORE_COLORS.put(Blocks.DEEPSLATE_REDSTONE_ORE, Formatting.RED);
        ORE_COLORS.put(Blocks.EMERALD_ORE, Formatting.GREEN);
        ORE_COLORS.put(Blocks.DEEPSLATE_EMERALD_ORE, Formatting.GREEN);
        ORE_COLORS.put(Blocks.COPPER_ORE, Formatting.GOLD);
        ORE_COLORS.put(Blocks.DEEPSLATE_COPPER_ORE, Formatting.GOLD);
        ORE_COLORS.put(Blocks.COAL_ORE, Formatting.DARK_GRAY);
        ORE_COLORS.put(Blocks.DEEPSLATE_COAL_ORE, Formatting.DARK_GRAY);
        ORE_COLORS.put(Blocks.NETHER_GOLD_ORE, Formatting.GOLD);
        ORE_COLORS.put(Blocks.NETHER_QUARTZ_ORE, Formatting.WHITE);
        ORE_COLORS.put(Blocks.ANCIENT_DEBRIS, Formatting.DARK_PURPLE);
    }

    /**
     * Ищет и подсвечивает руды вокруг игрока
     *
     * @param player Игрок, вокруг которого искать руды
     * @param radius Радиус поиска в блоках
     * @param duration Длительность подсветки в тиках (игнорируется, используется только для совместимости)
     */
    public static boolean findAndHighlightOres(PlayerEntity player, int radius, int duration) {
        if (!(player.getWorld() instanceof ServerWorld serverWorld)) return false;

        System.out.println("Активирована способность обнаружения руд для игрока: " + player.getName().getString());

        // Удаляем предыдущие маркеры для этого игрока
        removeMarkersForPlayer(player);

        // Создаем список для хранения новых маркеров
        List<Entity> markers = new ArrayList<>();

        // Находим и отмечаем руды
        BlockPos playerPos = player.getBlockPos();
        int oreCount = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = playerPos.add(x, y, z);
                    Block block = serverWorld.getBlockState(pos).getBlock();

                    // Если блок является рудой, создаем маркер
                    if (ORE_COLORS.containsKey(block)) {
                        Entity marker = createOreMarker(serverWorld, pos, block);
                        if (marker != null) {
                            markers.add(marker);
                            oreCount++;
                        }
                    }
                }
            }
        }

        // Сохраняем ссылки на созданные маркеры
        PLAYER_MARKERS.put(player.getUuidAsString(), markers);

        // Отправляем сообщение игроку
        player.sendMessage(
                Text.literal("Обнаружено руд: " + oreCount + " в радиусе " + radius + " блоков"),
                true
        );

        // Запланируем удаление маркеров через главный поток сервера
        serverWorld.getServer().execute(() -> {
            try {
                // Задержка с учетом продолжительности в тиках (1 тик = 50 мс)
                Thread.sleep(10000); // 10 секунд - фиксированное время

                // Проверяем, существует ли еще игрок и не отключен ли сервер
                if (!serverWorld.getServer().isStopped()) {
                    removeMarkersForPlayer(player);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при удалении маркеров руд: " + e.getMessage());
            }
        });

        return true;
    }

    /**
     * Удаляет все маркеры, созданные для указанного игрока
     */
    private static void removeMarkersForPlayer(PlayerEntity player) {
        String playerId = player.getUuidAsString();

        if (PLAYER_MARKERS.containsKey(playerId)) {
            List<Entity> markers = PLAYER_MARKERS.get(playerId);

            for (Entity marker : markers) {
                if (marker != null && marker.isAlive()) {
                    marker.kill();
                }
            }

            PLAYER_MARKERS.remove(playerId);
        }
    }

    /**
     * Создает маркер над блоком руды
     * @return созданный маркер или null, если не удалось создать
     */
    private static Entity createOreMarker(World world, BlockPos pos, Block oreBlock) {
        try {
            Formatting color = ORE_COLORS.getOrDefault(oreBlock, Formatting.WHITE);
            String oreName = getOreName(oreBlock);

            ArmorStandEntity marker = new ArmorStandEntity(
                    world,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5
            );

            // Делаем стойку полностью невидимой
            marker.setInvisible(true);
            marker.setNoGravity(true);
            // Отключаем подсветку самой стойки
            marker.setGlowing(false);

            // Устанавливаем яркий текст с форматированием для лучшей видимости
            // Добавляем форматирование BOLD для выделения
            Style textStyle = Style.EMPTY
                    .withColor(color)
                    .withBold(true);  // Жирный текст

            marker.setCustomName(Text.literal(oreName).setStyle(textStyle));
            marker.setCustomNameVisible(true);

            if (world.spawnEntity(marker)) {
                return marker;
            }
        } catch (Exception e) {
            System.err.println("Ошибка при создании маркера руды: " + e.getMessage());
        }

        return null;
    }

    /**
     * Определяет название руды
     */
    private static String getOreName(Block block) {
        if (block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE) return "Железная руда";
        if (block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE || block == Blocks.NETHER_GOLD_ORE) return "Золотая руда";
        if (block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE) return "Алмазная руда";
        if (block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE) return "Изумрудная руда";
        if (block == Blocks.LAPIS_ORE || block == Blocks.DEEPSLATE_LAPIS_ORE) return "Лазуритовая руда";
        if (block == Blocks.REDSTONE_ORE || block == Blocks.DEEPSLATE_REDSTONE_ORE) return "Редстоуновая руда";
        if (block == Blocks.COPPER_ORE || block == Blocks.DEEPSLATE_COPPER_ORE) return "Медная руда";
        if (block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE) return "Угольная руда";
        if (block == Blocks.NETHER_QUARTZ_ORE) return "Кварцевая руда";
        if (block == Blocks.ANCIENT_DEBRIS) return "Древние обломки";

        return "Руда";
    }
}