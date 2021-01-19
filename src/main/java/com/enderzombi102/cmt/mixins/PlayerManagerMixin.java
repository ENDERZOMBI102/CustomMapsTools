package com.enderzombi102.cmt.mixins;

import net.minecraft.network.MessageType;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;
import java.util.UUID;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {

	@Shadow
	@Final
	private MinecraftServer server;

	@Shadow public abstract void sendToAll(Packet<?> packet);

	@Shadow @Nullable public abstract ServerPlayerEntity getPlayer(UUID uuid);

	@Inject( method = "broadcastChatMessage", at = @At(value = "INVOKE") )
	public void onBroadcastChatMessage(Text message, MessageType type, UUID senderUuid, CallbackInfo info) {
		if (! (message instanceof TranslatableText) ) return;
		String key = ( (TranslatableText) message ).getKey();
		if ( key.equals("multiplayer.player.joined") || key.equals("multiplayer.player.joined.renamed") ) {
//			info.cancel();
		} else if ( key.equals("multiplayer.player.left") ) {
//			info.cancel();
		}
		this.server.sendSystemMessage(message, senderUuid);
		this.sendToAll( new GameMessageS2CPacket(message, type, senderUuid) );
		LOGGER.info("{} logged in!", this.getPlayer(senderUuid).getName() );
	}

}