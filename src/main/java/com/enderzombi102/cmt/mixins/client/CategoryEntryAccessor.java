package com.enderzombi102.cmt.mixins.client;

import net.minecraft.client.gui.widget.option.KeyBindListWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin( KeyBindListWidget.CategoryEntry.class)
public interface CategoryEntryAccessor {
	@Invoker("<init>")
	static KeyBindListWidget.CategoryEntry invokeInit( KeyBindListWidget outerThis, MutableText title ) {
		throw new IllegalStateException( "CategoryEntryAccessor::<init>" );
	}

	@Accessor("title")
	Text getCategory();
}
