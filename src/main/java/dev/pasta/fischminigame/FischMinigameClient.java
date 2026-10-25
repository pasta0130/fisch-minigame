
package dev.pasta.fischminigame;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FishingRodItem;

public class FischMinigameClient implements ClientModInitializer {
    private static boolean openingMinigame = false;

    @Override
    public void onInitializeClient() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (world.isClientSide()
                    && player.getItemInHand(hand).getItem() instanceof FishingRodItem
                    && Minecraft.getInstance().screen == null
                    && !openingMinigame) {

                openingMinigame = true;
                Minecraft.getInstance().execute(() -> {
                    try {
                        if (Minecraft.getInstance().screen == null) {
                            Minecraft.getInstance().setScreen(
                                new FishingMinigameScreen()
                            );
                        }
                    } finally {
                        openingMinigame = false;
                    }
                });
            }

            return InteractionResult.PASS;
        });
    }
}
