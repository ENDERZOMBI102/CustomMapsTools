package com.enderzombi102.cmt.keybind.client

import net.minecraft.client.MinecraftClient
import java.util.function.Consumer

object KeyBindingHelper {
	@JvmField
	val keyCallbacks = ArrayList<KeyBinding>()
	val defaultCategories = ArrayList<String>()
	fun makeKeybind(
		key: Key,
		callback: Consumer<MinecraftClient>,
		transText: String,
		category: String,
		requiresInGame: Boolean,
		requiresInteracting: Boolean
	): KeyBinding {
		val bind = KeyBinding(key, callback, transText, category, requiresInGame, requiresInteracting)
		keyCallbacks.add(bind)
		return bind
	}

	fun removeKeybind(keyBinding: KeyBinding?): Boolean {
		return keyCallbacks.remove(keyBinding)
	}

	fun getKeybind(func: Consumer<MinecraftClient?>): KeyBinding? {
		for (bind in keyCallbacks) {
			if (bind.callback === func) return bind
		}
		return null
	}

	fun getKeybindsWithKey(key: Key): ArrayList<KeyBinding?> {
		val toReturn = ArrayList<KeyBinding?>()
		for (bind in keyCallbacks) {
			if (bind.getKey() == key)
				toReturn.add(bind)
		}
		return toReturn
	}

	init {
		defaultCategories.add("key.categories.movement")
		defaultCategories.add("key.categories.gameplay")
		defaultCategories.add("key.categories.inventory")
		defaultCategories.add("key.categories.creative")
		defaultCategories.add("key.categories.multiplayer")
		defaultCategories.add("key.categories.ui")
		defaultCategories.add("key.categories.misc")
	}
}
