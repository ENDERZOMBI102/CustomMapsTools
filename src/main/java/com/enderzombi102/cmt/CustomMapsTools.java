package com.enderzombi102.cmt;

import com.enderzombi102.cmt.client.ClientTweaker;
import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.packets.PacketsIdentifiers;
import com.enderzombi102.cmt.server.ServerTweaker;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

/** TODO: MinecraftClient.openScreen() calls updateTitle that gets the title from getWindowTitle
*	need to mixin in it and do an early return to give it MY title
 */




public class CustomMapsTools implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

	public static ModConfig config;

	@Override
	public void onInitialize() {
		LogHelper.info("setting up config page!");
//		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
//		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


	}


	@Override
	public void onInitializeClient() {
		LogHelper.info("Setting up window callbacks");
		new ClientTweaker();

	}

	@Override
	public void onInitializeServer() {
		LogHelper.info("Hello Fabric Server World!");
		new ServerTweaker();
	}
}
