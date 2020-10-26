package com.enderzombi102.cmt.mixins;

import com.enderzombi102.cmt.client.KeyBindingHelper;
import lombok.SneakyThrows;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ControlsListWidget.class)
public abstract class ControlsListWidgetMixin {

	@SneakyThrows
	@Inject(at = @At("TAIL"), method = "<init>")
	public void constructor(ControlsOptionsScreen parent, MinecraftClient client, CallbackInfo info) {
		for ( KeyBindingHelper.KeyBind bind : KeyBindingHelper.getKeyCallbacks() ) {
			( (KeyBindingHelper.addEntryInterface) this).addEntry( KeyBindingEntryAccessor.invokeInit( bind.getKeyBinding(), bind.getTranslationTextT() ) );
		}

	}

//	@Mixin(ControlsListWidget.KeyBindingEntry.class)
//	public interface KeyBindingEntryAccessor {
//		@Invoker("<init>")
//		static ControlsListWidget.KeyBindingEntry invokeInit(final KeyBinding binding, final Text text) {
//			throw null;
//		}
//	}

}
