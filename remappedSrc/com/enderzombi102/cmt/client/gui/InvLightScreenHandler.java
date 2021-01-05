package com.enderzombi102.cmt.client.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class InvLightScreenHandler extends ScreenHandler {

	protected InvLightScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
		super(type, syncId);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}

}
