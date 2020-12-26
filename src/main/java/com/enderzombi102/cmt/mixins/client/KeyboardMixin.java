package com.enderzombi102.cmt.mixins.client;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

	@Inject( at = @At(value="HEAD"), method = "onKey" )
	public void onKey(long window, int key, int scancode, int i, int j, CallbackInfo info) {
		MinecraftClient mc = MinecraftClient.getInstance();
		if( window == mc.getWindow().getHandle() ) {
			// cycle in our keys
			for (KeyBindingHelper.KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
				// check if its one of our keys
				if ( bind.getKey() == key && InputUtil.isKeyPressed( mc.getWindow().getHandle(), bind.getKey() ) ) {
					// check requirements
					if ( bind.requiresInGame && !mc.isPaused() ) {
						continue;
					} else if ( bind.requiresInteracting && mc.currentScreen != null ) {
						continue;
					}
					// all good, execute
					bind.getCallback().accept( mc );
				}
			}

		}

	}

}
