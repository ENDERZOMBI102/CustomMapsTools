package com.enderzombi102.cmt.block

import com.enderzombi102.cmt.CustomMapsTools
import com.enderzombi102.cmt.CustomMapsToolsClient
import com.enderzombi102.cmt.registry.BlockRegistry
import com.enderzombi102.cmt.registry.ItemRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings
import java.util.*

@Suppress("OVERRIDE_DEPRECATION")
class InvLightBlock : Block(
	QuiltBlockSettings
		.of( Material.BARRIER )
		.collidable( false )
		.strength( -1.0f, 3600000.8f )
		.dropsNothing()
		.nonOpaque()
		.luminance { it.get( LIGHT_LEVEL ) }
) {
	init {
		defaultState = getStateManager().defaultState.with( LIGHT_LEVEL, 15 )
	}

	override fun isTranslucent( state: BlockState, world: BlockView, pos: BlockPos ): Boolean =
		true

	override fun getRenderType( state: BlockState ): BlockRenderType =
		BlockRenderType.INVISIBLE

	override fun appendProperties( stateManager: StateManager.Builder<Block, BlockState> ) {
		stateManager.add(LIGHT_LEVEL)
	}

	@Environment(EnvType.CLIENT)
	override fun getAmbientOcclusionLightLevel( state: BlockState, world: BlockView, pos: BlockPos ): Float =
		0.0f

	override fun onUse(
		state: BlockState,
		world: World,
		pos: BlockPos,
		player: PlayerEntity,
		hand: Hand,
		hit: BlockHitResult
	): ActionResult {
		if ( world.isClient() )
			return ActionResult.SUCCESS

		var currentLevel: Int = state.get( LIGHT_LEVEL )
		currentLevel = when {
			// normal: add 1 to light level
			!player.isSneaking -> ( currentLevel + 1 ) % 16

			// sneak: reduce light level
			currentLevel > 0 -> currentLevel - 1
			else -> 15
		}
		world.setBlockState( pos, state.with( LIGHT_LEVEL, currentLevel ) )

		return ActionResult.PASS
	}

	@Environment(EnvType.CLIENT)
	fun randomDisplayTick(state: BlockState?, world: World, pos: BlockPos, random: Random?) {
		val player = MinecraftClient.getInstance().player ?: return

		// render "lightbulb" particle
		if ( player.mainHandStack.item === ItemRegistry["inv_light"] )
			world.addParticle(
				CustomMapsToolsClient.invLightParticle,
				pos.x.toDouble() + 0.5,
				pos.y.toDouble() + 0.5,
				pos.z.toDouble() + 0.5,
				0.0,
				0.0,
				0.0
			)
	}

	companion object {
		val LIGHT_LEVEL = IntProperty.of( "light_level", 0, 15 )!!
	}
}
