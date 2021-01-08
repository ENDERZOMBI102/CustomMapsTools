package com.enderzombi102.cmt.block.renderer;

import com.enderzombi102.cmt.block.entity.ScreenBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ScreenBlockEntityRenderer extends BlockEntityRenderer<ScreenBlockEntity> {

	private static final ItemStack stack = new ItemStack(Items.JUKEBOX, 1);

	public ScreenBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(ScreenBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		matrices.push(); // always and in every case push before use a MatrixStack

		double offset = Math.sin( (blockEntity.getWorld().getTime() + tickDelta) / 8.0 ) / 4.0;
		// Move the item
		matrices.translate(0.5, 1.25 + offset, 0.5);

		// Rotate the item
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * 4));

		int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
		MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers);

		matrices.pop();
	}

}
