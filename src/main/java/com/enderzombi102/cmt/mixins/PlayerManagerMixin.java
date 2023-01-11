package com.enderzombi102.cmt.mixins;

import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

	@ModifyArg( method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/PlayerManager;broadcastSystemMessage(Lnet/minecraft/text/Text;Z)V"), index = 0)
	public Text onPlayerConnect( Text text ) {
		LOGGER.info( "[CMT] " + text.getString() );
		return Text.literal("hello world");
	}

}
