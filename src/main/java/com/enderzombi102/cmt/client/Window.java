package com.enderzombi102.cmt.client;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class Window {

	// FOR THE CUSTOM WINDOW TITLE ANC ICONS, LOOK AT THIS!
	// https://github.com/TheRandomLabs/RandomPatches/blob/1.16-fabric/src/main/java/com/therandomlabs/randompatches/mixin/client/MinecraftClientMixin.java

	public static final String DEFAULT_TITLE = "Minecraft " + SharedConstants.getGameVersion().getName();
	public static String windowTitle = DEFAULT_TITLE;

	public static void setTitle( String title ) {
		windowTitle = title;
		MinecraftClient.getInstance().getWindow().setTitle( title );
	}

	public static String getTitle() {
		return windowTitle;
	}

	public static void setIcon( String iconBasePath ) {
		var mcc = MinecraftClient.getInstance();
		var icon16 = new Identifier( iconBasePath + "16.png" );
		var icon32 = new Identifier( iconBasePath + "32.png" );
		mcc.getWindow().setIcon(
			() -> mcc.getResourceManager().getResource( icon16 ).orElseThrow().open(),
			() -> mcc.getResourceManager().getResource( icon32 ).orElseThrow().open()
		);
	}
}
