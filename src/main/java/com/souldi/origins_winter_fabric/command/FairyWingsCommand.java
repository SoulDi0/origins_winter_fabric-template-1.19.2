package com.souldi.origins_winter_fabric.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.souldi.origins_winter_fabric.registry.ModItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * /fairywings_show – визуально надевает крылья, не «захламляя» инвентарь.<br>
 * /fairywings_hide – убирает их.
 *
 * • если на груди был предмет, он перекладывается в обычный инвентарь либо
 *   дропается при переполнении;<br>
 * • сами «крылья» при снятии исчезают безвозвратно (не остаётся предмета).
 */
public final class FairyWingsCommand {
    private FairyWingsCommand() {}

    /* =========================  PUBLIC API  ========================= */

    /** вызывать в onInitialize(): FairyWingsCommand.init(); */
    public static void init() {
        CommandRegistrationCallback.EVENT.register(FairyWingsCommand::register);
    }

    /* =======================  REGISTRATION  ========================= */

    private static void register(CommandDispatcher<ServerCommandSource> d, CommandRegistryAccess a,
                                 CommandManager.RegistrationEnvironment env) {

        d.register(CommandManager.literal("fairywings_show")
                .requires(src -> src.hasPermissionLevel(2))
                .executes(FairyWingsCommand::show));

        d.register(CommandManager.literal("fairywings_hide")
                .requires(src -> src.hasPermissionLevel(2))
                .executes(FairyWingsCommand::hide));
    }

    /* =======================  COMMAND LOGIC  ======================== */

    /** /fairywings_show */
    private static int show(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();

        // Сохраняем/перекладываем то, что было надето
        ItemStack prev = player.getEquippedStack(EquipmentSlot.CHEST);
        if (!prev.isEmpty()) {
            if (!player.getInventory().insertStack(prev))
                player.dropItem(prev, false);
        }

        // Создаём «виртуальные» крылья – метим NBT для последующего удаления
        ItemStack wings = new ItemStack(ModItems.FAIRY_CHESTPLATE);
        wings.getOrCreateNbt().putBoolean("origins_winter_fabric:temp_wings", true);
        player.equipStack(EquipmentSlot.CHEST, wings);

        player.sendMessage(Text.literal("§d✨ Крылья феи расправлены!"), false);
        return 1;
    }

    /** /fairywings_hide */
    private static int hide(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerPlayerEntity player = ctx.getSource().getPlayerOrThrow();

        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        // снимаем только те «крылья», что были надеты командой
        if (!chest.isEmpty() && chest.getNbt() != null &&
                chest.getNbt().getBoolean("origins_winter_fabric:temp_wings")) {

            player.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
            player.sendMessage(Text.literal("§7Крылья убраны."), false);
            return 1;
        }

        player.sendMessage(Text.literal("§cНа вас нет временного комплекта крыльев."), false);
        return 0;
    }
}
