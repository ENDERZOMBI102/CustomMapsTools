package com.enderzombi102.cmt.registry

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.CustomMapsTools.Companion.LOGGER
import com.enderzombi102.cmt.block.entity.DisplayBlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.registry.Registry
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder

object BlockEntityRegistry {
	val displayBlockEntityType: BlockEntityType<DisplayBlockEntity> =
		QuiltBlockEntityTypeBuilder.create(::DisplayBlockEntity, BlockRegistry["display"] ).build(null)

	fun register() {
		LOGGER.info( "Registering block entities.." )
		Registry.register( Registry.BLOCK_ENTITY_TYPE, "screen_block_entity.type".toId(), displayBlockEntityType )
	}
}
