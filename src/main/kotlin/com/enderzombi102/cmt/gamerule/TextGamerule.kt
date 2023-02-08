package com.enderzombi102.cmt.gamerule;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.function.BiConsumer;

public class TextGamerule extends GameRules.Rule<TextGamerule> {

	private String value;
	private String defValue;

	// ---- this code gets the content of a gamerule ----
	// MinecraftServer server = client.getServer();
	// GameRules.Rule<?> rule = server.getGameRules().get(key);
	// logger.info( rule.toString() );

	public static GameRules.Type<TextGamerule> create(String initialValue, BiConsumer<MinecraftServer, TextGamerule> changeCallback) {
		return new GameRules.Type<>(
				StringArgumentType::string,
				(type) -> new TextGamerule( type, initialValue ),
				changeCallback,
				GameruleVisitor::visitString
		);
	}

	public static GameRules.Type<TextGamerule> create(String initialValue) {
		return create( initialValue, (server, rule) -> {} );
	}

	public TextGamerule(GameRules.Type<TextGamerule> rule, String initialValue) {
		super( rule );
		this.value = initialValue;
		this.defValue = initialValue;
	}

	public String get() {
		return this.value;
	}

	public void set(String value, @Nullable MinecraftServer server) {
		this.value = value;
		this.changed(server);
	}

	@Override
	protected void setFromArgument(CommandContext ctx, String name) {
		this.value = StringArgumentType.getString(ctx, name);
	}

	@Override
	protected void deserialize(String value) {
		this.value = value;
	}

	@Override
	public String serialize() {
		return this.value;
	}

	@Override
	public int getCommandResult() {
		return 0;
	}

	@Override
	protected TextGamerule getThis() {
		return this;
	}

	@Override
	protected TextGamerule copy() {
		return new TextGamerule(this.type, this.value);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void setValue(TextGamerule rule, @Nullable MinecraftServer server) {
		this.value = rule.value;
		this.changed(server);
	}

	public TextGamerule getObj() {
		return this;
	}

	public static String getRuleContent(World world, GameRules.Key<TextGamerule> key) {
		MinecraftServer server = world.getServer();
		GameRules.Rule<?> rule = server.getGameRules().get(key);
		return rule.toString();
	}

}
