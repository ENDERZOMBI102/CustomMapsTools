package com.enderzombi102.cmt.mixins.client;

import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ControlsListWidget.CategoryEntry.class)
public interface CategoryEntryAccessor {
	@Invoker("<init>")
	static ControlsListWidget.CategoryEntry invokeInit(ControlsListWidget parent, Text text) {
		throw new AssertionError("This shouldn't happen!");
	}

	@Accessor("text")
	Text getCategory();
}
