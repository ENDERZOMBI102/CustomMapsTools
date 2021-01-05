package com.enderzombi102.cmt.config;

import com.enderzombi102.cmt.config.ModConfig.InnerStuff;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;


@Config.Gui.Background("cloth-config2:transparent")
@Config(name="CustomMapsTools")
public class ModConfig implements ConfigData {

	boolean toggleA = true;
	boolean toggleB = false;

	@ConfigEntry.Gui.Excluded
	InnerStuff invisibleStuff = new InnerStuff();

	static class InnerStuff {
		int a = 0;
		int b = 1;
	}
}