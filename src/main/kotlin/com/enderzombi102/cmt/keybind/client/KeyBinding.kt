package com.enderzombi102.cmt.keybind.client

import com.enderzombi102.cmt.mixins.client.KeyBindingEntryAccessor
import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.widget.option.KeyBindListWidget
import net.minecraft.client.gui.widget.option.KeyBindListWidget.KeyBindEntry
import net.minecraft.client.option.KeyBind
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.text.component.TranslatableComponent
import java.util.function.Consumer

class KeyBinding( key: Key, @JvmField val callback: Consumer<MinecraftClient>, transText: String, category: String, requiresInGame: Boolean, requiresInteracting: Boolean ) : KeyBind( transText, key.code, category ) {
	var intKey: Int
		private set
	private val defaultKey: Int
	private val translationText: TranslatableComponent
	val categoryy: String
	@JvmField
	val requiresInGame: Boolean
	@JvmField
	val requiresInteracting: Boolean

	init {
		intKey = key.code
		this.defaultKey = key.code
		translationText = TranslatableComponent(transText)
		this.categoryy = category
		this.requiresInGame = requiresInGame // requires the game to not be paused
		this.requiresInteracting = requiresInteracting // requires be in-game and not in a inventory

		// taken from FAPI
		val map = KeyBindingAccessor.fabric_getCategoryMap()
		if (!KeyBindingAccessor.fabric_getCategoryMap().containsKey(category)) {
			val largest = map.values.stream().max { obj: Int, anotherInteger: Int? ->
				obj.compareTo( anotherInteger!! )
			}
			val largestInt = largest.orElse(0)
			map[category] = largestInt + 1
		}
	}

	fun getEntry(parent: KeyBindListWidget?): KeyBindEntry {
		return KeyBindingEntryAccessor.invokeInit(parent, this, MutableText.create(translationText))
	}

	fun getTranslationText(): Text {
		return MutableText.create(translationText)
	}

	fun getKey(): Key? {
		for (key in Key.values()) {
			if (key.code == intKey) return key
		}
		return null
	}

	val isUsingDefaultCategories: Boolean
		get() = KeyBindingHelper.defaultCategories.contains(this.categoryy)

	override fun setBoundKey(boundKey: InputUtil.Key) {
		intKey = boundKey.keyCode
		super.setBoundKey(boundKey)
	}

	override fun getCategory(): String {
		return categoryy
	}

	override fun getDefaultKey(): InputUtil.Key {
		return InputUtil.Type.KEYSYM.createFromKeyCode(this.defaultKey)
	}

	override fun isDefault(): Boolean {
		return intKey == this.defaultKey
	}

	override fun getTranslationKey(): String {
		return translationText.key
	}
}
