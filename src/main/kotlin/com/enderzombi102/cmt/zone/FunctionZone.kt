package com.enderzombi102.cmt.zone

import com.enderzombi102.cmt.CustomMapsTools
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.function.CommandFunction
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d

class FunctionZone : AbstractZone<PlayerEntity> {
	val enterFunction: Identifier?
	val leaveFunction: Identifier?
	val midFunction: Identifier?
	private var enterFunc: CommandFunction? = null
	private var midFunc: CommandFunction? = null
	private var leaveFunc: CommandFunction? = null

	constructor(world: ServerWorld, pos0: Vec3d, pos1: Vec3d, id: String, data: ZoneActionData) : super(
		world,
		pos0,
		pos1,
		id,
		PlayerEntity::class.java
	) {
		enterFunction = data.enterFunction
		leaveFunction = data.leaveFunction
		midFunction = data.midFunction
		loadFunc()
	}

	// serialization
	constructor(world: ServerWorld?, tag: NbtCompound) : super(world, tag, PlayerEntity::class.java) {
		enterFunction = Identifier(tag.getString("efunc"))
		midFunction = Identifier(tag.getString("mfunc"))
		leaveFunction = Identifier(tag.getString("lfunc"))
		loadFunc()
	}

	private fun loadFunc() {
		// get possible function objects
		val enter = server.commandFunctionManager.getFunction(enterFunction)
		val stay = server.commandFunctionManager.getFunction(midFunction)
		val leave = server.commandFunctionManager.getFunction(leaveFunction)

		// check if we found all objects + log if not
		if (enter.isPresent) enterFunc =
			enter.get() else CustomMapsTools.LOGGER.warn("[CMT] function <" + enterFunction + "> was not found")
		if (stay.isPresent) midFunc =
			stay.get() else CustomMapsTools.LOGGER.warn("[CMT] function <" + midFunction + "> was not found")
		if (leave.isPresent) leaveFunc =
			leave.get() else CustomMapsTools.LOGGER.warn("[CMT] function <" + leaveFunction + "> was not found")
	}

	override fun onEnter(entity: PlayerEntity) {
		server.commandFunctionManager.execute(
			enterFunc,
			server.commandSource.withEntity(entity).withWorld(world)
		)
	}

	override fun onLeave(entity: PlayerEntity) {
		server.commandFunctionManager.execute(
			leaveFunc,
			server.commandSource.withEntity(entity).withWorld(world)
		)
	}

	override fun onTick(entity: PlayerEntity) {
		server.commandFunctionManager.execute(
			midFunc,
			server.commandSource.withEntity(entity).withWorld(world)
		)
	}

	// serialization
	override fun writeToNbt(tag: NbtCompound) {
		super.writeToNbt(tag)
		tag.putString("efunc", enterFunction.toString())
		tag.putString("mfunc", midFunction.toString())
		tag.putString("lfunc", leaveFunction.toString())
	}
}
