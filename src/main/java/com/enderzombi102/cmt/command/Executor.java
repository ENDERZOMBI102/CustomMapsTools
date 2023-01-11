package com.enderzombi102.cmt.command;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Executor {
	private final @NotNull MinecraftServer server;
	private final @Nullable String command;
	private final @Nullable CommandFunction function;

	public Executor( @NotNull MinecraftServer server, @NotNull String executes ) {
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
				.withWorld( (ServerWorld) entity.getCommandSenderWorld() )
				.withPosition( entity.getPos() )
				.withRotation( entity.getRotationClient() );
	}

}
