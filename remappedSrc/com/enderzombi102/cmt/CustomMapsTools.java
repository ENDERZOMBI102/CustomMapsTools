package com.enderzombi102.cmt;

import com.enderzombi102.cmt.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** TODO: MinecraftClient.openScreen() calls updateTitle that gets the title from getWindowTitle
 *	need to mixin in it and do an early return to give it MY title
 */

// ---- this code gets the content of a gamerule ----
// MinecraftServer server = client.getServer();
// GameRules.Rule<?> rule = server.getGameRules().get(key);
// logger.info( rule.toString() );

public class CustomMapsTools implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

	public static Logger logger = LogManager.getLogger("CustomMapsTools");

	@Override
	public void onInitialize() {
		logger.info("setting up config page!");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		CMTContent.register();

	}


	@Override
	public void onInitializeClient() {
		logger.info("Setting up window callbacks");
	}

	@Override
	public void onInitializeServer() {
		logger.info("Hello Fabric Server World!");
	}
}
