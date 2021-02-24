package com.enderzombi102.cmt.keybind.server;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Consumer;

public class ServerKeybind {

	public Consumer<ServerPlayerEntity> callback;

	public ServerKeybind(Consumer<ServerPlayerEntity> callback) {
		this.callback = callback;
	}

	public void onPressed(ServerPlayerEntity player) {
		this.callback.accept(player);
	}



}
