package com.enderzombi102.cmt;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import com.enderzombi102.cmt.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** TODO: MinecraftClient.openScreen() calls updateTitle that gets the title from getWindowTitle
*	need to mixin in it and do an early return to give it MY title
 */




public class CustomMapsTools implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

	public static Logger logger = LogManager.getLogger("CustomMapsTools");

	@Override
	public void onInitialize() {
		logger.info("setting up config page!");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}


	@Override
	public void onInitializeClient() {
		logger.info("Setting up window callbacks");
		// until its fixed, disable this "module"
		KeyBindingHelper.registerEventHandler();
	}

	@Override
	public void onInitializeServer() {
		logger.info("Hello Fabric Server World!");
	}
}
