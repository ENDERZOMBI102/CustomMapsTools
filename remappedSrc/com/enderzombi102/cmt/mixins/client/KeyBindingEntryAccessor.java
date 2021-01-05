package com.enderzombi102.cmt.mixins.client;

import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ControlsListWidget.KeyBindingEntry.class)
public interface KeyBindingEntryAccessor {
	@Invoker("<init>")
	static ControlsListWidget.KeyBindingEntry invokeInit(ControlsListWidget parent, KeyBinding binding, Text text) {
		throw new AssertionError("This shouldn't happen!");
	}
}