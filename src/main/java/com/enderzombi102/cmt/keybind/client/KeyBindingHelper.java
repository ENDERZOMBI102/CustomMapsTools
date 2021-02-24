package com.enderzombi102.cmt.keybind.client;

import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Consumer;

public class KeyBindingHelper {

	private static final ArrayList< KeyBind > keyCallbacks = new ArrayList<>();
	static final ArrayList<String> defaultCategories = new ArrayList<>();

	public static ArrayList<KeyBind> getKeyCallbacks() {
		return keyCallbacks;
	}

	public static KeyBind makeKeybind(Key key, Consumer<MinecraftClient> callback, String transText, String category, Boolean requiresInGame, Boolean requiresInteracting ) {
		KeyBind bind = new KeyBind(key, callback, transText, category, requiresInGame, requiresInteracting );
		keyCallbacks.add( bind );
		return bind;
	}

	public static boolean removeKeybind(KeyBind keyBind) {
		return keyCallbacks.remove( keyBind );
	}

	public static @Nullable KeyBind getKeybind(Consumer<MinecraftClient> func) {
		for (KeyBind bind : keyCallbacks ) {
			if (bind.getCallback() == func) return bind;
		}
		return null;
	}

	public static ArrayList<KeyBind> getKeybindsWithKey(Key key) {
		ArrayList<KeyBind> toReturn = new ArrayList<>();
		for (KeyBind bind : keyCallbacks ) {
			if ( bind.getKey() == key ) toReturn.add(bind);
		}
		return toReturn;
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
