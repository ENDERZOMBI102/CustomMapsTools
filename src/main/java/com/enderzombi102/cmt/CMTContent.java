package com.enderzombi102.cmt;

import com.enderzombi102.cmt.block.InvLightBlock;
import com.enderzombi102.cmt.block.DisplayBlock;
import com.enderzombi102.cmt.block.entity.DisplayBlockEntity;
import com.enderzombi102.cmt.block.renderer.DisplayBlockEntityRenderer;
import com.enderzombi102.cmt.item.ZoneCreatorItem;
import com.enderzombi102.cmt.keybind.KeybindComponent;
import com.enderzombi102.cmt.particle.InvLightParticle;
import com.enderzombi102.cmt.zone.ZoneComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.enderzombi102.cmt.CustomMapsTools.*;

public class CMTContent {

	// blocks
	public static Block invLightBlock = new InvLightBlock();
	public static Block displayBlock = new DisplayBlock();
	// items
	public static Item invLightItem = new BlockItem(invLightBlock, new Item.Settings().group(CMT_GROUP) );
	public static Item displayBlockItem = new BlockItem(displayBlock, new Item.Settings().group(CMT_GROUP) );
	public static Item zoneCreatorItem = new ZoneCreatorItem();
	// particles
	public static DefaultParticleType invLightParticle;
	// block entities types
	public static BlockEntityType<DisplayBlockEntity> displayBlockEntityType = FabricBlockEntityTypeBuilder.create(
			DisplayBlockEntity::new,
			displayBlock
	).build(null);
	// CCA components
	public static final ComponentKey<ZoneComponent> ZONE_COMP_KEY = ComponentRegistry.getOrCreate(
			ID( "world_zone_manager"),
			ZoneComponent.class
	);
	public static final ComponentKey<KeybindComponent> BIND_COMP_KEY = ComponentRegistry.getOrCreate(
			ID( "level_bind_manager"),
			KeybindComponent.class
	);


	public static void register() {
		LOGGER.info("Registering blocks..");
		Registry.register( Registry.BLOCK, "cmt:inv_light", invLightBlock );
		Registry.register( Registry.BLOCK, "cmt:display_block", displayBlock );
		LOGGER.info("Registering items..");
		Registry.register( Registry.ITEM, "cmt:inv_light", invLightItem );
		Registry.register( Registry.ITEM, "cmt:display_block", displayBlockItem );
		Registry.register( Registry.ITEM, "cmt:zone_creator", zoneCreatorItem );
		LOGGER.info("Registering block entities..");
		Registry.register(Registry.BLOCK_ENTITY_TYPE, "cmt:screen_block_entity.type", displayBlockEntityType);
		LOGGER.info("Everything that exist for both sides has been registered!");
	}

	@Environment(EnvType.CLIENT)
	public static void registerClientThings() {
		LOGGER.info("Registering Block Entity Renderers..");
		BlockEntityRendererRegistry.INSTANCE.register(
				displayBlockEntityType,
				DisplayBlockEntityRenderer::new
		);
		LOGGER.info("Registering particles..");
		invLightParticle = Registry.register(
				Registry.PARTICLE_TYPE,
				"cmt:inv_light",
				FabricParticleTypes.simple()
		);
		ParticleFactoryRegistry.getInstance().register(invLightParticle, new InvLightParticle.Factory() );
		LOGGER.info("Everything client-side only has been registered!");
	}
}
