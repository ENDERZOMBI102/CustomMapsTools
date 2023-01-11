package com.enderzombi102.cmt.zone

import dev.onyxstudios.cca.api.v3.component.ComponentV3
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity

interface ZoneComponent : ComponentV3, ServerTickingComponent {
	fun addZone(zone: AbstractZone<out Entity>)
	fun removeZone(entity: PlayerEntity): String?
	fun removeZone(id: String)
	fun containsZone(id: String): Boolean
	fun entityInZone(entities: List<Entity>, id: String): Boolean
	fun getZone(id: String): AbstractZone<out Entity>?
	fun getZones(): List<AbstractZone<out Entity>>
	fun isIdValid(id: String): Boolean
	val randomId: String
}
