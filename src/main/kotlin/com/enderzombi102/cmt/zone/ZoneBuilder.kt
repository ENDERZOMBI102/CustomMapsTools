package com.enderzombi102.cmt.zone

import com.enderzombi102.cmt.CustomMapsTools
import com.enderzombi102.cmt.zone.CommandZone
import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import java.lang.reflect.InvocationTargetException

class ZoneBuilder {
	private var type: Class<out AbstractZone<out Entity>>? = null
	private var pos0: Vec3d? = null
	private var pos1: Vec3d? = null
	private var id: String? = null
	private var clazz: Class<out Entity>? = null
	private val data = ZoneActionData()
	private var deleteOnExit = false
	fun withEnterCommand(cmd: String?): ZoneBuilder {
		data.enterCommand = cmd
		return this
	}

	fun withLeaveCommand(cmd: String?): ZoneBuilder {
		data.leaveCommand = cmd
		return this
	}

	fun withType(type: Class<out AbstractZone<out Entity>>?): ZoneBuilder {
		this.type = type
		return this
	}

	fun withIdentifier(id: String?): ZoneBuilder {
		this.id = id
		return this
	}

	fun deleteOnExit(): ZoneBuilder {
		deleteOnExit = true
		return this
	}

	fun withIdentifier(id: Identifier): ZoneBuilder {
		return this.withIdentifier(id.toString())
	}

	fun withPosition(pos0: Vec3d?, pos1: Vec3d?): ZoneBuilder {
		this.pos0 = pos0
		this.pos1 = pos1
		return this
	}

	fun withEntityType(clazz: Class<out Entity>?): ZoneBuilder {
		this.clazz = clazz
		return this
	}

	fun build(): AbstractZone<out Entity>? {
		val zone: AbstractZone<out Entity>
		zone = try {
			if (type != CommandZone::class.java) {
				type!!
					.getDeclaredConstructor(
						ServerWorld::class.java,
						Vec3d::class.java,
						Vec3d::class.java,
						String::class.java,
						ZoneActionData::class.java,
						Class::class.java
					)
					.newInstance(null, pos0, pos1, id, data, clazz)
			} else {
				type!!
					.getDeclaredConstructor(
						ServerWorld::class.java,
						Vec3d::class.java,
						Vec3d::class.java,
						String::class.java,
						ZoneActionData::class.java
					)
					.newInstance(null, pos0, pos1, id, data)
			}
		} catch (e: NoSuchMethodException) {
			CustomMapsTools.LOGGER.error("Failed to create zone!\n", e)
			return null
		} catch (e: InstantiationException) {
			CustomMapsTools.LOGGER.error("Failed to create zone!\n", e)
			return null
		} catch (e: IllegalAccessException) {
			CustomMapsTools.LOGGER.error("Failed to create zone!\n", e)
			return null
		} catch (e: InvocationTargetException) {
			CustomMapsTools.LOGGER.error("Failed to create zone!\n", e)
			return null
		}
		zone.deleteOnExit = deleteOnExit
		return zone
	}
}
