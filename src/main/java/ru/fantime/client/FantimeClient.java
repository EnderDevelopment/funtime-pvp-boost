package ru.fantime.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class FantimeClient implements ClientModInitializer {
    private static KeyBinding toggleHud;
    private static KeyBinding toggleKeystrokes;
    private static KeyBinding toggleArmor;
    private static KeyBinding toggleSprint;
    private static KeyBinding toggleFullbright;
    private static double savedGamma = 1.0;

    @Override
    public void onInitializeClient() {
        toggleHud = register("Toggle HUD", GLFW.GLFW_KEY_H);
        toggleKeystrokes = register("Toggle Keystrokes", GLFW.GLFW_KEY_K);
        toggleArmor = register("Toggle Armor HUD", GLFW.GLFW_KEY_J);
        toggleSprint = register("Toggle Auto Sprint", GLFW.GLFW_KEY_R);
        toggleFullbright = register("Toggle Fullbright", GLFW.GLFW_KEY_B);

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
        FantimeHud.register();
    }

    private KeyBinding register(String name, int keyCode) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fantime_client." + name.toLowerCase().replace(' ', '_'),
                InputUtil.Type.KEYSYM,
                keyCode,
                "category.fantime_client"
        ));
    }

    private void tick(Minecraft client) {
        handleToggles(client);
        if (client.player != null && ClientState.autoSprintEnabled && client.player.input.hasForwardMovement()) {
            client.player.setSprinting(!client.player.isSneaking() && client.player.getHungerManager().getFoodLevel() > 6);
        }
    }

    private void handleToggles(Minecraft client) {
        while (toggleHud.wasPressed()) ClientState.hudEnabled = !ClientState.hudEnabled;
        while (toggleKeystrokes.wasPressed()) ClientState.keystrokesEnabled = !ClientState.keystrokesEnabled;
        while (toggleArmor.wasPressed()) ClientState.armorEnabled = !ClientState.armorEnabled;
        while (toggleSprint.wasPressed()) ClientState.autoSprintEnabled = !ClientState.autoSprintEnabled;
        while (toggleFullbright.wasPressed()) toggleFullbright(client);
    }

    private void toggleFullbright(Minecraft client) {
        ClientState.fullbrightEnabled = !ClientState.fullbrightEnabled;
        if (ClientState.fullbrightEnabled) {
            savedGamma = client.options.getGamma().getValue();
            client.options.getGamma().setValue(16.0);
        } else {
            client.options.getGamma().setValue(savedGamma);
        }
    }
}
