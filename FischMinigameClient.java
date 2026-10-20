package dev.pasta.fischminigame;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FishingRodItem;

public class FischMinigameClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Prototype: using a fishing rod opens the minigame immediately.
        // Later we can replace this with real bobber/bite detection.
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (world.isClient()
                    && player.getItemInHand(hand).getItem() instanceof FishingRodItem
                    && Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new FishingMinigameScreen());
            }
            return InteractionResult.PASS;
        });
    }
}
