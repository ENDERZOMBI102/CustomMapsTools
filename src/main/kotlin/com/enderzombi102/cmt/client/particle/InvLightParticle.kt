package com.enderzombi102.cmt.client.particle

import com.enderzombi102.cmt.registry.ItemRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteBillboardParticle
import net.minecraft.client.world.ClientWorld
import net.minecraft.item.ItemConvertible
import net.minecraft.particle.DefaultParticleType

@Environment(EnvType.CLIENT)
class InvLightParticle private constructor( world: ClientWorld, x: Double, y: Double, z: Double )
	: SpriteBillboardParticle( world, x, y, z ) {
	init {
		sprite = MinecraftClient.getInstance().itemRenderer.models.getModel(ItemRegistry["inv_light"])!!.particleSprite
		gravityStrength = 0.0f
		maxAge = 40
		collidesWithWorld = false
	}

	override fun getType(): ParticleTextureSheet =
		ParticleTextureSheet.TERRAIN_SHEET

	override fun getSize(tickDelta: Float): Float =
		0.5f

	@Environment(EnvType.CLIENT)
	class Factory : ParticleFactory<DefaultParticleType> {
		override fun createParticle(
			defaultParticleType: DefaultParticleType,
			clientWorld: ClientWorld,
			x: Double,
			y: Double,
			z: Double,
			velocityX: Double,
			velocityY: Double,
			velocityZ: Double
		): Particle =
			InvLightParticle(clientWorld, x, y, z)
	}
}
