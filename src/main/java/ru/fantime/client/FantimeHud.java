package ru.fantime.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class FantimeHud {
    private FantimeHud() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register(FantimeHud::render);
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.options.hudHidden || !ClientState.hudEnabled) return;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int panelColor = 0xA0101420;
        int accentColor = 0xFF55D6BE;
        int textColor = 0xFFF3F7FA;

        context.fill(6, 6, 154, 57, panelColor);
        context.drawTextWithShadow(client.textRenderer, Text.literal("FANTIME").formatted(Formatting.BOLD), 12, 11, accentColor);
        context.drawTextWithShadow(client.textRenderer, Text.literal("FPS " + client.getFps() + "  " + ping(client)), 12, 25, textColor);
        context.drawTextWithShadow(client.textRenderer, coordinates(client), 12, 40, textColor);

        if (ClientState.keystrokesEnabled) renderKeystrokes(context, client, width - 78, height - 72);
        if (ClientState.armorEnabled) renderArmor(context, client, width - 86, height - 112);
    }

    private static String coordinates(Minecraft client) {
        return String.format("XYZ %d %d %d", client.player.getBlockX(), client.player.getBlockY(), client.player.getBlockZ());
    }

    private static String ping(Minecraft client) {
        if (client.getNetworkHandler() == null) return "Ping --";
        PlayerListEntry entry = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid());
        return "Ping " + (entry == null ? "--" : entry.getLatency() + "ms");
    }

    private static void renderKeystrokes(DrawContext context, Minecraft client, int x, int y) {
        drawKey(context, client, "W", x + 24, y, client.options.forwardKey.isPressed());
        drawKey(context, client, "A", x, y + 24, client.options.leftKey.isPressed());
        drawKey(context, client, "S", x + 24, y + 24, client.options.backKey.isPressed());
        drawKey(context, client, "D", x + 48, y + 24, client.options.rightKey.isPressed());
        drawKey(context, client, "SP", x + 12, y + 48, client.options.jumpKey.isPressed());
    }

    private static void drawKey(DrawContext context, Minecraft client, String label, int x, int y, boolean pressed) {
        context.fill(x, y, x + 20, y + 20, pressed ? 0xFF55D6BE : 0xB0202630);
        int color = pressed ? 0xFF081015 : 0xFFFFFFFF;
        context.drawCenteredTextWithShadow(client.textRenderer, Text.literal(label), x + 10, y + 6, color);
    }

    private static void renderArmor(DrawContext context, Minecraft client, int x, int y) {
        context.fill(x - 4, y - 4, x + 80, y + 32, 0xA0101420);
        for (int index = 0; index < 4; index++) {
            ItemStack stack = client.player.getInventory().getArmorStack(index);
            context.drawItem(stack, x + index * 19, y);
            if (!stack.isEmpty()) context.drawItemInSlot(client.textRenderer, stack, x + index * 19, y);
        }
    }
}
