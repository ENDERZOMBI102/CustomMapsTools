package com.enderzombi102.cmt;

import com.enderzombi102.cmt.command.ZoneCommand;
import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.zone.ZoneManager;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static net.minecraft.server.command.CommandManager.*;

public class CustomMapsTools implements ModInitializer, ClientModInitializer, WorldComponentInitializer {

	public static final Logger LOGGER = LogManager.getLogger("CustomMapsTools");


	@Override
	public void onInitialize() {
		LOGGER.info("setting up config page!");
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
		CMTContent.register();
		CommandRegistrationCallback.EVENT.register( ( dispatcher, dedicated ) -> {
			LOGGER.info("Registering commands!");
			ZoneCommand.register(dispatcher);
		});
	}

	@Override
	public void onInitializeClient() {
		CMTContent.registerClientThings();
	}

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
		registry.register(CMTContent.ZONE_COMP_KEY, ZoneManager::new);
	}
}
