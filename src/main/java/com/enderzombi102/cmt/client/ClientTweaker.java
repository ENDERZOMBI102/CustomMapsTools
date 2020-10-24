package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.LogHelper;
import com.enderzombi102.cmt.Utils;
import com.enderzombi102.cmt.packets.PacketsIdentifiers;
import me.shedaniel.clothconfig2.impl.KeyBindingHooks;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.java.games.input.Component;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

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
			LogHelper.warn("Can't get default icons! how is that possible?");
		}

		ClientSidePacketRegistry.INSTANCE.register( PacketsIdentifiers.WORLD_SETTINGS_PACKET_ID,
				(packetContext, attachedData) -> {
					packetContext.getTaskQueue().execute( () -> {
								attachedData.array();

							}

					);
				}
		);






		// updateIcon();
		// updateTitle();
	}


	public static void updateTitle() {
		Utils.mcinstance().getWindow().setTitle("");
	}


	public static void updateIcon() {


	}

	public static void removeKeyBind(KeyBinding key) {

	}

	public static KeyBinding addKeyBind(String name, String category, Character defKey) {


		return new KeyBinding("", 9, "");
	}


}
