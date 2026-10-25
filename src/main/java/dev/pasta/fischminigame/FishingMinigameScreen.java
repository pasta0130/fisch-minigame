package dev.pasta.fischminigame;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class FishingMinigameScreen extends Screen {
    private double marker = 0.0;
    private double direction = 1.0;
    private double progress = 0.0;
    private int ticks = 0;
    private String result = "";
    private int resultTicks = 0;

    private static final double TARGET_MIN = 0.42;
    private static final double TARGET_MAX = 0.62;

    public FishingMinigameScreen() {
        super(Component.literal("Fishing Minigame"));
    }

    @Override
    public void tick() {
        super.tick();
        if (!result.isEmpty()) {
            resultTicks++;
            if (resultTicks > 45) {
                onClose();
            }
            return;
        }

        ticks++;
        marker += direction * 0.012;
        if (marker >= 1.0) {
            marker = 1.0;
            direction = -1.0;
        } else if (marker <= 0.0) {
            marker = 0.0;
            direction = 1.0;
        }

        // Passive progress decay makes timing and repeated hits matter.
        progress = Math.max(0.0, progress - 0.0015);
        if (ticks > 60 * 30) {
            result = "The fish got away!";
            resultTicks = 0;
        }
    }

    @Override
    public boolean keyPressed(net.minecraft.client.input.KeyEvent event) {
        int keyCode = event.key();
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            onClose();
            return true;
        }

        if (keyCode == GLFW.GLFW_KEY_SPACE && result.isEmpty()) {
            if (marker >= TARGET_MIN && marker <= TARGET_MAX) {
                progress += 0.22;
                if (progress >= 1.0) {
                    progress = 1.0;
                    result = "Fish caught! (prototype)";
                    resultTicks = 0;
                }
            } else {
                progress = Math.max(0.0, progress - 0.12);
            }
            return true;
        }

        return super.keyPressed(event);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
    graphics.fill(0, 0, this.width, this.height, 0x88000000);

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int barWidth = Math.min(280, this.width - 40);
        int barHeight = 18;
        int left = centerX - barWidth / 2;
        int top = centerY - 10;

        graphics.drawCenteredString(this.font, "FISCH-STYLE FISHING", centerX, top - 55, 0xFFFFFF);
        graphics.drawCenteredString(this.font, "Press SPACE when the marker is in the green zone", centerX, top - 35, 0xD8D8D8);

        graphics.fill(left, top, left + barWidth, top + barHeight, 0xFF252525);

        int targetLeft = left + (int) (barWidth * TARGET_MIN);
        int targetRight = left + (int) (barWidth * TARGET_MAX);
        graphics.fill(targetLeft, top, targetRight, top + barHeight, 0xFF36B85A);

        int markerX = left + (int) (barWidth * marker);
        graphics.fill(markerX - 2, top - 5, markerX + 2, top + barHeight + 5, 0xFFFFFFFF);

    
         int progressWidth = (int) (barWidth * progress);
        graphics.fill(left, top + 30, left + progressWidth, top + 38, 0xFF42BDE8);
        graphics.drawString(this.font, "Catch progress: " + (int) (progress * 100) + "%", left, top + 43, 0xFFFFFF);

        if (!result.isEmpty()) {
            graphics.fill(centerX - 120, centerY + 65, centerX + 120, centerY + 100, 0xCC111111);
            graphics.drawCenteredString(this.font, result, centerX, centerY + 77, 0xFFFFFF);
        }

        graphics.drawCenteredString(this.font, "ESC: close", centerX, this.height - 25, 0xAAAAAA);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
