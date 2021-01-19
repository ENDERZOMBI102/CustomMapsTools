package com.enderzombi102.cmt.zone;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public interface ZoneComponent extends ComponentV3, ServerTickingComponent {

	void addZone(AbstractZone<? extends Entity> zone);
	void removeZone(PlayerEntity entity);
	void removeZone(String id);
	boolean containZoneWithIdentifier(String id);
	boolean playerInZone(List<PlayerEntity> entities, String id);
	AbstractZone<? extends Entity> getZone(String id);
	List< AbstractZone<? extends Entity> > getZones();
}
