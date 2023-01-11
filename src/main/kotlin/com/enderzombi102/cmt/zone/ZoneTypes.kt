package com.enderzombi102.cmt.zone

enum class ZoneTypes {
	CommandZone, FunctionZone;

	companion object {
		val length = values().size
		fun getType(index: Int): ZoneTypes {
			return values()[index]
		}
	}
}
