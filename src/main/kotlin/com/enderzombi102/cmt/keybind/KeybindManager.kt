package com.enderzombi102.cmt.keybind

import com.enderzombi102.cmt.keybind.client.Key
import com.enderzombi102.cmt.keybind.client.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import java.util.function.Consumer

object KeybindManager {
	fun createBind(key: String, category: String, name: String, executes: String?) {
		val consumer: Consumer<MinecraftClient> = object : Consumer<MinecraftClient> {
			val executor = null // Executor( null, executes!! )
			override fun accept(minecraftClient: MinecraftClient) {
//				executor.execute(minecraftClient.player!!)
			}
		}
		KeyBindingHelper.makeKeybind(
			Key.findKey(key)!!,
			consumer,
			name,
			category,
			true,
			true
		)
	}

	fun clearKeybinds() {
		KeyBindingHelper.keyCallbacks.clear()
	}
}
