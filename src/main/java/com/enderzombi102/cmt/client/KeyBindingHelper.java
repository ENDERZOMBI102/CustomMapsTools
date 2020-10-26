package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.LogHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.ArrayList;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class KeyBindingHelper {

	@Getter
	private static final ArrayList< KeyBind > keyCallbacks = new ArrayList<>();

	public static KeyBind makeKeybind(int key, Consumer<MinecraftClient> callback, String transText, String category ) {
		KeyBind bind = new KeyBind(key, callback);
		keyCallbacks.add( bind );
		return bind;
	}

	public static boolean removeKeybind(KeyBind keyBind) {
		return keyCallbacks.remove( keyBind );
	}

	public static KeyBind getKeybindPair(Consumer<MinecraftClient> func) {
		for (KeyBind bind : keyCallbacks ) {
			if (bind.getCallback() == func) return bind;
		}
		return null;
	}

	public static ArrayList<KeyBind> getKeybinds(int key) {
		ArrayList<KeyBind> toReturn = new ArrayList<>();
		for (KeyBind bind : keyCallbacks ) {
			if ( bind.getKey() == key ) toReturn.add(bind);
		}
		return toReturn;
	}

	public static void registerEventHandler() {
		ClientTickEvents.END_CLIENT_TICK.register(
				client -> {
					// check if we have a key pressed that is handled by us
					if (keyCallbacks.size() == 1) {

					}
				}
		);

		makeKeybind(
				GLFW.GLFW_KEY_K,
					client -> {
						LogHelper.info("WORKS!");
					},
			"",
			""
				);



	}

	public static class KeyBind {

		@Getter
		private final Consumer<MinecraftClient> callback;
		@Setter
		@Getter
		private int key;
		@Getter
		@Setter
		private String translationText;
		@Getter
		@Setter
		private String category;

		public KeyBind( int key, Consumer<MinecraftClient> callback ) {
			this.callback = callback;
			this.key = key;
		}

		public KeyBinding getKeyBinding() {
			return new KeyBindAdapter(this);
		}

		public Text getTranslationTextT() {
			return new TranslatableText(this.translationText);
		}
	}

	public static class KeyBindAdapter extends KeyBinding {

		KeyBind bind;

		public KeyBindAdapter(KeyBind bind) {
			super(bind.translationText, bind.key, bind.category);
			this.bind = bind;
		}

		@Override
		public void setBoundKey(InputUtil.Key boundKey) {
			this.bind.setKey( boundKey.getCode() );
			super.setBoundKey(boundKey);
		}
	}

	public interface addEntryInterface {
		<E> int addEntry(E entry);
	}

//	public static class NewKeyBindingEntry extends ControlsListWidget.KeyBindingEntry {
//
//		protected final KeyBinding binding;
//		protected final Text bindingName;
//		protected final ButtonWidget editButton;
//		protected final ButtonWidget resetButton;
//
//		public NewKeyBindingEntry(final KeyBind bind) {
//			super();
//			this.binding = bind.getKeyBinding();
//			this.bindingName = bind.getTranslationTextT();
//			this.editButton = new ButtonWidget(0, 0, 75, 20, bind.getTranslationTextT(), (buttonWidget) -> {
//				ControlsOptionsScreen.this.parent.focusedBinding = binding;
//			}) {
//				protected MutableText getNarrationMessage() {
//					return binding.isUnbound() ? new TranslatableText("narrator.controls.unbound", new Object[]{text}) : new TranslatableText("narrator.controls.bound", new Object[]{text, super.getNarrationMessage()});
//				}
//			};
//			this.resetButton = new ButtonWidget(0, 0, 50, 20, new TranslatableText("controls.reset"), (buttonWidget) -> {
//				this.client.options.setKeyCode(binding, binding.getDefaultKey());
//				KeyBinding.updateKeysByCode();
//			}) {
//				protected MutableText getNarrationMessage() {
//					return new TranslatableText("narrator.controls.reset", bind.getTranslationTextT() );
//				}
//			};
//		}
//
//	}

}
