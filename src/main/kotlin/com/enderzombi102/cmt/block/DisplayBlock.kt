package com.enderzombi102.cmt.block

import com.enderzombi102.cmt.block.entity.DisplayBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings
import java.util.*

@Suppress("OVERRIDE_DEPRECATION")
class DisplayBlock : Block( QuiltBlockSettings.of(Material.BARRIER).hardness(1.0f) ), BlockEntityProvider {
	override fun createBlockEntity( pos: BlockPos, state: BlockState ): BlockEntity =
		DisplayBlockEntity( pos, state )

	override fun onUse(
		state: BlockState,
		world: World,
		pos: BlockPos,
		player: PlayerEntity,
		hand: Hand,
		hit: BlockHitResult
	): ActionResult {
		( world.getBlockEntity( pos ) as DisplayBlockEntity )
			.apply { markDirty() }
			.stack = player.getStackInHand( hand )
		return ActionResult.SUCCESS
	}
}
