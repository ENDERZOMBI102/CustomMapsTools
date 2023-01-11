package com.enderzombi102.cmt

import net.minecraft.util.Identifier
import org.quiltmc.loader.api.ModContainer

object Const {
	private lateinit var CONTAINER: ModContainer

	val version: String
		get() = CONTAINER.metadata().version().raw()
	val modid: String
		get() = CONTAINER.metadata().id()


	fun String.toId(): Identifier =
		Identifier( modid, this )

	fun initialize( container: ModContainer ) {
		CONTAINER = container
	}
}