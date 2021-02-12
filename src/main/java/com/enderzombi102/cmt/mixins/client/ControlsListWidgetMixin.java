package com.enderzombi102.cmt.mixins.client;

import com.enderzombi102.cmt.client.keybind.KeyBind;
import com.enderzombi102.cmt.client.keybind.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ControlsListWidget.class)
@SuppressWarnings("rawtypes")
public abstract class ControlsListWidgetMixin extends ElementListWidget {

	public ControlsListWidgetMixin(MinecraftClient client, int width, int height, int top, int bottom, int itemHeight) {
		super(client, width, height, top, bottom, itemHeight);
	}

	/**
	 * SCOPE OF ALL KEYBOARD-RELATED STUFF
	 * implement hot-loading keybindings
	 * all left to do is somehow show the binding in the correct category in the controls options screen
	 */

	@SuppressWarnings("unchecked")
	@Inject(at = @At("TAIL"), method = "<init>")
	public void constructor(ControlsOptionsScreen parent, MinecraftClient client, CallbackInfo info) {
		String lastCategory = "";
		for ( KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
			// if the keybind uses a default category, we need to find that category, and add the keybind before the next one
			if ( bind.isUsingDefaultCategories() ) {
				boolean catFound = false;
				for ( Object rawentry : this.children() ) {
					EntryListWidget.Entry entry = (EntryListWidget.Entry) rawentry;
					// ignore non-category entries
					if (! (entry instanceof ControlsListWidget.CategoryEntry) ) continue;
					if ( ( (TranslatableText) ( (CategoryEntryAccessor) entry ).getCategory() ).getKey().equals( bind.getCategory() ) ) {
						catFound = true;
					} else if (catFound) {
						this.children().add(
							this.children().indexOf(entry),
							bind.getEntry( (ControlsListWidget)(Object) this)
						);
						break;
					}
				}
			} else {
				// create new category and add the keybind
				if (! bind.getCategory().equals(lastCategory) ) {
					this.addEntry( CategoryEntryAccessor.invokeInit( (ControlsListWidget)(Object) this, new TranslatableText( bind.getCategory() ) ) );
					lastCategory =bind.getCategory();
				}
				this.addEntry( bind.getEntry( (ControlsListWidget)(Object) this) );
			}

		}

	}


}
