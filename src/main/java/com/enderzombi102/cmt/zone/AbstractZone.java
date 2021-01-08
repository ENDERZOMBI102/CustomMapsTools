package com.enderzombi102.cmt.zone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractZone {

	public BlockPos pos0, pos1;

	public AbstractZone() {

	}




	public abstract void onEnter(PlayerEntity entity);
	public abstract void onExit(PlayerEntity entity);

}
