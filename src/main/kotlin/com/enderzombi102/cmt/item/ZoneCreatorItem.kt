package com.enderzombi102.cmt.item

import com.enderzombi102.cmt.Utils
import com.enderzombi102.cmt.registry.ComponentRegistry
import com.enderzombi102.cmt.zone.*
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ZoneCreatorItem( settings: Settings ) : Item( settings ) {
	private var pos0: BlockPos? = null
	private var pos1: BlockPos? = null
	private var type = 0
	private val data: ZoneActionData =
		ZoneActionData()
	override fun appendTooltip( stack: ItemStack, world: World?, tooltip: MutableList<Text>, ctx: TooltipContext ) {
		if ( world == null )
			return
		tooltip.add( Text.literal( "Zone type : ${ZoneTypes.getType(type)}" ) )
		tooltip.add( Text.literal( "Position 0: ${pos0?.toShortString() ?: "not set"}" ) )
		tooltip.add( Text.literal( "Position 1: ${pos1?.toShortString() ?: "not set"}" ) )
	}

	override fun use( world: World, user: PlayerEntity, hand: Hand ): TypedActionResult<ItemStack> {
		if ( world.isClient() )
			return TypedActionResult.pass( user.getStackInHand( hand ) )
		if ( user.isSneaking ) {
			if ( type == ZoneTypes.length - 1 )
				type = 0
			else
				type++
			user.sendMessage( Text.literal("Zone type set to ${ZoneTypes.getType(type)}."), true )
		}
		return TypedActionResult.pass( user.getStackInHand( hand ) )
	}

	override fun useOnBlock( ctx: ItemUsageContext ): ActionResult {
		if ( ctx.player == null || ctx.world.isClient() )
			return ActionResult.PASS

		if ( pos0 == null ) {
			pos0 = ctx.blockPos
			ctx.player!!.sendMessage( Text.literal( "Point 0 set to ${pos0!!.toShortString()}." ), true)
		} else {
			pos1 = ctx.blockPos
			ctx.player!!.sendMessage( Text.literal( "Point 1 set to ${pos1!!.toShortString()}." ), true)
		}
		return ActionResult.SUCCESS
	}

	private fun createZone( ctx: ItemUsageContext ) {
		var zone: AbstractZone<out Entity>? = null
		val mgr: ZoneComponent = ComponentRegistry.ZONE_COMPONENT_KEY.get( ctx.world )
		if (type == 0) {
			zone = CommandZone(
				ctx.world as ServerWorld,
				Utils.vecFrom(pos0!!),
				Utils.vecFrom(pos1!!),
				mgr.randomId,
				data
			)
		} else if (type == 1) {
			zone = FunctionZone(
				ctx.world as ServerWorld,
				Utils.vecFrom(pos0!!),
				Utils.vecFrom(pos1!!),
				mgr.randomId,
				data
			)
		}
		mgr.addZone(zone!!)
	}
}
