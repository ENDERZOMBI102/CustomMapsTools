package com.enderzombi102.cmt.zone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;

public class FunctionZone extends AbstractZone<PlayerEntity> {

	public final Identifier enterFunction;
	public final Identifier leaveFunction;
	public final Identifier midFunction;
	private CommandFunction enterFunc;
	private CommandFunction midFunc;
	private CommandFunction leaveFunc;

	public FunctionZone(ServerWorld world, Vec3d pos0, Vec3d pos1, String id, ZoneActionData data) {
		super(world, pos0, pos1, id, PlayerEntity.class );
		this.enterFunction = data.enterFunction;
		this.leaveFunction = data.leaveFunction;
		this.midFunction = data.midFunction;
		this.loadFunc();
	}

	// serialization
	public FunctionZone(ServerWorld world, NbtCompound tag) {
		super(world, tag, PlayerEntity.class);
		this.enterFunction = new Identifier( tag.getString("efunc") );
		this.midFunction = new Identifier( tag.getString("mfunc") );
		this.leaveFunction = new Identifier( tag.getString("lfunc") );
		this.loadFunc();
	}

	private void loadFunc() {
		// get possible function objects
		final Optional<CommandFunction> enter = this.server.getCommandFunctionManager().getFunction(this.enterFunction);
		final Optional<CommandFunction> stay = this.server.getCommandFunctionManager().getFunction(this.midFunction);
		final Optional<CommandFunction> leave = this.server.getCommandFunctionManager().getFunction(this.leaveFunction);

		// check if we found all objects + log if not
		if ( enter.isPresent() ) this.enterFunc = enter.get();
		else LOGGER.warn("[CMT] function <" + this.enterFunction + "> was not found");
		if ( stay.isPresent() ) this.midFunc = stay.get();
		else LOGGER.warn("[CMT] function <" + this.midFunction + "> was not found");
		if ( leave.isPresent() ) this.leaveFunc = leave.get();
		else LOGGER.warn("[CMT] function <" + this.leaveFunction + "> was not found");
	}

	@Override
	public void onEnter(PlayerEntity entity) {
		this.server.getCommandFunctionManager().execute(
				this.enterFunc,
				this.server.getCommandSource().withEntity(entity).withWorld(this.world)
		);
	}

	@Override
	public void onLeave(PlayerEntity entity) {
		this.server.getCommandFunctionManager().execute(
				this.leaveFunc,
				this.server.getCommandSource().withEntity(entity).withWorld(this.world)
		);
	}

	@Override
	public void onTick(PlayerEntity entity) {
		this.server.getCommandFunctionManager().execute(
				this.midFunc,
				this.server.getCommandSource().withEntity(entity).withWorld(this.world)
		);
	}

	// serialization
	@Override
	public void writeToNbt(NbtCompound tag) {
		super.writeToNbt(tag);
		tag.putString( "efunc", this.enterFunction.toString() );
		tag.putString( "mfunc", this.midFunction.toString() );
		tag.putString( "lfunc", this.leaveFunction.toString() );
	}
}