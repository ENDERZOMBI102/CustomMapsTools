package com.enderzombi102.cmt.zone

import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d
import java.util.*

abstract class AbstractZone<T : Entity?> {
	var pos0: Vec3d
	var pos1: Vec3d
	val id: String
	var deleteOnExit = false
	var world: ServerWorld?
	private val lastEntities: MutableList<T> = ArrayList()
	protected val server: MinecraftServer
	private val cachedBox: Box
	private val entityClass: Class<T>

	constructor(world: ServerWorld, pos0: Vec3d, pos1: Vec3d, id: String, entityClass: Class<T>) {
		this.world = world
		server = world.server
		this.pos0 = pos0
		this.pos1 = pos1
		this.id = id
		this.entityClass = entityClass
		cachedBox = Box(this.pos0, this.pos1)
	}

	// serialization
	constructor(world: ServerWorld?, tag: NbtCompound, entityClass: Class<T>) {
		this.world = world
		server = world!!.server
		id = tag.getString("id")
		pos0 = Vec3d(tag.getDouble("x0"), tag.getDouble("y0"), tag.getDouble("z0"))
		pos1 = Vec3d(tag.getDouble("x1"), tag.getDouble("y1"), tag.getDouble("z1"))
		this.entityClass = entityClass
		cachedBox = Box(pos0, pos1)
	}

	fun tick() {
		// get all players in the zone
		val entities = world!!.getEntitiesByClass(
			entityClass,
			cachedBox
		) { entity: T -> !entity!!.isSpectator && entity.isAlive }

		// check for entering players
		for (entity in entities) {
			if (!lastEntities.contains(entity)) {
				onEnter(entity)
				lastEntities.add(entity)
			}
		}

		// check for exiting players
		val toRemove = ArrayList<T>()
		for (entity in lastEntities) {
			if (!entities.contains(entity)) {
				onLeave(entity)
				toRemove.add(entity)
			}
		}
		lastEntities.removeAll(toRemove)
		for (entity in lastEntities) {
			onTick(entity)
		}
	}

	val box: Box
		get() = Box(pos0, pos1)

	fun getLastEntities(): List<T> {
		return lastEntities
	}

	abstract fun onEnter(entity: T)
	abstract fun onLeave(entity: T)
	abstract fun onTick(entity: T)

	// serialization
	open fun writeToNbt(tag: NbtCompound) {
		tag.putString("type", this.javaClass.simpleName.lowercase(Locale.getDefault()))
		tag.putString("id", id)
		tag.putDouble("x0", pos0.x)
		tag.putDouble("y0", pos0.y)
		tag.putDouble("z0", pos0.z)
		tag.putDouble("x1", pos1.x)
		tag.putDouble("y1", pos1.y)
		tag.putDouble("z1", pos1.z)
	}
}
