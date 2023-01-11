package com.enderzombi102.cmt.registry

import com.enderzombi102.cmt.CMTContent
import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.CustomMapsTools.Companion.LOGGER
import com.enderzombi102.cmt.block.entity.DisplayBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder

object BlockEntityRegistry {
	val displayBlockEntityType: BlockEntityType<DisplayBlockEntity> =
		QuiltBlockEntityTypeBuilder.create( ::DisplayBlockEntity, BlockRegistry["display"] ).build(null)

	fun register() {
		LOGGER.info( "Registering block entities.." )
		Registry.register( Registries.BLOCK_ENTITY_TYPE, "screen_block_entity.type".toId(), displayBlockEntityType )
	}
}