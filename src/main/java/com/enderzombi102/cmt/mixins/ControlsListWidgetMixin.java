package com.enderzombi102.cmt.mixins;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import lombok.SneakyThrows;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ControlsListWidget.class)
public abstract class ControlsListWidgetMixin extends EntryListWidget {

	public ControlsListWidgetMixin(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	/**
	 * SCOPE OF ALL KEYBOARD-RELATED STUFF
	 * implement hot-loading keybindings
	 * all left to do is somehow show the binding in the controls options screen
	 */

	@SuppressWarnings("unchecked")
	@Inject(at = @At("TAIL"), method = "<init>")
	public void constructor(ControlsOptionsScreen parent, MinecraftClient client, CallbackInfo info) {
		for ( KeyBindingHelper.KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
			if ( bind.getEntry( (ControlsListWidget)(Object) this ) != null) {
				this.addEntry( bind.getEntry( (ControlsListWidget)(Object) this) );
			}
		}

	}


}
