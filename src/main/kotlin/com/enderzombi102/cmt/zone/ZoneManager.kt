package com.enderzombi102.cmt.zone

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World
import org.apache.commons.lang3.RandomStringUtils

class ZoneManager( world: World? ) : ZoneComponent {
	private val world: ServerWorld? = if ( world is ServerWorld ) world else null
	private val zones = ArrayList<AbstractZone<out Entity>>()

	override fun writeToNbt(tag: NbtCompound) {
		val list = NbtList()
		for (i in zones.indices) {
			if (zones[i].deleteOnExit) continue
			val compoundTag = NbtCompound()
			zones[i].writeToNbt(compoundTag)
			list.addElement(i, compoundTag)
		}
		tag.put("zones", list)
	}

	override fun readFromNbt(tag: NbtCompound) {
		zones.clear()
		for (ctag in (tag["zones"] as NbtList?)!!.toTypedArray()) {
			val compoundTag = ctag as NbtCompound
			var zone: AbstractZone<out Entity>
			zone = when (compoundTag.getString("type")) {
				"FunctionZone" -> FunctionZone(world, compoundTag)
				else -> CommandZone(world, compoundTag)
			}
			zones.add(zone)
		}
	}

	override fun serverTick() {
		for (zone in zones) {
			zone.tick()
		}
	}

	// ZoneComponent-specific methods
	override fun addZone(zone: AbstractZone<out Entity>) {
		zone.world = world
		if (world != null) zones.add(zone)
	}

	override fun removeZone(entity: PlayerEntity): String? {
		for (zone in zones) {
			if (zone.box.intersects(entity.boundingBox)) {
				val buf = zone.id
				zones.remove(zone)
				return buf
			}
		}
		return null
	}

	override fun removeZone(id: String) {
		zones.remove(getZone(id))
	}

	override fun containsZone(id: String): Boolean {
		return zones.stream().anyMatch { zone: AbstractZone<out Entity>? -> zone!!.id == id }
	}

	override fun entityInZone(entities: List<Entity>, id: String): Boolean {
		val zone = getZone(id) ?: return false
		for (ent in entities) {
			if (zone.getLastEntities().contains(ent)) return true
		}
		return false
	}

	override fun getZone(id: String): AbstractZone<out Entity>? {
		return zones.stream().filter { zone: AbstractZone<out Entity>? -> zone!!.id == id }.findAny().orElse(null)
	}

	override fun getZones(): List<AbstractZone<out Entity>> {
		return zones
	}

	override fun isIdValid(id: String): Boolean {
		return !containsZone(id)
	}

	override val randomId: String
		get() {
			var id: String
			do {
				id = RandomStringUtils.random(8)
			} while (isIdValid(id))
			return id
		}
}
