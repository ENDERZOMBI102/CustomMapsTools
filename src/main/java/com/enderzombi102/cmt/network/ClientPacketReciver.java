package com.enderzombi102.cmt.network;

import com.enderzombi102.cmt.network.packets.PacketIdentifiers;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class ClientPacketReciver implements ClientPlayNetworking.PlayChannelHandler {

	public static final ClientPacketReciver INSTANCE = new ClientPacketReciver();

	public static void register() {
		ClientPlayNetworking.registerGlobalReceiver(PacketIdentifiers.WINDOW_DATA, INSTANCE);
	}

	@Override
	public void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {

	}
}
