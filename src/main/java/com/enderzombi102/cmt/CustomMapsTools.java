package com.enderzombi102.cmt;

import com.enderzombi102.cmt.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public class CustomMapsTools implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {

	public static ModConfig config;


	@Override
	public void onInitialize() {
		System.out.println("Hello Fabric World!");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}


	@Override
	public void onInitializeClient() {
		System.out.println("Hello Fabric Client World!");
	}

	@Override
	public void onInitializeServer() {
		System.out.println("Hello Fabric Server World!");
	}
}
