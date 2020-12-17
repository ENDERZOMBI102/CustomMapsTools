package com.enderzombi102.cmt.mixins;

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
		if( window == MinecraftClient.getInstance().getWindow().getHandle() ) {
			for (KeyBindingHelper.KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
				if ( bind.getKey() == key && InputUtil.isKeyPressed( MinecraftClient.getInstance().getWindow().getHandle(), bind.getKey() ) ) {
					bind.getCallback().accept( MinecraftClient.getInstance() );
				}
			}

		}

	}

}
