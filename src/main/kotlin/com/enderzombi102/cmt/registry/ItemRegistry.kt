package com.enderzombi102.cmt.registry

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.CustomMapsTools
import com.enderzombi102.cmt.CustomMapsTools.Companion.LOGGER
import com.enderzombi102.cmt.item.ZoneCreatorItem
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings

object ItemRegistry {
	private val ITEMS: Map<String, Item> = mapOf(
		"inv_light" to BlockItem( BlockRegistry["inv_light"], settings() ),
		"display" to BlockItem( BlockRegistry["display"], settings() ),
		"zone_creator" to ZoneCreatorItem( settings() )
	)

	fun register() {
		LOGGER.info( "Registering items.." )
		for ( ( key, value ) in ITEMS )
			Registry.register( Registries.ITEM, key.toId(), value )
	}

	operator fun get( itemId: String ): Item =
		ITEMS.getOrDefault( itemId, Items.AIR )

	private fun settings(): QuiltItemSettings =
		QuiltItemSettings()
}
