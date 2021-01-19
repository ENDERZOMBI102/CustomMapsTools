package com.enderzombi102.cmt;

import com.enderzombi102.cmt.block.InvLightBlock;
import com.enderzombi102.cmt.block.ScreenBlock;
import com.enderzombi102.cmt.block.entity.ScreenBlockEntity;
import com.enderzombi102.cmt.block.renderer.ScreenBlockEntityRenderer;
import com.enderzombi102.cmt.particle.InvLightParticle;
import com.enderzombi102.cmt.zone.ZoneComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import static com.enderzombi102.cmt.CustomMapsTools.LOGGER;

public class CMTContent {

	// blocks
	public static Block invLightBlock = new InvLightBlock();
	public static Block screenBlock = new ScreenBlock();
	// items
	public static Item invLightItem = new BlockItem(invLightBlock, new Item.Settings().group(ItemGroup.MISC) );
	public static Item screenBlockItem = new BlockItem(screenBlock, new Item.Settings().group(ItemGroup.MISC) );
	// particles
	public static DefaultParticleType invLightParticle;
	// block entities types
	public static BlockEntityType<ScreenBlockEntity> screenBlockEntityType = BlockEntityType.Builder.create(ScreenBlockEntity::new, screenBlock).build(null);
	// CCA components
	public static final ComponentKey<ZoneComponent> ZONE_COMP_KEY = ComponentRegistry.getOrCreate( new Identifier("cmt", "world_zone_manager"), ZoneComponent.class );



	public static void register() {
		LOGGER.info("Registering blocks..");
		Registry.register( Registry.BLOCK, "cmt:inv_light", invLightBlock );
		Registry.register( Registry.BLOCK, "cmt:screen_block", screenBlock );
		LOGGER.info("Registering items..");
		Registry.register( Registry.ITEM, "cmt:inv_light", invLightItem );
		Registry.register( Registry.ITEM, "cmt:screen_block", screenBlockItem );
		LOGGER.info("Registering particles..");
		invLightParticle = Registry.register(Registry.PARTICLE_TYPE, "cmt:inv_light", FabricParticleTypes.simple() );
		ParticleFactoryRegistry.getInstance().register(invLightParticle, new InvLightParticle.Factory() );
		LOGGER.info("Registering block entities..");
		Registry.register(Registry.BLOCK_ENTITY_TYPE, "cmt:screen_block_entity.type", screenBlockEntityType);
		LOGGER.info("Everything that exist for both sides has been registered!");
	}

	@Environment(EnvType.CLIENT)
	public static void registerClientThings() {
		LOGGER.info("Registering Block Entity Renderers..");
		BlockEntityRendererRegistry.INSTANCE.register(screenBlockEntityType, ScreenBlockEntityRenderer::new);
		LOGGER.info("Everything client-side only has been registered!");
	}
}
