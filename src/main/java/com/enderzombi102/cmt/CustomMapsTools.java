package com.enderzombi102.cmt;

import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.network.ClientPacketReciver;
import com.enderzombi102.cmt.network.ServerPacketReciver;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
		CMTContent.register();
		ServerPacketReciver.register();
	}


	@Override
	public void onInitializeClient() {
		logger.info("Setting up window callbacks");
		ClientPacketReciver.register();
	}

	@Override
	public void onInitializeServer() {
		logger.info("Hello Fabric Server World!");
	}
}
