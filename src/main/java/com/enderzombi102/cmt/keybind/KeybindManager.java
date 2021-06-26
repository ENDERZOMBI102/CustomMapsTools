package com.enderzombi102.cmt.keybind;

import com.enderzombi102.cmt.command.Executor;
import com.enderzombi102.cmt.keybind.client.Key;
import com.enderzombi102.cmt.keybind.client.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;

import java.util.function.Consumer;

public class KeybindManager {

	public static void createBind(String key, String category, String name, String executes) {

		Consumer<MinecraftClient> consumer = new Consumer<>() {

			public final Executor executor = new Executor(null, executes);

			@Override
			public void accept(MinecraftClient minecraftClient) {
				executor.Execute(minecraftClient.player);
			}
		};

		KeyBindingHelper.makeKeybind(
				Key.findKey(key),
				consumer,
				name,
				category,
				true,
				true
		);
	}

	public static void clearKeybinds() {
		KeyBindingHelper.keyCallbacks.clear();
	}

}
