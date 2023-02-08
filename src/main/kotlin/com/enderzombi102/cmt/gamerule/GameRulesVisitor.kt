package com.enderzombi102.cmt.gamerule

import net.minecraft.world.GameRules

interface GameRulesVisitor : GameRules.Visitor {
	fun visitString( key: GameRules.Key<TextGamerule>, type: GameRules.Type<TextGamerule> ) {}

	companion object {
		fun visitString( visitor: GameRules.Visitor, key: GameRules.Key<TextGamerule>, type: GameRules.Type<TextGamerule> ) {

		}
	}
}
