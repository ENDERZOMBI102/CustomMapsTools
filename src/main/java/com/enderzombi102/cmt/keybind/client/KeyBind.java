package com.enderzombi102.cmt.keybind.client;

import com.enderzombi102.cmt.mixins.client.KeyBindingEntryAccessor;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.enderzombi102.cmt.keybind.client.KeyBindingHelper.defaultCategories;

public class KeyBind extends KeyBinding {

	private int key;
	private final int defaultKey;
	private final TranslatableText translationText;
	public final String category;
	private final Consumer<MinecraftClient> callback;
	public final boolean requiresInGame;
	public final boolean requiresInteracting;

	public KeyBind( Key key, Consumer<MinecraftClient> callback, String transText, String category, boolean requiresInGame, boolean requiresInteracting ) {
		super(transText, key.getCode(), category);
		this.callback = callback;
		this.key = key.getCode();
		this.defaultKey = key.getCode();
		this.translationText = new TranslatableText( transText );
		this.category = category;
		this.requiresInGame = requiresInGame;  // requires the game to not be paused
		this.requiresInteracting = requiresInteracting;  // requires be in-game and not in a inventory

		// taken from FAPI
		Map<String, Integer> map = KeyBindingAccessor.fabric_getCategoryMap();

		if (! KeyBindingAccessor.fabric_getCategoryMap().containsKey(category) ) {

			Optional<Integer> largest = map.values().stream().max(Integer::compareTo);
			int largestInt = largest.orElse(0);
			map.put(category, largestInt + 1);
		}
	}

	public ControlsListWidget.KeyBindingEntry getEntry(ControlsListWidget parent ) {
		return KeyBindingEntryAccessor.invokeInit( parent, this, this.translationText );
	}

	public Text getTranslationText() {
		return this.translationText;
	}

	public int getIntKey() {
		return key;
	}

	public @Nullable Key getKey() {
		for ( Key key : Key.values() ) {
			if ( key.getCode() == this.key ) return key;
		}
		return null;
	}

	public Consumer<MinecraftClient> getCallback() {
		return callback;
	}

	public boolean isUsingDefaultCategories() {
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
