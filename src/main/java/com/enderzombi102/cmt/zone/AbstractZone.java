package com.enderzombi102.cmt.zone;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractZone<T extends Entity> {

	public Vec3d pos0;
	public Vec3d pos1;
	public final String id;
	public boolean deleteOnExit = false;
	protected ServerWorld world;
	protected final MinecraftServer server;
	private final Box cachedBox;
	private final List<T> lastEntities = new ArrayList<>();
	private final Class<T> entityClass;

	public AbstractZone(ServerWorld world, Vec3d pos0, Vec3d pos1, String id, Class<T> entityClass) {
		this.world = world;
		this.server = world.getServer();
		this.pos0 = pos0;
		this.pos1 = pos1;
		this.id = id;
		this.entityClass = entityClass;
		this.cachedBox = new Box(this.pos0, this.pos1);
	}

	// serialization
	public AbstractZone(ServerWorld world, CompoundTag tag, Class<T> entityClass) {
		this.world = world;
		this.server = world.getServer();
		this.id = tag.getString("id");
		this.pos0 = new Vec3d( tag.getDouble("x0"), tag.getDouble("y0"), tag.getDouble("z0") );
		this.pos1 = new Vec3d( tag.getDouble("x1"), tag.getDouble("y1"), tag.getDouble("z1") );
		this.entityClass = entityClass;
		this.cachedBox = new Box(this.pos0, this.pos1);
	}

	public void tick() {
		// get all players in the zone
		List<T> entities = this.world.getEntitiesByClass(
				this.entityClass,
				this.cachedBox,
				(entity) -> ( !entity.isSpectator() ) && entity.isAlive()
		);

		// check for entering players
		for (T entity : entities ) {
			if (! this.lastEntities.contains( entity ) ) {
				this.onEnter( entity );
				this.lastEntities.add( entity );
			}
		}

		// check for exiting players
		final ArrayList<T> toRemove = new ArrayList<>();
		for (T entity : this.lastEntities) {
			if (! entities.contains( entity ) ) {
				this.onLeave( entity );
				toRemove.add( entity );
			}
		}
		this.lastEntities.removeAll(toRemove);

		for (T entity : this.lastEntities) {
			this.onTick(entity);
		}
	}

	public Box getBox() {
		return new Box(this.pos0, this.pos1);
	}

	public List<T> getLastEntities() {
		return this.lastEntities;
	}

	public abstract void onEnter(T entity);
	public abstract void onLeave(T entity);
	public abstract void onTick(T entity);

	// serialization
	public void writeToNbt(CompoundTag tag) {
		tag.putString( "type", this.getClass().getSimpleName().toLowerCase() );
		tag.putString( "id", this.id );
		tag.putDouble( "x0", this.pos0.x );
		tag.putDouble( "y0", this.pos0.y );
		tag.putDouble( "z0", this.pos0.z );
		tag.putDouble( "x1", this.pos1.x );
		tag.putDouble( "y1", this.pos1.y );
		tag.putDouble( "z1", this.pos1.z );
	}
}
