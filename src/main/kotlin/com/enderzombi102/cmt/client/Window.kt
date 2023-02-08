package com.enderzombi102.cmt.client

import net.minecraft.SharedConstants
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier

object Window {
	// FOR THE CUSTOM WINDOW TITLE ANC ICONS, LOOK AT THIS!
	// https://github.com/TheRandomLabs/RandomPatches/blob/1.16-fabric/src/main/java/com/therandomlabs/randompatches/mixin/client/MinecraftClientMixin.java
	val DEFAULT_TITLE = "Minecraft " + SharedConstants.getGameVersion().name
	var windowTitle = DEFAULT_TITLE

	@JvmStatic
	var title: String
		get() = windowTitle
		set(title) {
			windowTitle = title
			MinecraftClient.getInstance().window.setTitle( title )
		}

	fun setIcon( iconBasePath: String ) {
		val client = MinecraftClient.getInstance()
		val icon16 = Identifier(iconBasePath + "16.png")
		val icon32 = Identifier(iconBasePath + "32.png")
		client.window.setIcon(
			client.resourceManager.getResource( icon16 ).orElseThrow().open(),
			client.resourceManager.getResource( icon32 ).orElseThrow().open()
		)
	}
}
