package com.enderzombi102.cmt.zone;

import com.enderzombi102.cmt.CustomMapsTools;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.InvocationTargetException;

public class ZoneBuilder {

	private Class< ? extends AbstractZone<? extends Entity> > type;
	private Vec3d pos0, pos1;
	private String id;
	private Class<? extends Entity> clazz;
	private final ZoneActionData data = new ZoneActionData();
	private boolean deleteOnExit = false;

	public ZoneBuilder withEnterCommand(String cmd) {
		this.data.enterCommand = cmd;
		return this;
	}

	public ZoneBuilder withLeaveCommand(String cmd) {
		this.data.leaveCommand = cmd;
		return this;
	}

	public ZoneBuilder withType(Class< ? extends AbstractZone<? extends Entity> > type) {
		this.type = type;
		return this;
	}

	public ZoneBuilder withIdentifier(String id) {
		this.id = id;
		return this;
	}

	public ZoneBuilder deleteOnExit() {
		this.deleteOnExit = true;
		return this;
	}

	public ZoneBuilder withIdentifier(Identifier id) {
		return this.withIdentifier( id.toString() );
	}

	public ZoneBuilder withPosition(Vec3d pos0, Vec3d pos1) {
		this.pos0 = pos0;
		this.pos1 = pos1;
		return this;
	}

	public ZoneBuilder withEntityType(Class<? extends Entity> clazz) {
		this.clazz = clazz;
		return this;
	}

	public AbstractZone<? extends Entity> build() {
		AbstractZone<? extends Entity> zone;
		try {
			if (this.type != CommandZone.class) {
				zone = this.type
						.getDeclaredConstructor(ServerWorld.class, Vec3d.class, Vec3d.class, String.class, ZoneActionData.class, Class.class)
						.newInstance(null, this.pos0, this.pos1, this.id, this.data, this.clazz);
			} else {
				zone = this.type
						.getDeclaredConstructor(ServerWorld.class, Vec3d.class, Vec3d.class, String.class, ZoneActionData.class)
						.newInstance(null, this.pos0, this.pos1, this.id, this.data);

			}
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			CustomMapsTools.LOGGER.error("Failed to create zone!\n", e);
			return null;
		}
		zone.deleteOnExit = this.deleteOnExit;
		return zone;
	}

}
