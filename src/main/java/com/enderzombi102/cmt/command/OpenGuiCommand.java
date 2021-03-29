package com.enderzombi102.cmt.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;


import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class OpenGuiCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(
				literal("opengui")
					.then(
							argument("identifier", StringArgumentType.string() )
									.executes( OpenGuiCommand::onCommand )
					)
		);
	}

	public static int onCommand(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
		String guiId = ctx.getArgument("identifier", String.class);


		return 0;
	}
}
