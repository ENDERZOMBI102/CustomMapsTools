package com.enderzombi102.cmt.command

import net.minecraft.entity.Entity
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.function.CommandFunction
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier

class Executor( private val server: MinecraftServer, executes: String ) {
	private var command: String? = null
	private var function: CommandFunction? = null

	init {
		val func = server.commandFunctionManager.getFunction( Identifier( executes ) )
		if (func.isPresent) {
			function = func.get()
			command = null
		} else {
			function = null
			command = executes
		}
	}

	fun execute( entity: Entity ) {
		if ( function != null )
			server.commandFunctionManager.execute( function, getSource( entity ) )
		else
			server.commandManager.executePrefixedCommand( getSource( entity ), command )
	}

	private fun getSource(entity: Entity): ServerCommandSource {
		return server.commandSource
			.withEntity(entity)
			.withWorld(entity.commandSenderWorld as ServerWorld)
			.withPosition(entity.pos)
			.withRotation(entity.rotationClient)
	}
}
