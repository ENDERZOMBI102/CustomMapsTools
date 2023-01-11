package com.enderzombi102.cmt.registry

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.CustomMapsTools.Companion.LOGGER
import com.enderzombi102.cmt.block.DisplayBlock
import com.enderzombi102.cmt.block.InvLightBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object BlockRegistry {
	private val BLOCKS: Map<String, Block> = mapOf(
		"invis_light" to InvLightBlock(),
		"display" to DisplayBlock()
	)

	fun register() {
		LOGGER.info("Registering blocks..")
		for ( ( key, value ) in BLOCKS )
			Registry.register( Registries.BLOCK, key.toId(), value )
	}

	operator fun get( blockId: String ): Block =
		BLOCKS.getOrDefault( blockId, Blocks.AIR )
}
