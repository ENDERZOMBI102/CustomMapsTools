package com.enderzombi102.cmt.block.entity

import com.enderzombi102.cmt.CustomMapsTools.Companion.LOGGER
import com.enderzombi102.cmt.registry.BlockEntityRegistry.displayBlockEntityType
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

class DisplayBlockEntity( pos: BlockPos, state: BlockState ) : BlockEntity( displayBlockEntityType, pos, state ) {
	var stack: ItemStack = ItemStack( Items.JUKEBOX )

	override fun writeNbt( tag: NbtCompound ) {
		super.writeNbt( tag )
		tag.putString( "item", Registry.ITEM.getId( stack.item ).toString() )
	}

	override fun readNbt( tag: NbtCompound ) {
		super.readNbt( tag )
		val id = tag.getString("item")

		// set item
		stack = ItemStack( Registry.ITEM.getOrEmpty( Identifier( id ) ).orElseGet {
			LOGGER.warn( "display at `x = ${pos.x}, y = ${pos.y}, z = ${pos.z}` contains the item `$id`, which is missing from registry!" )
			Items.JUKEBOX
		})
	}
}
