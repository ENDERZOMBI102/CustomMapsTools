package com.enderzombi102.cmt;

import com.enderzombi102.cmt.block.InvLightBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import static com.enderzombi102.cmt.CustomMapsTools.logger;

public class CMTContent {

	// blocks
	public static Block invLightBlock = new InvLightBlock();

	public static void register() {
		logger.info("registering blocks..");
		Registry.register( Registry.BLOCK, "cmt:inv_light", invLightBlock );
		logger.info("registering items..");
		Registry.register( Registry.ITEM, "cmt:inv_light", new BlockItem(invLightBlock, new Item.Settings().group(ItemGroup.MISC) ) );
	}
}
