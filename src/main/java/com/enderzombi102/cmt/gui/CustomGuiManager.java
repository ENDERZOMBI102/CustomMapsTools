package com.enderzombi102.cmt.gui;

import com.enderzombi102.cmt.network.NetworkingConstants;
import io.px.mcgui.mcui.DocumentManager;
import io.px.mcgui.mcui.elements.UIView;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class CustomGuiManager {

	public static void openScreen(ServerPlayerEntity player, String id) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeString( id );
		ServerPlayNetworking.send( player, NetworkingConstants.CUSTOM_GUI_PACKET_ID, buf );
	}

	@Environment(EnvType.CLIENT)
	public static void onOpenScreen(MinecraftClient client, ClientPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender sender) {
		Identifier id = buf.readIdentifier();
		client.openScreen( view );
	}
}
