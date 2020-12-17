package com.enderzombi102.cmt.mixins;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import lombok.SneakyThrows;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Surrogate;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

@Mixin(ControlsListWidget.class)
public abstract class ControlsListWidgetMixin {

	/**
	 * SCOPE OF ALL KEYBOARD-RELATED STUFF
	 * implement hot-loading keybindings
	 * all left to do is somwhow show the binding in the controls options screen
	 */

	@Surrogate
	public abstract <E> int addEntry(E entry);

	@SneakyThrows
	@Inject(at = @At("TAIL"), method = "<init>")
	public void constructor(ControlsOptionsScreen parent, MinecraftClient client, CallbackInfo info) {
		for ( KeyBindingHelper.KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
			if (bind.getEntry() != null) this.addEntry( bind.getEntry() );
		}

	}


}
