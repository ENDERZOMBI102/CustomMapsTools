package com.enderzombi102.cmt.zone;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ZoneComponent extends ComponentV3, ServerTickingComponent {

	void addZone(AbstractZone<? extends Entity> zone);
	String removeZone(PlayerEntity entity);
	void removeZone(String id);
	boolean containsZone(String id);
	boolean entityInZone(List<Entity> entities, String id);
	@Nullable AbstractZone<? extends Entity> getZone(String id);
	List< AbstractZone<? extends Entity> > getZones();
	boolean isIdValid(String id);
	String getRandomId();
}
