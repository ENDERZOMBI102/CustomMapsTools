package com.enderzombi102.cmt

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.block.renderer.DisplayBlockEntityRenderer
import com.enderzombi102.cmt.client.particle.InvLightParticle
import com.enderzombi102.cmt.registry.BlockEntityRegistry.displayBlockEntityType
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.registry.Registry
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer

class CustomMapsToolsClient : ClientModInitializer {
	override fun onInitializeClient( container: ModContainer ) {
		BlockEntityRendererFactories.register( displayBlockEntityType, ::DisplayBlockEntityRenderer)
		invLightParticle = Registry.register( Registry.PARTICLE_TYPE, "inv_light".toId(), FabricParticleTypes.simple() )
		ParticleFactoryRegistry.getInstance().register( invLightParticle, InvLightParticle.Factory() )
	}

	companion object {
		lateinit var invLightParticle: DefaultParticleType
	}
}
