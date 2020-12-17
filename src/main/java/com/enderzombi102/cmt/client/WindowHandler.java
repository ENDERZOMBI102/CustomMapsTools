package com.enderzombi102.cmt.client;

import net.minecraft.client.MinecraftClient;

public class WindowHandler {

	/**
	 * DO NOT USE, THIS IS FOR IMPLEMENTATION
	 */
	public static String windowTitle;

	public static void setTitle(String title) {
		MinecraftClient.getInstance().getWindow().setTitle(title);
	}

	public static String getTitle() {
		return windowTitle;
	}




}
