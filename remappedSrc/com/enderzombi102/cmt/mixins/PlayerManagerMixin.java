package com.enderzombi102.cmt.mixins;

import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.enderzombi102.cmt.CustomMapsTools.logger;
import java.util.UUID;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

	@Shadow @Final private MinecraftServer server;

	@Inject( method = "broadcastChatMessage", at = @At(value = "INVOKE")  )
	public void onBroadcastChatMessage(Text message, MessageType type, UUID senderUuid, CallbackInfo info) {
		if (! (message instanceof TranslatableText) ) return;
		TranslatableText tranText = (TranslatableText) message;
		String key = tranText.getKey();
		if ( key.equals("multiplayer.player.joined") || key.equals("multiplayer.player.joined.renamed") ) {
			logger.info("somebody joined");
		} else if ( key.equals("multiplayer.player.left") ) {
			logger.info("somebody left");
//			message = new LiteralText( this.server.getGameRules().get().toString() );
		}

	}

}