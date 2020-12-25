package com.enderzombi102.cmt.gamerule;

import net.minecraft.world.GameRules;

public interface GameruleVisitor extends GameRules.Visitor {

	static void visitString(GameRules.Visitor visitor, GameRules.Key<TextGamerule> textGameruleKey, GameRules.Type<TextGamerule> textGameruleType) {
	}

	default void visitString(GameRules.Key<TextGamerule> key, GameRules.Type<TextGamerule> type) {
	}
}
