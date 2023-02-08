package com.enderzombi102.cmt.keybind.server

import net.minecraft.server.network.ServerPlayerEntity
import java.util.function.Consumer

class ServerKeybind(var callback: Consumer<ServerPlayerEntity>) {
	fun onPressed(player: ServerPlayerEntity) {
		callback.accept(player)
	}
}
