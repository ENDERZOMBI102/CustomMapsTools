package com.enderzombi102.cmt.registry

import com.enderzombi102.cmt.CMTContent
import com.enderzombi102.cmt.Const.toId
import com.enderzombi102.cmt.keybind.KeybindComponent
import com.enderzombi102.cmt.zone.ZoneComponent
import com.enderzombi102.cmt.zone.ZoneManager
import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer

object ComponentRegistry {
	val zoneComponentKey: ComponentKey<ZoneComponent> =
		ComponentRegistry.getOrCreate( "world_zone_manager".toId(), ZoneComponent::class.java )

	val bindComponentKey: ComponentKey<KeybindComponent> =
		ComponentRegistry.getOrCreate( "level_bind_manager".toId(), KeybindComponent::class.java )

	class WorldEntrypoint : WorldComponentInitializer {
		override fun registerWorldComponentFactories( registry: WorldComponentFactoryRegistry ) {
			registry.register( CMTContent.ZONE_COMP_KEY, ::ZoneManager )
		}
	}
}
