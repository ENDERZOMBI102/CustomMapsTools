package com.enderzombi102.cmt.keybind;

import com.enderzombi102.cmt.keybind.client.Key;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.WorldProperties;

import java.util.function.Consumer;

public class KeybindManager implements KeybindComponent {

	public KeybindManager(WorldProperties properties) {

	}

	@Override
	public void readFromNbt(CompoundTag tag) {

	}

	@Override
	public void writeToNbt(CompoundTag tag) {

	}

	@Override
	public void createBind(Key key, Consumer<MinecraftClient> callback, String category, String name) {

	}

}
