package com.enderzombi102.cmt

import com.enderzombi102.cmt.Const.getId
import com.enderzombi102.cmt.command.OpenGuiCommand
import com.enderzombi102.cmt.command.ZoneCommand
import com.enderzombi102.cmt.registry.ItemRegistry
import com.enderzombi102.cmt.zone.ZoneManager
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer
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
		CMTContent.register()
		CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
			LOGGER.info("Registering commands!")
			ZoneCommand.register(dispatcher)
			OpenGuiCommand.register(dispatcher)
		}
	}

	companion object {
		val LOGGER: Logger = LoggerFactory.getLogger("CustomMapsTools")
		val ITEM_GROUP: ItemGroup = QuiltItemGroup.createWithIcon( getId("item_group") ) {
			ItemStack( ItemRegistry.get("inv_light") )
		}
	}
}