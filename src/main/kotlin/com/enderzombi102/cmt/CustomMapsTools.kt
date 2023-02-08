package com.enderzombi102.cmt

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.command.OpenGuiCommand
import com.enderzombi102.cmt.command.ZoneCommand
import com.enderzombi102.cmt.registry.BlockEntityRegistry
import com.enderzombi102.cmt.registry.BlockRegistry
import com.enderzombi102.cmt.registry.EventListeners
import com.enderzombi102.cmt.registry.ItemRegistry
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.command.api.CommandRegistrationCallback
import org.quiltmc.qsl.item.group.api.QuiltItemGroup
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CustomMapsTools : ModInitializer {
	override fun onInitialize(container: ModContainer) {
		LOGGER.info("setting up config page!")
		BlockRegistry.register()
		ItemRegistry.register()
		EventListeners.register()
		BlockEntityRegistry.register()

		CommandRegistrationCallback.EVENT.register { dispatcher, ctx, _ ->
			LOGGER.info("Registering commands!")
			ZoneCommand.register( dispatcher, ctx )
			OpenGuiCommand.register( dispatcher, ctx )
		}
	}

	companion object {
		@JvmField
		val LOGGER: Logger = LoggerFactory.getLogger("CustomMapsTools")
//		val CONFIG = CmtConfig.createAndLoad()
		val ITEM_GROUP: ItemGroup = QuiltItemGroup.builder( "item_group".toId() )
			.icon { ItemStack( ItemRegistry["inv_light"] ) }
			.build()
	}
}
