package com.enderzombi102.cmt.zone

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d

class CommandZone : AbstractZone<PlayerEntity> {
	private val enterCommand: String?
	private val leaveCommand: String?
	private val midCommand: String?

	constructor( world: ServerWorld, pos0: Vec3d, pos1: Vec3d, id: String, data: ZoneActionData ) : super(
		world,
		pos0,
		pos1,
		id,
		PlayerEntity::class.java
	) {
		enterCommand = data.enterCommand
		leaveCommand = data.leaveCommand
		midCommand = data.midCommand
	}

	// serialization
	constructor( world: ServerWorld?, tag: NbtCompound ) : super( world, tag, PlayerEntity::class.java ) {
		enterCommand = tag.getString("ecmd")
		midCommand = tag.getString("mcmd")
		leaveCommand = tag.getString("lcmd")
	}

	override fun onEnter( entity: PlayerEntity ) {
		server.commandManager.executePrefixedCommand(
			server.commandSource.withEntity(entity).withWorld(world),
			enterCommand
		)
	}

	override fun onLeave(entity: PlayerEntity) {
		server.commandManager.executePrefixedCommand(
			server.commandSource.withEntity(entity).withWorld(world),
			leaveCommand
		)
	}

	override fun onTick(entity: PlayerEntity) {
		server.commandManager.executePrefixedCommand(
			server.commandSource.withEntity(entity).withWorld(world),
			midCommand
		)
	}

	// serialization
	override fun writeToNbt(tag: NbtCompound) {
		super.writeToNbt(tag)
		tag.putString("ecmd", enterCommand)
		tag.putString("mcmd", midCommand)
		tag.putString("lcmd", leaveCommand)
	}
}
