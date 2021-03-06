package com.enderzombi102.cmt.block.renderer;

import com.enderzombi102.cmt.block.entity.DisplayBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class DisplayBlockEntityRenderer implements BlockEntityRenderer<DisplayBlockEntity> {

	public DisplayBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

	@Override
	public void render(DisplayBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		// always and in every case push before use a MatrixStack
		matrices.push();

		// calc item offset
		// noinspection ConstantConditions
		double offset = Math.sin(
				( blockEntity.getWorld().getTime() + tickDelta ) / 8.0
		) / 4.0;

		// Move the item
		matrices.translate(0.5, 1.25 + offset, 0.5);

		// Rotate the item
		matrices.multiply(
				Vec3f.POSITIVE_Y.getDegreesQuaternion(
				( blockEntity.getWorld().getTime() + tickDelta ) * 4
				)
		);

		int lightAbove = WorldRenderer.getLightmapCoordinates(
				blockEntity.getWorld(),
				blockEntity.getPos().up()
		);
		MinecraftClient.getInstance().getItemRenderer().renderItem(
				blockEntity.getItem(),
				ModelTransformation.Mode.GROUND,
				lightAbove,
				OverlayTexture.DEFAULT_UV,
				matrices,
				vertexConsumers,
				0
		);

		matrices.pop();
	}

}
