package com.souldi.origins_winter_fabric.effect;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class MinerFortuneEffect {

    // Список блоков, на которые влияет зачарование Fortune
    private static final List<Block> FORTUNE_BLOCKS = new ArrayList<>();

    // Список UUID игроков с активированным эффектом удачи
    private static final Set<UUID> MINERS_WITH_FORTUNE = new HashSet<>();

    static {
        // Инициализация списка блоков
        FORTUNE_BLOCKS.add(Blocks.COAL_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_COAL_ORE);
        FORTUNE_BLOCKS.add(Blocks.IRON_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_IRON_ORE);
        FORTUNE_BLOCKS.add(Blocks.COPPER_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_COPPER_ORE);
        FORTUNE_BLOCKS.add(Blocks.GOLD_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_GOLD_ORE);
        FORTUNE_BLOCKS.add(Blocks.DIAMOND_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_DIAMOND_ORE);
        FORTUNE_BLOCKS.add(Blocks.EMERALD_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_EMERALD_ORE);
        FORTUNE_BLOCKS.add(Blocks.LAPIS_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_LAPIS_ORE);
        FORTUNE_BLOCKS.add(Blocks.REDSTONE_ORE);
        FORTUNE_BLOCKS.add(Blocks.DEEPSLATE_REDSTONE_ORE);
        FORTUNE_BLOCKS.add(Blocks.NETHER_QUARTZ_ORE);
        FORTUNE_BLOCKS.add(Blocks.NETHER_GOLD_ORE);
        FORTUNE_BLOCKS.add(Blocks.GILDED_BLACKSTONE);
        FORTUNE_BLOCKS.add(Blocks.AMETHYST_CLUSTER);
    }

    private MinerFortuneEffect() {}

    /**
     * Регистрирует обработчики событий для эффекта удачи шахтера.
     */
    public static void register() {
        Origins_Winter_Fabric.LOG.info("Registering Miner Fortune Effect");

        // Регистрируем обработчик события разрушения блока
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient) return true; // Пропускаем на клиенте

            // Проверяем, является ли игрок шахтером и подходит ли блок для применения удачи
            if (hasMinerFortune(player) && FORTUNE_BLOCKS.contains(state.getBlock())) {
                applyFortuneEffect(world, player, pos, state);
            }

            return true; // Позволяем разрушение блока
        });

        // Регистрируем команды для управления эффектом удачи
        CommandRegistrationCallback.EVENT.register(MinerFortuneEffect::registerCommands);

        Origins_Winter_Fabric.LOG.info("Miner Fortune Effect registered successfully");
    }

    /**
     * Регистрирует команды для управления эффектом удачи шахтера.
     */
    private static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
                                         CommandRegistryAccess registryAccess,
                                         CommandManager.RegistrationEnvironment environment) {

        // Команда для активации эффекта удачи для игрока
        dispatcher.register(CommandManager.literal("miner_fortune")
                .requires(source -> source.hasPermissionLevel(2)) // Только для операторов
                .then(CommandManager.literal("enable")
                        .executes(MinerFortuneEffect::enableMinerFortune))
                .then(CommandManager.literal("disable")
                        .executes(MinerFortuneEffect::disableMinerFortune))
        );
    }

    /**
     * Активирует эффект удачи для игрока.
     */
    private static int enableMinerFortune(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
            MINERS_WITH_FORTUNE.add(player.getUuid());
            player.sendMessage(Text.literal("§a✨ Удача шахтера активирована!"), false);
            Origins_Winter_Fabric.LOG.info("Miner Fortune enabled for player: " + player.getName().getString());
            return 1;
        } catch (Exception e) {
            Origins_Winter_Fabric.LOG.error("Error enabling miner fortune: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Деактивирует эффект удачи для игрока.
     */
    private static int disableMinerFortune(CommandContext<ServerCommandSource> context) {
        try {
            ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
            MINERS_WITH_FORTUNE.remove(player.getUuid());
            player.sendMessage(Text.literal("§7Удача шахтера деактивирована."), false);
            Origins_Winter_Fabric.LOG.info("Miner Fortune disabled for player: " + player.getName().getString());
            return 1;
        } catch (Exception e) {
            Origins_Winter_Fabric.LOG.error("Error disabling miner fortune: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Проверяет, активирован ли для игрока эффект удачи шахтера.
     */
    private static boolean hasMinerFortune(PlayerEntity player) {
        return player != null && MINERS_WITH_FORTUNE.contains(player.getUuid());
    }

    /**
     * Применяет эффект зачарования "Удача I" к разрушаемому блоку.
     */
    private static void applyFortuneEffect(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        if (!(world instanceof ServerWorld)) return;

        // Получаем текущий инструмент в руке игрока
        ItemStack heldItem = player.getMainHandStack();

        // Проверяем, есть ли у инструмента уже зачарование "Удача"
        boolean alreadyHasFortune = false;
        int existingFortuneLevel = 0;

        if (heldItem.hasEnchantments()) {
            NbtList enchantments = heldItem.getEnchantments();
            for (int i = 0; i < enchantments.size(); i++) {
                NbtCompound enchantment = enchantments.getCompound(i);
                String id = enchantment.getString("id");
                if (id.contains("fortune")) {
                    alreadyHasFortune = true;
                    existingFortuneLevel = enchantment.getShort("lvl");
                    break;
                }
            }
        }

        // Если инструмент уже имеет Удачу более высокого уровня, не трогаем его
        if (alreadyHasFortune && existingFortuneLevel >= 1) {
            return;
        }

        // Сохраняем оригинальное состояние предмета
        NbtCompound originalNbt = null;
        if (heldItem.hasNbt()) {
            originalNbt = heldItem.getNbt().copy();
        }

        // Временно добавляем зачарование "Удача I"
        try {
            heldItem.addEnchantment(Enchantments.FORTUNE, 1);

            // Оригинальное разрушение блока будет происходить в момент события
            // Minecraft сам обработает выпадение предметов с учетом зачарования

        } finally {
            // Восстанавливаем оригинальное состояние предмета после разрушения блока
            if (originalNbt != null) {
                heldItem.setNbt(originalNbt);
            } else if (!alreadyHasFortune) {
                // Если не было нбт и не было Fortune, удаляем добавленное зачарование
                removeFortuneEnchantment(heldItem);
            }
        }
    }

    /**
     * Удаляет зачарование "Удача" с предмета.
     */
    private static void removeFortuneEnchantment(ItemStack itemStack) {
        if (itemStack.hasEnchantments()) {
            NbtCompound nbt = itemStack.getNbt();
            if (nbt != null && nbt.contains("Enchantments", 9)) {
                NbtCompound newNbt = nbt.copy();
                newNbt.remove("Enchantments");
                itemStack.setNbt(newNbt);
            }
        }
    }
}