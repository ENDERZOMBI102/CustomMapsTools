package com.enderzombi102.cmt;

import com.enderzombi102.cmt.command.OpenGuiCommand;
import com.enderzombi102.cmt.command.ZoneCommand;
import com.enderzombi102.cmt.config.ModConfig;
import com.enderzombi102.cmt.gui.CustomGuiManager;
import com.enderzombi102.cmt.keybind.KeybindManager;
import com.enderzombi102.cmt.network.NetworkingConstants;
import com.enderzombi102.cmt.zone.ZoneManager;
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("UnstableApiUsage")
public class CustomMapsTools implements ModInitializer, ClientModInitializer, WorldComponentInitializer, LevelComponentInitializer {

	public static final Logger LOGGER = LogManager.getLogger("CustomMapsTools");
	public static final ItemGroup CMT_GROUP = FabricItemGroupBuilder.build(
			new Identifier("cmt:item_group"),
			() -> new ItemStack(CMTContent.invLightItem)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("setting up config page!");
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
		CMTContent.register();
		CommandRegistrationCallback.EVENT.register( ( dispatcher, dedicated ) -> {
			LOGGER.info("Registering commands!");
			ZoneCommand.register(dispatcher);
			OpenGuiCommand.register(dispatcher);
		});
	}

	@Override
	public void onInitializeClient() {
		CMTContent.registerClientThings();

		ClientPlayNetworking.registerGlobalReceiver(NetworkingConstants.CUSTOM_GUI_PACKET_ID, CustomGuiManager::onOpenScreen );
	}

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
		registry.register(CMTContent.ZONE_COMP_KEY, ZoneManager::new);
	}

	@Override
	public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
		registry.register(CMTContent.BIND_COMP_KEY, KeybindManager::new);
	}
}
