package com.enderzombi102.cmt.command;

import com.enderzombi102.cmt.gui.CustomGuiManager;
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
									.suggests( (ctx, builder) -> {/*
										for (AbstractZone<? extends Entity> zone : CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZones() ) {
											builder.suggest(zone.id);
										}*/
										return builder.buildFuture();
									})
									.executes( OpenGuiCommand::onCommand )
					)
		);
	}

	public static int onCommand(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
		String guiId = ctx.getArgument("identifier", String.class);

		CustomGuiManager.openScreen( ctx.getSource().getPlayer(), guiId );

		return 0;
	}
}
