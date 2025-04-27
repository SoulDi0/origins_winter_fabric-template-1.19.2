package com.souldi.origins_winter_fabric.event;

import com.souldi.origins_winter_fabric.Origins_Winter_Fabric;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;

import java.util.Arrays;
import java.util.List;

/**
 * Обработчик удаления крыльев феи при смене происхождения
 */
public class WingsRemovalHandler {

    // ID орба происхождений
    private static final Identifier ORB_ID = new Identifier("origins", "orb_of_origin");

    // ID всех типов крыльев феи для удаления
    private static final List<Identifier> FAIRY_WINGS_IDS = Arrays.asList(
            new Identifier("origins_winter_fabric", "fairy_chestplate"),
            new Identifier("origins_winter_fabric", "fire_fairy_chestplate"),
            new Identifier("origins_winter_fabric", "winter_fairy_chestplate")
    );

    /**
     * Регистрация обработчика событий
     */
    public static void register() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack stack = player.getStackInHand(hand);

            // Проверяем, используется ли Orb of Origins
            if (Registries.ITEM.getId(stack.getItem()).equals(ORB_ID) && !world.isClient()) {
                final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

                // Используем простой таймер с отдельным потоком
                new Thread(() -> {
                    try {
                        // Ждем 2 секунды
                        Thread.sleep(2000);
                        // Выполняем на основном потоке сервера
                        serverPlayer.getServer().execute(() -> {
                            removeWings(serverPlayer);
                        });
                    } catch (Exception e) {
                        Origins_Winter_Fabric.LOG.error("Error in wings removal thread: " + e.getMessage());
                    }
                }).start();
            }

            // Возвращаем управление оригинальному обработчику
            return TypedActionResult.pass(stack);
        });

        Origins_Winter_Fabric.LOG.info("Wings removal handler registered successfully");
    }

    /**
     * Удаляет крылья феи у игрока
     */
    private static void removeWings(ServerPlayerEntity player) {
        try {
            // Удаляем крылья из слота брони
            ItemStack chestItem = player.getEquippedStack(EquipmentSlot.CHEST);
            Identifier chestItemId = Registries.ITEM.getId(chestItem.getItem());

            if (FAIRY_WINGS_IDS.contains(chestItemId)) {
                player.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
                Origins_Winter_Fabric.LOG.info("Removed fairy wings from chest slot: " + player.getName().getString());
            }

            // Удаляем крылья феи из всего инвентаря
            int removedCount = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                ItemStack invStack = player.getInventory().getStack(i);
                if (!invStack.isEmpty()) {
                    Identifier itemId = Registries.ITEM.getId(invStack.getItem());
                    if (FAIRY_WINGS_IDS.contains(itemId)) {
                        player.getInventory().setStack(i, ItemStack.EMPTY);
                        removedCount++;
                    }
                }
            }

            if (removedCount > 0) {
                Origins_Winter_Fabric.LOG.info("Removed " + removedCount + " fairy wing items from inventory for player: " + player.getName().getString());
            }

            Origins_Winter_Fabric.LOG.info("Wings removal completed for player: " + player.getName().getString());
        } catch (Exception e) {
            Origins_Winter_Fabric.LOG.error("Failed to clear fairy wings: " + e.getMessage());
        }
    }
}