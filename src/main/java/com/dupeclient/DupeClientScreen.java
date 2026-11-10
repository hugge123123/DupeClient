package com.dupeclient;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class DupeClientScreen extends Screen {
    private String status = "Ready";
    public DupeClientScreen() { super(Text.literal("DupeClient")); }

    @Override
    protected void init() {
        int cx = width / 2, top = height / 2 - 70;
        addDrawableChild(ButtonWidget.builder(Text.literal("Singleplayer test"), b -> {
            status = client != null && client.isInSingleplayer()
                    ? "Singleplayer world detected. Use a test copy of your world."
                    : "Open a singleplayer world to run local tests.";
        }).dimensions(cx - 100, top, 200, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Server compatibility"), b -> {
            status = client != null && client.getNetworkHandler() != null
                    ? "Connected. No duplication exploit is run automatically."
                    : "Not connected to a multiplayer server.";
        }).dimensions(cx - 100, top + 28, 200, 20).build());
        addDrawableChild(ButtonWidget.builder(safeModeLabel(), b -> {
            DupeClient.toggleSafeMode();
            b.setMessage(safeModeLabel());
            status = DupeClient.isSafeMode()
                    ? "Safe Mode ON: experimental actions are disabled."
                    : "Safe Mode OFF: use only permitted features.";
        }).dimensions(cx - 100, top + 56, 200, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Item manager"), b -> {
            if (client != null && client.player != null) {
                var stack = client.player.getMainHandStack();
                status = stack.isEmpty() ? "Hold an item to inspect it."
                        : "Held item: " + stack.getName().getString() + " x" + stack.getCount();
            } else status = "Join a world first.";
        }).dimensions(cx - 100, top + 84, 200, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> close())
                .dimensions(cx - 100, top + 112, 200, 20).build());
    }

    private Text safeModeLabel() {
        return Text.literal("Safe Mode: " + (DupeClient.isSafeMode() ? "ON" : "OFF"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        int cx = width / 2;
        context.fill(cx - 120, height / 2 - 112, cx + 120, height / 2 + 124, 0xEE171A22);
        context.drawCenteredTextWithShadow(textRenderer,
                Text.literal("DupeClient").formatted(Formatting.WHITE, Formatting.BOLD),
                cx, height / 2 - 103, 0xFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer,
                Text.literal("Fabric 1.21.11 • Right Shift • Safe Mode " +
                        (DupeClient.isSafeMode() ? "ON" : "OFF")).formatted(Formatting.GRAY),
                cx, height / 2 - 90, 0xAAAAAA);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal(status).formatted(Formatting.GRAY),
                cx, height / 2 + 101, 0xCCCCCC);
    }
    @Override public boolean shouldPause() { return false; }
}
