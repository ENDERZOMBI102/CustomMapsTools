package com.enderzombi102.cmt.command

import com.enderzombi102.cmt.Utils
import com.enderzombi102.cmt.registry.ComponentRegistry.ZONE_COMPONENT_KEY
import com.enderzombi102.cmt.zone.AbstractZone
import com.enderzombi102.cmt.zone.CommandZone
import com.enderzombi102.cmt.zone.FunctionZone
import com.enderzombi102.cmt.zone.ZoneActionData
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandBuildContext
import net.minecraft.command.CommandException
import net.minecraft.command.argument.*
import net.minecraft.entity.Entity
import net.minecraft.registry.Registries
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.FunctionCommand
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ZoneCommand {
	fun register( dispatcher: CommandDispatcher<ServerCommandSource?>, buildCtx: CommandBuildContext ) {
		// inside command
		dispatcher.register(
			literal("inside")
				.requires { source: ServerCommandSource -> source.hasPermissionLevel(1) }
				.then(
					argument( "entity", EntityArgumentType.entities() )
						.then(
							argument( "identifier", StringArgumentType.string() )
								.suggests { ctx: CommandContext<ServerCommandSource>, builder: SuggestionsBuilder ->
									for ( zone in ZONE_COMPONENT_KEY[ctx.source.world].getZones() ) {
										builder.suggest( zone.id )
									}
									builder.buildFuture()
								}
								.executes { ctx: CommandContext<ServerCommandSource> ->
									val entities = EntityArgumentType.getEntities( ctx, "entity" ).toList()
									val id = ctx.getArgument( "identifier", String::class.java )

									if ( ZONE_COMPONENT_KEY.get( ctx.source.world ).entityInZone( entities, id ) )
										return@executes 4
									else
										return@executes 0
								}
						)
				)
		)
		dispatcher.register(
			literal("countent")
				.requires { source: ServerCommandSource -> source.hasPermissionLevel(4) }
				.then(
					argument("identifier", StringArgumentType.string())
					.suggests { ctx: CommandContext<ServerCommandSource>, builder: SuggestionsBuilder ->
						for ( zone in ZONE_COMPONENT_KEY.get( ctx.source.world ).getZones() )
							builder.suggest(zone.id)
						builder.buildFuture()
					}
					.executes { ctx: CommandContext<ServerCommandSource> ->
						ZONE_COMPONENT_KEY.get( ctx.source.world )
							.getZone( ctx.getArgument( "identifier", String::class.java ) )!!
							.getLastEntities()
							.size
					}
					.then(
						argument("except", RegistryEntryArgumentType.registryEntry( buildCtx, Registries.ENTITY_TYPE.key ) )
							.executes { ctx: CommandContext<ServerCommandSource> ->
								val ent = RegistryEntryArgumentType.getEntityType( ctx, "except" )
								val id = ctx.getArgument( "identifier", String::class.java )

								ZONE_COMPONENT_KEY.get(ctx.source.world)
									.getZone(id)!!
									.getLastEntities()
									.count { it.type.builtInRegistryHolder !== ent }
							}
					)
				)
		)

		// zone command
		dispatcher.register(
			literal("zone")
				.requires { source: ServerCommandSource -> source.hasPermissionLevel(4) }
				.then(
					literal("list")
						.executes( ::onList )
				)
				.then(
					literal("add")
					.then(
						literal("FunctionZone")
							.then(
								argument("identifier", StringArgumentType.string())
									.then(
										argument("pos0", BlockPosArgumentType.blockPos())
											.then(
												argument("pos1", BlockPosArgumentType.blockPos())
													.then(
														argument( "enterf", CommandFunctionArgumentType.commandFunction() )
															.suggests(FunctionCommand.SUGGESTION_PROVIDER)
															.then(
																argument( "midf", CommandFunctionArgumentType.commandFunction() )
																	.suggests(FunctionCommand.SUGGESTION_PROVIDER)
																	.then(
																		argument( "leavef", CommandFunctionArgumentType.commandFunction() )
																			.suggests(FunctionCommand.SUGGESTION_PROVIDER)
																			.executes ( ::onAddFunction )
																	)
															)
													)
											)
									)
							)
					)
					.then(
						literal("CommandZone")
							.then(
								argument("identifier", StringArgumentType.string())
									.then(
										argument("pos0", BlockPosArgumentType.blockPos())
											.then(
												argument("pos1", BlockPosArgumentType.blockPos())
													.then(
														argument( "enter", StringArgumentType.string() )
															.then(
																argument( "mid", StringArgumentType.string() )
																	.then(
																		argument( "leave", StringArgumentType.string() )
																			.executes( ::onAddCommand )
																	)
															)
													)
											)
									)
							)
					)
				)
				.then(
					literal("remove")
					.executes( ::onRemovePlayer )
					.then(
						argument("identifier", StringArgumentType.string())
							.executes( ::onRemoveIdentifier )
					)
				)
		)
	}

	fun onAddCommand(ctx: CommandContext<ServerCommandSource>): Int {
		val id = ctx.getArgument( "identifier", String::class.java )
		// coordinates
		val pos0 = ctx.getArgument( "pos0", PosArgument::class.java ).toAbsoluteBlockPos( ctx.source )
		val pos1 = ctx.getArgument( "pos1", PosArgument::class.java ).toAbsoluteBlockPos( ctx.source )
		// other vars
		val world = ctx.source.world
		val zone: AbstractZone<out Entity?>
		val data = ZoneActionData()
		// create zone
		data.withEnterCommand (ctx.getArgument( "enter", String::class.java ) )
			.withMidCommand( ctx.getArgument( "mid", String::class.java ) )
			.withLeaveCommand( ctx.getArgument( "leave", String::class.java ) )
		zone = CommandZone(
			world,
			Utils.vecFrom(pos0),
			Utils.vecFrom(pos1),
			id,
			data
		)
		// add zone
		ZONE_COMPONENT_KEY.get( world ).addZone( zone )
		ctx.source.sendFeedback( Text.literal( "Sucessfully added zone \"$id\"" ), false )
		return 1
	}

	@Throws(CommandSyntaxException::class)
	fun onAddFunction( ctx: CommandContext<ServerCommandSource> ): Int {
		val id = ctx.getArgument( "identifier", String::class.java )
		// coordinates
		val pos0 = ctx.getArgument( "pos0", PosArgument::class.java ).toAbsoluteBlockPos( ctx.source )
		val pos1 = ctx.getArgument( "pos1", PosArgument::class.java ).toAbsoluteBlockPos( ctx.source )
		// other vars
		val world = ctx.source.world
		val zone: AbstractZone<out Entity?>
		val data = ZoneActionData()
		// create zone
		data.withEnterFunction( getFuncIdentifier( ctx, "enterf" ) )
			.withMidFunction( getFuncIdentifier( ctx, "midf" ) )
			.withLeaveFunction( getFuncIdentifier( ctx, "leavef" ) )
		zone = FunctionZone(
			world,
			Utils.vecFrom(pos0),
			Utils.vecFrom(pos1),
			id,
			data
		)
		// add zone
		ZONE_COMPONENT_KEY.get(world).addZone(zone)
		ctx.source.sendFeedback( Text.literal("Sucessfully added zone \"$id\""), false)
		return 1
	}

	fun onList( ctx: CommandContext<ServerCommandSource> ): Int {
		val buf = buildString {
			append( "type         identifier\n" )
			for ( zone in ZONE_COMPONENT_KEY.get( ctx.source.world ).getZones() )
				append( zone.javaClass.simpleName + "   " + zone.id + "\n" )
		}

		try {
			ctx.source.player!!.sendMessage( Text.literal( buf ), false )
		} catch ( e: CommandSyntaxException ) {
			e.printStackTrace()
			return 0
		}
		return 1
	}

	fun onRemoveIdentifier( ctx: CommandContext<ServerCommandSource> ): Int {
		if ( ctx.source.player == null )
			throw CommandException( Text.literal( "This command can only be executed by players" ) )

		val zone = ctx.getArgument("identifier", String::class.java)
		ZONE_COMPONENT_KEY.get(ctx.source.world).removeZone( zone )
		ctx.source.sendFeedback( Text.literal( "Sucessfully removed zone '$zone'" ), false )
		return 1
	}

	@Throws(CommandSyntaxException::class)
	fun onRemovePlayer( ctx: CommandContext<ServerCommandSource> ): Int {
		if ( ctx.source.player == null )
			throw CommandException( Text.literal( "This command can only be executed by players" ) )

		val id: String? = ZONE_COMPONENT_KEY.get( ctx.source.world ).removeZone( ctx.source.player as ServerPlayerEntity )
		ctx.source.sendFeedback( Text.literal( "Sucessfully removed zone \"$id\"" ), false )
		return 1
	}

	@Throws(CommandSyntaxException::class)
	private fun getFuncIdentifier(ctx: CommandContext<ServerCommandSource>, name: String): Identifier {
		return ArrayList(CommandFunctionArgumentType.getFunctions(ctx, name))[0].id
	}
}
