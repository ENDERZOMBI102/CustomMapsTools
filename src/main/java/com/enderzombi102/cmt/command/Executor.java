package com.enderzombi102.cmt.command;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class Executor {

	private final MinecraftServer server;
	private final String command;
	private final CommandFunction function;

	public Executor(MinecraftServer server, String executes) {
		this.server = server;
		Optional<CommandFunction> func = this.server.getCommandFunctionManager().getFunction( new Identifier(executes) );
		if ( func.isPresent() ) {
			this.function = func.get();
			this.command = null;
		} else {
			this.function = null;
			this.command = executes;
		}
	}

	public void Execute(Entity entity) {
		if ( this.function != null ) {
			this.server.getCommandFunctionManager().execute(
					this.function,
					this.getSource(entity)
			);
		} else {
			this.server.getCommandManager().execute(
				this.getSource(entity),
				this.command
			);
		}
	}

	private ServerCommandSource getSource(Entity entity) {
		return this.server.getCommandSource()
				.withEntity(entity)
				.withWorld( (ServerWorld) entity.getEntityWorld() )
				.withPosition( entity.getPos() )
				.withRotation( entity.getRotationClient() );
	}

}
