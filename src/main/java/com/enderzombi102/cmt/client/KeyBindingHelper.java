package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.CustomMapsTools;
import com.enderzombi102.cmt.mixins.KeyBindingEntryAccessor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Consumer;

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

	public static @Nullable KeyBind getKeybindPair(Consumer<MinecraftClient> func) {
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
		makeKeybind(
				Key.GLFW_KEY_K,
				client -> CustomMapsTools.logger.info("WORKS!"),
				"text.invalid.translation",
				"category.custom.title"
		);
	}

	public static class KeyBind {

		@Setter
		@Getter
		private int key;
		@Getter
		@Setter
		private String translationText;
		@Getter
		@Setter
		private String category;
		@Getter
		private final Consumer<MinecraftClient> callback;
		@Getter
		private final KeyBindAdapter adapter = new KeyBindAdapter(this);
		private ControlsListWidget.KeyBindingEntry entry = null;

		public KeyBind( int key, Consumer<MinecraftClient> callback ) {
			this(key, callback, "placeholder", "placeholder");
		}

		public KeyBind( int key, Consumer<MinecraftClient> callback, String transText, String category ) {
			this.callback = callback;
			this.key = key;
			this.translationText = transText;
			this.category = category;
		}

		public ControlsListWidget.KeyBindingEntry getEntry() {
			if ( this.entry == null ) {
				this.entry = KeyBindingEntryAccessor.invokeInit( this.adapter, this.getTranslationTextT() );
			}
			return this.entry;
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

}
