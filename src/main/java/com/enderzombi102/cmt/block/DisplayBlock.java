package com.enderzombi102.cmt.block;

import com.enderzombi102.cmt.block.entity.DisplayBlockEntity;
import com.enderzombi102.cmt.state.property.IdentifierProperty;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DisplayBlock extends Block implements BlockEntityProvider {

	public DisplayBlock() {
		super( FabricBlockSettings.of(Material.BARRIER).hardness(1.0F) );
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new DisplayBlockEntity();
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack stack = player.getStackInHand(hand);
		if ( stack != null ) {
			( (DisplayBlockEntity) Objects.requireNonNull( world.getBlockEntity(pos) ) ).stack = stack;
		}
		return ActionResult.SUCCESS;
	}
}
