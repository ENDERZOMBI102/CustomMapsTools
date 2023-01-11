package com.enderzombi102.cmt.mixins.client;

import net.minecraft.client.gui.widget.option.KeyBindListWidget;
import net.minecraft.client.option.KeyBind;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin( KeyBindListWidget.KeyBindEntry.class )
public interface KeyBindingEntryAccessor {
	@Invoker("<init>")
	static KeyBindListWidget.KeyBindEntry invokeInit( KeyBindListWidget outerThis, KeyBind binding, Text text ) {
		throw new IllegalStateException( "KeyBindingEntryAccessor::<init>()" );
	}
}