package com.enderzombi102.cmt.keybind.client;

import com.enderzombi102.cmt.mixins.client.KeyBindingEntryAccessor;
import com.mojang.blaze3d.platform.InputUtil;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.option.KeyBindListWidget;
import net.minecraft.client.option.KeyBind;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.component.TranslatableComponent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.enderzombi102.cmt.keybind.client.KeyBindingHelper.defaultCategories;

public class KeyBinding extends KeyBind {

	private int key;
	private final int defaultKey;
	private final TranslatableComponent translationText;
	public final String category;
	private final Consumer< MinecraftClient > callback;
	public final boolean requiresInGame;
	public final boolean requiresInteracting;

	public KeyBinding( Key key, Consumer< MinecraftClient > callback, String transText, String category, boolean requiresInGame, boolean requiresInteracting ) {
		super( transText, key.getCode(), category );
		this.callback = callback;
		this.key = key.getCode();
		this.defaultKey = key.getCode();
		this.translationText = new TranslatableComponent( transText );
		this.category = category;
		this.requiresInGame = requiresInGame;  // requires the game to not be paused
		this.requiresInteracting = requiresInteracting;  // requires be in-game and not in a inventory

		// taken from FAPI
		Map< String, Integer > map = KeyBindingAccessor.fabric_getCategoryMap();

		if ( !KeyBindingAccessor.fabric_getCategoryMap().containsKey( category ) ) {

			Optional< Integer > largest = map.values().stream().max( Integer::compareTo );
			int largestInt = largest.orElse( 0 );
			map.put( category, largestInt + 1 );
		}
	}

	public KeyBindListWidget.KeyBindEntry getEntry( KeyBindListWidget parent ) {
		return KeyBindingEntryAccessor.invokeInit( parent, this, MutableText.create( this.translationText ) );
	}

	public Text getTranslationText() {
		return MutableText.create( this.translationText );
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

	public Consumer< MinecraftClient > getCallback() {
		return callback;
	}

	public boolean isUsingDefaultCategories() {
		return defaultCategories.contains( this.category );
	}

	@Override
	public void setBoundKey( InputUtil.Key boundKey ) {
		this.key = boundKey.getKeyCode();
		super.setBoundKey( boundKey );
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public InputUtil.Key getDefaultKey() {
		return InputUtil.Type.KEYSYM.createFromKeyCode( this.defaultKey );
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
