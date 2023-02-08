package com.enderzombi102.cmt.gamerule

import com.mojang.brigadier.arguments.StringArgumentType
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule
import net.minecraft.world.GameRules.IntRule

object Gamerules {
	var joinMessage: GameRules.Key<TextGamerule>? = null
	var exitMessage: GameRules.Key<TextGamerule>? = null
	var onJoinFunction: GameRules.Key<TextGamerule>? = null
	var onExitFunction: GameRules.Key<TextGamerule>? = null
	var maxHealth: GameRules.Key<IntRule>? = null
	var maxHunger: GameRules.Key<IntRule>? = null
	var canSleep: GameRules.Key<BooleanRule>? = null

	init {
		joinMessage = GameRuleRegistry.register(
			"joinMessage",
			GameRules.Category.PLAYER,
			GameRules.Type(
				StringArgumentType::string,
				{ textGameruleType -> TextGamerule( textGameruleType, "" ) },
				{ server, gamerule -> },
				GameRulesVisitor::visitString
			)
		)
	}
}
