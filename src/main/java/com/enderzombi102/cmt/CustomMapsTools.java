package com.enderzombi102.cmt;

import com.enderzombi102.cmt.command.OpenGuiCommand;
import com.enderzombi102.cmt.command.ZoneCommand;
import com.enderzombi102.cmt.zone.ZoneManager;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomMapsTools implements ModInitializer, WorldComponentInitializer {

	public static final Logger LOGGER = LogManager.getLogger("CustomMapsTools");
	public static final ItemGroup CMT_GROUP = FabricItemGroupBuilder.build(
			ID("item_group"),
			() -> new ItemStack(CMTContent.invLightItem)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("setting up config page!");
		CMTContent.register();
		CommandRegistrationCallback.EVENT.register( ( dispatcher, dedicated ) -> {
			LOGGER.info("Registering commands!");
			ZoneCommand.register(dispatcher);
			OpenGuiCommand.register(dispatcher);
		});
	}

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
		registry.register(CMTContent.ZONE_COMP_KEY, ZoneManager::new);
	}

	public static Identifier ID(String path) {
		return new Identifier("cmt", path);
	}
}
