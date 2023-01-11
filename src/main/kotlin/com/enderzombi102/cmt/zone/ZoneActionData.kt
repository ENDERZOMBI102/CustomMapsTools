package com.enderzombi102.cmt.zone

import net.minecraft.util.Identifier

class ZoneActionData {
	// command
	var enterCommand: String? = null
	var leaveCommand: String? = null
	var midCommand: String? = null

	// function
	var leaveFunction: Identifier? = null
	var enterFunction: Identifier? = null
	var midFunction: Identifier? = null
	fun withEnterFunction(cmd: Identifier?): ZoneActionData {
		enterFunction = cmd
		return this
	}

	fun withMidFunction(cmd: Identifier?): ZoneActionData {
		midFunction = cmd
		return this
	}

	fun withLeaveFunction(cmd: Identifier?): ZoneActionData {
		leaveFunction = cmd
		return this
	}

	fun withEnterCommand(cmd: String?): ZoneActionData {
		enterCommand = cmd
		return this
	}

	fun withMidCommand(cmd: String?): ZoneActionData {
		midCommand = cmd
		return this
	}

	fun withLeaveCommand(cmd: String?): ZoneActionData {
		leaveCommand = cmd
		return this
	}
}
