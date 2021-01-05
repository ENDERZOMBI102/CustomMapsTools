package com.enderzombi102.cmt.client;

import lombok.SneakyThrows;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.io.IOException;

public class Window {

	// FOR THE CUSTOM WINDOW TITLE ANC ICONS, LOOK AT THIS!
	// https://github.com/TheRandomLabs/RandomPatches/blob/1.16-fabric/src/main/java/com/therandomlabs/randompatches/mixin/client/MinecraftClientMixin.java

	public static final String DEFAULT_TITLE = "Minecraft " + SharedConstants.getGameVersion().getName();
	public static String windowTitle = DEFAULT_TITLE;

	public static void setTitle(String title) {
		windowTitle = title;
		MinecraftClient.getInstance().getWindow().setTitle(title);
	}

	public static String getTitle() {
		return windowTitle;
	}

	public static void setIcon(String iconBasePath) {
		final MinecraftClient mcc = MinecraftClient.getInstance();
		Identifier icon16 = new Identifier(iconBasePath + "16.png");
		Identifier icon32 = new Identifier(iconBasePath + "32.png");
		try {
			mcc.getWindow().setIcon(
					mcc.getResourceManager().getResource(icon16).getInputStream(),
					mcc.getResourceManager().getResource(icon32).getInputStream()
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
