package com.enderzombi102.cmt.keybind.client;

import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Consumer;

public class KeyBindingHelper {

	public static final ArrayList<KeyBinding> keyCallbacks = new ArrayList<>();
	static final ArrayList<String> defaultCategories = new ArrayList<>();

	public static ArrayList<KeyBinding> getKeyCallbacks() {
		return keyCallbacks;
	}

	public static KeyBinding makeKeybind(Key key, Consumer<MinecraftClient> callback, String transText, String category, Boolean requiresInGame, Boolean requiresInteracting ) {
		KeyBinding bind = new KeyBinding(key, callback, transText, category, requiresInGame, requiresInteracting );
		keyCallbacks.add( bind );
		return bind;
	}

	public static boolean removeKeybind(KeyBinding keyBinding) {
		return keyCallbacks.remove(keyBinding);
	}

	public static @Nullable KeyBinding getKeybind(Consumer<MinecraftClient> func) {
		for (KeyBinding bind : keyCallbacks ) {
			if (bind.getCallback() == func) return bind;
		}
		return null;
	}

	public static ArrayList<KeyBinding> getKeybindsWithKey(Key key) {
		ArrayList<KeyBinding> toReturn = new ArrayList<>();
		for (KeyBinding bind : keyCallbacks ) {
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
