package com.enderzombi102.cmt;

import com.enderzombi102.cmt.block.InvLightBlock;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;
import static com.enderzombi102.cmt.CustomMapsTools.logger;

public class CMTContent {

	// blocks
	public static Block invLightBlock = new InvLightBlock();
	// items
	public static Item invLightItem = new BlockItem(invLightBlock, new Item.Settings().group(ItemGroup.MISC) );
	// particles
	public static DefaultParticleType invLightParticle;


	public static void register() {
		logger.info("registering blocks..");
		Registry.register( Registry.BLOCK, "cmt:inv_light", invLightBlock );
		logger.info("registering items..");
		Registry.register( Registry.ITEM, "cmt:inv_light", invLightItem );
		logger.info("registering particles..");
		invLightParticle = Registry.register(Registry.PARTICLE_TYPE, "cmt:inv_light", FabricParticleTypes.simple(true) );
		logger.info("everything has been registered!");
	}
}
