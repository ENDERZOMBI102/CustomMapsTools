package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.CustomMapsTools;
import com.enderzombi102.cmt.Utils;
import com.enderzombi102.cmt.network.packets.PacketsIdentifiers;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;

public class ClientTweaker {

	private static InputStream icon16 = null, icon32 = null;
	private static InputStream defIcon16 = null, defIcon32 = null;

	public final ClientTweaker instance;

	public ClientTweaker() {
		instance = this;
		try {
			defIcon16 = Utils.mcinstance().getResourcePackDownloader().getPack().open(
					ResourceType.CLIENT_RESOURCES, new Identifier("icons/icon_16x16.png")
			);
			defIcon32 = Utils.mcinstance().getResourcePackDownloader().getPack().open(
					ResourceType.CLIENT_RESOURCES, new Identifier("icons/icon_32x32.png")
			);
		} catch (IOException e) {
			CustomMapsTools.logger.warn("Can't get default icons! how is that possible?");
		}

		ClientSidePacketRegistry.INSTANCE.register( PacketsIdentifiers.WORLD_SETTINGS_PACKET_ID,
				(packetContext, attachedData) -> {
					packetContext.getTaskQueue().execute( () -> {
								attachedData.array();

							}

					);
				}
		);
	}


	public static void updateTitle() {
		Utils.mcinstance().getWindow().setTitle("");
	}


	public static void updateIcon() {


	}


}
