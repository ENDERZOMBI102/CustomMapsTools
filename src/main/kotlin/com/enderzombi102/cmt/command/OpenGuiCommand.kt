package com.enderzombi102.cmt.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.command.CommandBuildContext
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource

object OpenGuiCommand {
	fun register( dispatcher: CommandDispatcher<ServerCommandSource?>, buildCtx: CommandBuildContext ) {
		dispatcher.register(
			literal( "opengui" )
				.then(
					argument( "identifier", StringArgumentType.string() )
						.executes( ::onCommand )
				)
		)
	}

	@Throws(CommandSyntaxException::class)
	fun onCommand( ctx: CommandContext<ServerCommandSource> ): Int {
		val guiId = ctx.getArgument( "identifier", String::class.java )
		return 0
	}
}
