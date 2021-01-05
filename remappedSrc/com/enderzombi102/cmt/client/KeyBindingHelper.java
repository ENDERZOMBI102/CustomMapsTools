package com.enderzombi102.cmt.client;

import com.enderzombi102.cmt.mixins.client.KeyBindingEntryAccessor;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.options.ControlsListWidget;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class KeyBindingHelper {

	private static final ArrayList< KeyBind > keyCallbacks = new ArrayList<>();
	private static final ArrayList<String> defaultCategories = new ArrayList<>();

	public static ArrayList<KeyBind> getKeyCallbacks() {
		return keyCallbacks;
	}

	public static KeyBind makeKeybind(int key, Consumer<MinecraftClient> callback, String transText, String category, Boolean requiresInGame, Boolean requiresInteracting ) {
		KeyBind bind = new KeyBind(key, callback, transText, category, requiresInGame, requiresInteracting );
		keyCallbacks.add( bind );
		return bind;
	}

	public static boolean removeKeybind(KeyBind keyBind) {
		return keyCallbacks.remove( keyBind );
	}

	public static @Nullable KeyBind getKeybind(Consumer<MinecraftClient> func) {
		for (KeyBind bind : keyCallbacks ) {
			if (bind.callback == func) return bind;
		}
		return null;
	}

	public static ArrayList<KeyBind> getKeybindsWithKey(int key) {
		ArrayList<KeyBind> toReturn = new ArrayList<>();
		for (KeyBind bind : keyCallbacks ) {
			if ( bind.key == key ) toReturn.add(bind);
		}
		return toReturn;
	}

	public static class KeyBind extends KeyBinding {

		private int key;
		private final int defaultKey;
		private final TranslatableText translationText;
		public final String category;
		private final Consumer<MinecraftClient> callback;
		public final boolean requiresInGame;
		public final boolean requiresInteracting;

		public KeyBind( int key, Consumer<MinecraftClient> callback ) {
			this(key, callback, "placeholder", "placeholder", true, true);
		}

		public KeyBind( int key, Consumer<MinecraftClient> callback, String transText, String category, boolean requiresInGame, boolean requiresInteracting ) {
			super(transText, key, category);
			this.callback = callback;
			this.key = key;
			this.defaultKey = key;
			this.translationText = new TranslatableText( transText );
			this.category = category;
			this.requiresInGame = requiresInGame;
			this.requiresInteracting = requiresInteracting;

			// taken from FAPI
			Map<String, Integer> map = KeyBindingAccessor.fabric_getCategoryMap();

			if (! KeyBindingAccessor.fabric_getCategoryMap().containsKey(category) ) {

				Optional<Integer> largest = map.values().stream().max(Integer::compareTo);
				int largestInt = largest.orElse(0);
				map.put(category, largestInt + 1);
			}
		}

		public ControlsListWidget.KeyBindingEntry getEntry( ControlsListWidget parent ) {
			return KeyBindingEntryAccessor.invokeInit( parent, this, this.translationText );
		}

		public Text getTranslationText() {
			return this.translationText;
		}

		public int getKey() {
			return key;
		}

		public Consumer<MinecraftClient> getCallback() {
			return callback;
		}

		public boolean useDefaultCategories() {
			return defaultCategories.contains( this.category );
		}

		@Override
		public void setBoundKey(InputUtil.Key boundKey) {
			this.key = boundKey.getCode();
			super.setBoundKey(boundKey);
		}

		@Override
		public String getCategory() {
			return category;
		}

		@Override
		public InputUtil.Key getDefaultKey() {
			return InputUtil.Type.KEYSYM.createFromCode(this.defaultKey);
		}

		@Override
		public boolean isDefault() {
			return this.key == this.defaultKey;
		}

		@Override
		public String getTranslationKey() {
			return this.translationText.getKey();
		}
	}

	static {
		defaultCategories.add("key.categories.movement");
		defaultCategories.add("key.categories.gameplay");
		defaultCategories.add("key.categories.inventory");
		defaultCategories.add("key.categories.creative");
		defaultCategories.add("key.categories.multiplayer");
		defaultCategories.add("key.categories.ui");
		defaultCategories.add("key.categories.misc");
	}

}
