package com.enderzombi102.cmt.gamerule

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.server.MinecraftServer
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.world.GameRules
import net.minecraft.world.World
import java.util.function.BiConsumer

class TextGamerule( rule: GameRules.Type<TextGamerule>, private var value: String ) : GameRules.Rule<TextGamerule>(rule) {
	private val defValue: String

	init {
		defValue = value
	}

	fun get(): String {
		return value
	}

	operator fun set(value: String, server: MinecraftServer?) {
		this.value = value
		changed(server)
	}

	override fun setFromArgument( ctx: CommandContext<ServerCommandSource>, name: String ) {
		value = StringArgumentType.getString( ctx, name )
	}

	override fun deserialize( value: String ) {
		this.value = value
	}

	override fun serialize(): String {
		return value
	}

	override fun getCommandResult(): Int {
		return 0
	}

	override fun getThis(): TextGamerule {
		return this
	}

	override fun copy(): TextGamerule {
		return TextGamerule(type, value)
	}

	@Environment(EnvType.CLIENT)
	override fun setValue(rule: TextGamerule, server: MinecraftServer?) {
		value = rule.value
		changed(server)
	}

	val obj: TextGamerule
		get() = this

	companion object {
		// ---- this code gets the content of a gamerule ----
		// MinecraftServer server = client.getServer();
		// GameRules.Rule<?> rule = server.getGameRules().get(key);
		// logger.info( rule.toString() );
		@JvmOverloads
		fun create( initialValue: String, changeCallback: BiConsumer<MinecraftServer, TextGamerule> = BiConsumer { _, _ -> } ): GameRules.Type<TextGamerule> {
			return GameRules.Type(
				{ StringArgumentType.string() },
				{ type -> TextGamerule( type, initialValue ) },
				changeCallback,
				GameRulesVisitor::visitString
			)
		}

		fun getRuleContent( world: World, key: GameRules.Key<TextGamerule> ): String {
			val server = world.server
			val rule: GameRules.Rule<*> = server!!.gameRules.get(key)
			return rule.toString()
		}
	}
}
