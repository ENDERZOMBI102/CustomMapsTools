package com.enderzombi102.cmt.gamerule;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;


public class Gamerules {

	public static GameRules.Key<TextGamerule> joinMessage;
	public static GameRules.Key<TextGamerule> exitMessage;
	public static GameRules.Key<TextGamerule> onJoinFunction;
	public static GameRules.Key<TextGamerule> onExitFunction;
	public static GameRules.Key<GameRules.IntRule> maxHealth;
	public static GameRules.Key<GameRules.IntRule> maxHunger;
	public static GameRules.Key<GameRules.BooleanRule> canSleep;

	static {
		joinMessage = GameRuleRegistry.register(
				"joinMessage",
				GameRules.Category.PLAYER,
				new GameRules.Type< TextGamerule >(
						StringArgumentType::string,
						textGameruleType -> new TextGamerule(textGameruleType, ""),
						(server, gamerule) -> {},
						GameruleVisitor::visitString
				)
		);
	}


}
