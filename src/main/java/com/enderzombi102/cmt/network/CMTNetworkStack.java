package com.enderzombi102.cmt.network;

import alexiil.mc.lib.net.*;
import alexiil.mc.lib.net.impl.ActiveMinecraftConnection;
import alexiil.mc.lib.net.impl.McNetworkStack;
import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.keybind.KeybindComponent;
import net.minecraft.entity.player.PlayerEntity;

public class CMTNetworkStack {

	public static final ParentNetIdSingle<KeybindComponent> KEY_BIND = new ParentNetIdSingle<KeybindComponent>(McNetworkStack.ROOT, KeybindComponent.class, "keybind", -1) {
		@Override
		public KeybindComponent readContext(NetByteBuf buffer, IMsgReadCtx ctx) {
			ActiveMinecraftConnection mcConn = (ActiveMinecraftConnection) ctx.getConnection();
			PlayerEntity player = mcConn.ctx.getPlayer();
			return CMTContent.BIND_COMP_KEY.get( player.world.getLevelProperties() );
		}

		@Override
		public void writeContext(NetByteBuf buffer, IMsgWriteCtx ctx, KeybindComponent comp) {}
	};



}
