package com.enderzombi102.cmt.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.TranslatableText;

public class HotControlsOptionsScreen extends GameOptionsScreen {

	public HotControlsOptionsScreen(Screen parent, GameOptions gameOptions) {
		super(parent, gameOptions, new TranslatableText("cmt.gui.hotreloadeablectrls.title") );
	}

}
