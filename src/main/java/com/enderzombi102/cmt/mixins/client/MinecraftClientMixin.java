package com.enderzombi102.cmt.mixins.client;

import com.enderzombi102.cmt.client.Window;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	@Inject(method = "getWindowTitle", at = @At("INVOKE"), cancellable = true )
	public void onGetWindowTitle(CallbackInfoReturnable<String> info) {
		info.setReturnValue( Window.getTitle() );
	}


}
