package com.enderzombi102.cmt;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.proxy.ClientProxy;
import com.enderzombi102.cmt.proxy.CommonProxy;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

/** TODO: MinecraftClient.openScreen() calls updateTitle that gets the title from getWindowTitle
*	need to mixin in it and do an early return to give it MY title
 */




public class CustomMapsTools implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

	public static ModConfig config;
	public static CommonProxy proxy;

	@Override
	public void onInitialize() {
		LogHelper.info("setting up config page!");
//		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
//		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

	}


	@Override
	public void onInitializeClient() {
		LogHelper.info("Setting up window callbacks");
		proxy = new ClientProxy();
		KeyBindingHelper.registerEventHandler();
	}

	@Override
	public void onInitializeServer() {
		LogHelper.info("Hello Fabric Server World!");
		proxy = new CommonProxy();
	}
}
