package com.enderzombi102.cmt.zone;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class ZoneManager implements ZoneComponent {

	private final ServerWorld world;
	private final ArrayList< AbstractZone<? extends Entity> > zones = new ArrayList<>();

	public ZoneManager(World world) {
		if (world instanceof ServerWorld) this.world = (ServerWorld) world;
		else this.world = null;
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		ListTag list = new ListTag();

		for (int i = 0; i < this.zones.size(); i++ ) {
			if ( this.zones.get(i).deleteOnExit ) continue;
			CompoundTag compoundTag = new CompoundTag();
			this.zones.get(i).writeToNbt(compoundTag);
			list.addTag(i, compoundTag);
		}

		tag.put("zones", list);
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		this.zones.clear();
		for ( Tag ctag : ( (ListTag) tag.get("zones") ).toArray( new Tag[0] ) ) {

			CompoundTag compoundTag = (CompoundTag) ctag;
			AbstractZone<? extends Entity> zone;

			switch ( compoundTag.getString("type") ) {
				case "FunctionZone":
					zone = new FunctionZone(this.world, compoundTag);
					break;
				default:  // this includes the "CommandZone" class/zone
					zone = new CommandZone(this.world, compoundTag);
			}

			this.zones.add( zone );
		}
	}

	@Override
	public void serverTick() {
		for ( AbstractZone<? extends Entity> zone : this.zones) {
			zone.tick();
		}
	}

	// ZoneComponent-specific methods

	@Override
	public void addZone(AbstractZone<? extends Entity> zone) {
		zone.world = this.world;
		if (this.world != null) this.zones.add(zone);
	}

	@Override
	public String removeZone(PlayerEntity entity) {
		for (AbstractZone<? extends Entity> zone : this.zones) {
			if( zone.getBox().intersects( entity.getBoundingBox() ) ) {
				String buf = zone.id;
				this.zones.remove( zone );
				return buf;
			}
		}
		return null;
	}

	@Override
	public void removeZone(String id) {
		this.zones.remove( this.getZone(id) );
	}

	@Override
	public boolean containsZone(String id) {
		return this.zones.stream().anyMatch(zone -> zone.id.equals(id) );
	}

	@Override
	public boolean entityInZone(List<Entity> entities, String id) {
		AbstractZone<? extends Entity> zone = this.getZone(id);
		if ( zone == null ) return false;
		for (Entity ent : entities ) {
			if ( zone.getLastEntities().contains(ent) ) return true;
		}
		return false;
	}

	@Override
	public @Nullable AbstractZone<? extends Entity> getZone(String id) {
		return this.zones.stream().filter( zone -> zone.id.equals(id) ).findAny().orElse(null);
	}

	@Override
	public List< AbstractZone<? extends Entity> > getZones() {
		return this.zones;
	}

	@Override
	public boolean isIdValid(String id) {
		return ! this.containsZone(id);
	}

	@Override
	public String getRandomId() {
		String id;
		do {
			id = RandomStringUtils.random(8);
		} while ( this.isIdValid(id) );
		return id;
	}

}
