package com.enderzombi102.cmt.block;

import com.enderzombi102.cmt.block.entity.DisplayBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class DisplayBlock extends Block implements BlockEntityProvider {

	public DisplayBlock() {
		super( FabricBlockSettings.of(Material.BARRIER).hardness(1.0F) );
	}


	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new DisplayBlockEntity();
	}
}
