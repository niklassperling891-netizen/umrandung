package de.spielerumriss;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.glfw.GLFW;

public final class SpielerUmrissClient implements ClientModInitializer {
    private static final Map<UUID, Boolean> ORIGINAL_GLOWING = new HashMap<>();
    private static final KeyMapping TOGGLE_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.spielerumriss.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            KeyMapping.Category.MISC
    ));

    private static boolean enabled;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(SpielerUmrissClient::onClientTick);
    }

    private static void onClientTick(Minecraft client) {
        while (TOGGLE_KEY.consumeClick()) {
            enabled = !enabled;
            if (!enabled) {
                restorePlayers(client);
            }
            if (client.player != null) {
                client.player.displayClientMessage(Component.translatable(
                        enabled ? "message.spielerumriss.enabled" : "message.spielerumriss.disabled"
                ), true);
            }
        }

        if (client.level == null || client.player == null) {
            ORIGINAL_GLOWING.clear();
            return;
        }

        if (enabled) {
            for (Player player : client.level.players()) {
                if (player == client.player) {
                    continue;
                }
                ORIGINAL_GLOWING.putIfAbsent(player.getUUID(), player.isCurrentlyGlowing());
                player.setGlowingTag(true);
            }
        }
    }

    private static void restorePlayers(Minecraft client) {
        if (client.level != null) {
            for (Player player : client.level.players()) {
                Boolean wasGlowing = ORIGINAL_GLOWING.get(player.getUUID());
                if (wasGlowing != null) {
                    player.setGlowingTag(wasGlowing);
                }
            }
        }
        ORIGINAL_GLOWING.clear();
    }
}
