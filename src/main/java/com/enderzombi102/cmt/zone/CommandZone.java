package com.enderzombi102.cmt.zone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class CommandZone extends AbstractZone<PlayerEntity> {

	public final String enterCommand;
	public final String leaveCommand;
	public final String midCommand;

	public CommandZone(ServerWorld world, Vec3d pos0, Vec3d pos1, String id, ZoneActionData data) {
		super(world, pos0, pos1, id, PlayerEntity.class );
		this.enterCommand = data.enterCommand;
		this.leaveCommand = data.leaveCommand;
		this.midCommand = data.midCommand;
	}

	// serialization
	public CommandZone(ServerWorld world, NbtCompound tag) {
		super(world, tag, PlayerEntity.class);
		this.enterCommand = tag.getString("ecmd");
		this.midCommand = tag.getString("mcmd");
		this.leaveCommand = tag.getString("lcmd");
	}

	@Override
	public void onEnter(PlayerEntity entity) {
		this.server.getCommandManager().execute(
				this.server.getCommandSource().withEntity(entity).withWorld(this.world),
				this.enterCommand
		);
	}

	@Override
	public void onLeave(PlayerEntity entity) {
		this.server.getCommandManager().execute(
				this.server.getCommandSource().withEntity(entity).withWorld(this.world),
				this.leaveCommand
		);
	}

	@Override
	public void onTick(PlayerEntity entity) {
		this.server.getCommandManager().execute(
				this.server.getCommandSource().withEntity(entity).withWorld(this.world),
				this.midCommand
		);
	}

	// serialization
	@Override
	public void writeToNbt(NbtCompound tag) {
		super.writeToNbt(tag);
		tag.putString("ecmd", this.enterCommand);
		tag.putString("mcmd", this.midCommand);
		tag.putString("lcmd", this.leaveCommand);
	}
}
