package com.enderzombi102.cmt

import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.block.renderer.DisplayBlockEntityRenderer
import com.enderzombi102.cmt.particle.InvLightParticle
import com.enderzombi102.cmt.registry.BlockEntityRegistry.displayBlockEntityType
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer

class CustomMapsToolsClient : ClientModInitializer {
	override fun onInitializeClient( container: ModContainer ) {
		BlockEntityRendererRegistry.register( displayBlockEntityType, ::DisplayBlockEntityRenderer )
		val particle = Registry.register( Registries.PARTICLE_TYPE, "inv_light".toId(), FabricParticleTypes.simple() )
		ParticleFactoryRegistry.getInstance().register( particle, InvLightParticle.Factory() )
	}
}