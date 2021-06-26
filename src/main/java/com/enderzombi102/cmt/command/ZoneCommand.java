package com.enderzombi102.cmt.command;

import com.enderzombi102.cmt.CMTContent;
import com.enderzombi102.cmt.Utils;
import com.enderzombi102.cmt.zone.AbstractZone;
import com.enderzombi102.cmt.zone.CommandZone;
import com.enderzombi102.cmt.zone.FunctionZone;
import com.enderzombi102.cmt.zone.ZoneActionData;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandException;
import net.minecraft.command.argument.*;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.FunctionCommand.SUGGESTION_PROVIDER;

public class ZoneCommand {

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {


		// inside command
		dispatcher.register(
				literal("inside")
						.requires( source -> source.hasPermissionLevel(1) )
						.then( argument("entity", EntityArgumentType.entities() )
								.then( argument("identifier", StringArgumentType.string() )
										.suggests( (ctx, builder) -> {
											for (AbstractZone<? extends Entity> zone : CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZones() ) {
												builder.suggest(zone.id);
											}
											return builder.buildFuture();
										})
										.executes( ctx -> {
											if( CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).entityInZone(
													new ArrayList<>( EntityArgumentType.getEntities(ctx, "entity") ),
													ctx.getArgument("identifier", String.class)
											) ) return  4;
											else return 0;
										})
								)
						)
		);

		dispatcher.register(
				literal("countent")
						.requires( source -> source.hasPermissionLevel(4) )
						.then( argument("identifier", StringArgumentType.string() )
								.suggests( (ctx, builder) -> {
									for (AbstractZone<? extends Entity> zone : CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZones() ) {
										builder.suggest(zone.id);
									}
									return builder.buildFuture();
								})
								.executes(
										ctx -> CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZone(
												ctx.getArgument("identifier", String.class)
										).getLastEntities().size()
								)
								.then( argument("except", EntitySummonArgumentType.entitySummon())
										.executes( ctx -> {
											Identifier ent = EntitySummonArgumentType.getEntitySummon(ctx, "except");
											// count all entities BUT the excluded one
											return (int) CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).getZone(
													ctx.getArgument("identifier", String.class)
											).getLastEntities().stream().filter( e -> Registry.ENTITY_TYPE.getId( e.getType() ) != ent ).count();
										})
								)
						)
		);

		// zone command
		dispatcher.register(
				literal("zone")
						.requires( source -> source.hasPermissionLevel(4) )
						.then( literal("list")
								.executes(ZoneCommand::onList)
						)
						.then( literal("add")
								.then( literal("FunctionZone" )
										.then( argument("identifier", StringArgumentType.string() )
												.then( argument("pos0", BlockPosArgumentType.blockPos() )
														.then( argument("pos1", BlockPosArgumentType.blockPos() )
																.then( argument("enterf", CommandFunctionArgumentType.commandFunction() )
																		.suggests(SUGGESTION_PROVIDER)
																		.then( argument("midf", CommandFunctionArgumentType.commandFunction() )
																				.suggests(SUGGESTION_PROVIDER)
																				.then( argument("leavef", CommandFunctionArgumentType.commandFunction() )
																						.suggests(SUGGESTION_PROVIDER)
																						.executes(ZoneCommand::onAddFunction)
																				)
																		)
																)
														)
												)
										)
								)
								.then(literal("CommandZone")
										.then( argument("identifier", StringArgumentType.string() )
												.then( argument("pos0", BlockPosArgumentType.blockPos() )
														.then( argument("pos1", BlockPosArgumentType.blockPos() )
																.then( argument("enter", StringArgumentType.string() )
																		.then( argument("mid", StringArgumentType.string() )
																				.then( argument("leave", StringArgumentType.string() )
																						.executes(ZoneCommand::onAddCommand)
																				)
																		)
																)
														)
												)
										)
								)
						)
						.then( literal("remove")
								.executes(ZoneCommand::onRemovePlayer)
								.then( argument("identifier", StringArgumentType.string() )
										.executes(ZoneCommand::onRemoveIdentifier)
								)

						)
		);
	}

	public static int onAddCommand(CommandContext<ServerCommandSource> ctx) {
		String id = ctx.getArgument("identifier", String.class);
		// coordinates
		BlockPos pos0 = ctx.getArgument("pos0", PosArgument.class).toAbsoluteBlockPos( ctx.getSource() );
		BlockPos pos1 = ctx.getArgument("pos1", PosArgument.class).toAbsoluteBlockPos( ctx.getSource() );
		// other vars
		ServerWorld world = ctx.getSource().getWorld();
		AbstractZone<? extends Entity> zone;
		ZoneActionData data = new ZoneActionData();
		// create zone
		data.withEnterCommand( ctx.getArgument("enter", String.class) )
				.withMidCommand( ctx.getArgument("mid", String.class) )
				.withLeaveCommand( ctx.getArgument("leave", String.class) );
		zone = new CommandZone(world, Utils.vecFrom(pos0), Utils.vecFrom(pos1), id, data);
		// add zone
		CMTContent.ZONE_COMP_KEY.get( world ).addZone( zone );
		ctx.getSource().sendFeedback( new LiteralText("Sucessfully added zone \""+id+"\""), false );
		return 1;
	}

	public static int onAddFunction(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
		String id = ctx.getArgument("identifier", String.class);
		// coordinates
		BlockPos pos0 = ctx.getArgument("pos0", PosArgument.class).toAbsoluteBlockPos( ctx.getSource() );
		BlockPos pos1 = ctx.getArgument("pos1", PosArgument.class).toAbsoluteBlockPos( ctx.getSource() );
		// other vars
		ServerWorld world = ctx.getSource().getWorld();
		AbstractZone<? extends Entity> zone;
		ZoneActionData data = new ZoneActionData();
		// create zone
		data.withEnterFunction( getFuncIdentifier( ctx, "enterf") )
				.withMidFunction( getFuncIdentifier( ctx, "midf") )
				.withLeaveFunction( getFuncIdentifier(ctx, "leavef") );
		zone = new FunctionZone(world, Utils.vecFrom(pos0), Utils.vecFrom(pos1), id, data);
		// add zone
		CMTContent.ZONE_COMP_KEY.get( world ).addZone( zone );
		ctx.getSource().sendFeedback( new LiteralText("Sucessfully added zone \""+id+"\""), false );
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

	public static int onRemoveIdentifier(CommandContext<ServerCommandSource> ctx) {
		try {
			CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).removeZone( ctx.getArgument("identifier", String.class) );
			ctx.getSource().sendFeedback( new LiteralText("Sucessfully removed zone \"" + ctx.getArgument("identifier", String.class) + "\""), false );
		} catch (NullPointerException e) {
			throw new CommandException( new LiteralText("This command can only be executed by players") );
		}
		return 1;
	}

	public static int onRemovePlayer(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
		try {
				String id = CMTContent.ZONE_COMP_KEY.get( ctx.getSource().getWorld() ).removeZone( ctx.getSource().getPlayer() );
				ctx.getSource().sendFeedback( new LiteralText("Sucessfully removed zone \"" + id + "\""), false );
		} catch (NullPointerException e) {
			throw new CommandException( new LiteralText("This command can only be executed by players") );
		}
		return 1;
	}

	private static Identifier getFuncIdentifier(CommandContext<ServerCommandSource> ctx, String name) throws CommandSyntaxException {
		return new ArrayList<>( CommandFunctionArgumentType.getFunctions(ctx, name) ).get(0).getId();
	}
}
