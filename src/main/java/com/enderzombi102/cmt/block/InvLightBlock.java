package com.enderzombi102.cmt.block;

import com.enderzombi102.cmt.CMTContent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class InvLightBlock extends Block {

	public static final IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);

	public InvLightBlock() {
		super(
				FabricBlockSettings
						.of(Material.BARRIER)
						.collidable(false)
						.strength(-1.0F, 3600000.8F)
						.dropsNothing()
						.nonOpaque()
						.lightLevel( (state) -> state.get(LIGHT_LEVEL) )
		);
		this.setDefaultState( this.getStateManager().getDefaultState().with(LIGHT_LEVEL, 15) );
	}


	@Override
	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(LIGHT_LEVEL);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
		return 0.0F;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if ( world.isClient() ) return ActionResult.SUCCESS;
		int currentLevel = state.get(LIGHT_LEVEL);
		if (! player.isSneaking() ) {
			if (currentLevel > 0) {
				currentLevel = currentLevel - 1;
			} else {
				currentLevel = 15;
			}
		} else {
			if (currentLevel < 15) {
				currentLevel = currentLevel + 1;
			} else {
				currentLevel = 0;
			}
		}
		world.setBlockState( pos, state.with(LIGHT_LEVEL, currentLevel) );
		return ActionResult.PASS;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		PlayerEntity player = MinecraftClient.getInstance().player;
		if ( player == null ) return;

		if ( player.getMainHandStack().getItem().equals(CMTContent.invLightItem) ) {
			int i = pos.getX(), j = pos.getY(), k = pos.getZ();
			world.addParticle(
					CMTContent.invLightParticle,
					(double) i + 0.5D,
					(double) j + 0.5D,
					(double) k + 0.5D,
					0.0D,
					0.0D,
					0.0D
			);
		}
	}
}
