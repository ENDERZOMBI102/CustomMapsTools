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
