package com.enderzombi102.cmt.command;

import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.zone.AbstractZone;
import com.enderzombi102.cmt.zone.CommandZone;
import com.enderzombi102.cmt.zone.FunctionZone;
import com.enderzombi102.cmt.zone.ZoneActionData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ZoneCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(
				literal("zone")
						.requires( source -> source.hasPermissionLevel(4) )
						.then( literal("list")
								.then( argument("type", StringArgumentType.string() )
										.executes(ZoneCommand::onList)
								)
						)
						.then( literal("add")
								.executes(ZoneCommand::onAdd)
						)
						.then( literal("remove")
								.executes(ZoneCommand::onRemove)
						)
		);
	}

	public static int onAdd(CommandContext<ServerCommandSource> ctx) {
		String type = ctx.getArgument("type", String.class);
		String enter = ctx.getArgument("enter", String.class);
		String mid = ctx.getArgument("mid", String.class);
		String leave = ctx.getArgument("leave", String.class);
		String id = ctx.getArgument("id", String.class);
		Vec3d pos0, pos1;
		ServerWorld world = ctx.getSource().getWorld();
		AbstractZone<? extends Entity> zone;
		ZoneActionData data = new ZoneActionData();
		switch (type) {
			case "FunctionZone":
				data.withEnterFunction( new Identifier( enter ) ).withMidFunction( new Identifier( mid ) ).withLeaveFunction( new Identifier( leave ) );
				zone = new FunctionZone(world, pos0, pos1, id, data);
				break;
			default:
				data.withEnterCommand(enter).withMidCommand(mid).withLeaveCommand(leave);
				zone = new CommandZone(world, pos0, pos1, id, data);
				break;
		}
		CMTContent.ZONE_COMP_KEY.get( world ).addZone( zone );
		return 1;
	}

	public static int onList(CommandContext<ServerCommandSource> ctx) {
		StringBuilder buf = new StringBuilder();
		buf.append("type         identifier\n");
		for (AbstractZone<? extends Entity> zone : CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZones() ) {
			buf.append(zone.getClass().getSimpleName() + "   " + zone.id + "\n");
		}
		try {
			ctx.getSource().getPlayer().sendMessage( new LiteralText( buf.toString() ), false );
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public static int onRemove(CommandContext<ServerCommandSource> ctx) {
		return 1;
	}
}
