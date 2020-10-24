package com.enderzombi102.cmt.config;

import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;


@Config.Gui.Background("cloth-config2:transparent")
@Config(name="CustomMapsTools")
public class ModConfig extends PartitioningSerializer.GlobalData {
	boolean toggleA = true;
	boolean toggleB = false;

	@ConfigEntry.Gui.Excluded
	InnerStuff invisibleStuff = new InnerStuff();

	static class InnerStuff {
		int a = 0;
		int b = 1;
	}
}