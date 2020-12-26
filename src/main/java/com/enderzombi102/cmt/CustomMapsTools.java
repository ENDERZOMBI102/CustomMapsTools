package com.enderzombi102.cmt;

import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.gamerule.TextGamerule;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
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
	public static GameRules.Key key = GameRuleRegistry.register( "test", GameRules.Category.MISC, TextGamerule.create("TEXT") );

	@Override
	public void onInitialize() {
		logger.info("setting up config page!");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
	}


	@Override
	@SuppressWarnings("unchecked")
	public void onInitializeClient() {
		logger.info("Setting up window callbacks");
	}

	@Override
	public void onInitializeServer() {
		logger.info("Hello Fabric Server World!");
	}
}
