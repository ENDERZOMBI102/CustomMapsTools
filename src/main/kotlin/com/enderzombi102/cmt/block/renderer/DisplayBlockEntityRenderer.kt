package com.enderzombi102.cmt.block.renderer

import com.enderzombi102.cmt.block.entity.DisplayBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.WorldRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.Vec3f
import kotlin.math.sin

class DisplayBlockEntityRenderer( ctx: Context ) : BlockEntityRenderer<DisplayBlockEntity> {
	override fun render(
		blockEntity: DisplayBlockEntity,
		tickDelta: Float,
		matrices: MatrixStack,
		vertexConsumers: VertexConsumerProvider,
		light: Int,
		overlay: Int
	) {
		// always and in every case push before use a MatrixStack
		matrices.push()

		// calc item offset
		val offset = sin( ( blockEntity.world!!.time + tickDelta ) / 8.0 ) / 4.0

		// Move the item
		matrices.translate(0.5, 1.25 + offset, 0.5)

		// Rotate the item
		matrices.multiply( 	Vec3f.POSITIVE_Y.getDegreesQuaternion( (blockEntity.world!!.time + tickDelta) * 4 ) )

		val lightAbove = WorldRenderer.getLightmapCoordinates( blockEntity.world, blockEntity.pos.up() )
		MinecraftClient.getInstance().itemRenderer.renderItem(
			blockEntity.stack,
			ModelTransformation.Mode.GROUND,
			lightAbove,
			OverlayTexture.DEFAULT_UV,
			matrices,
			vertexConsumers,
			0
		)

		matrices.pop()
	}
}
