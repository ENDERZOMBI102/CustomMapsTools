package com.enderzombi102.cmt.zone;

import net.minecraft.util.Identifier;

public class ZoneActionData {

	// command
	public String enterCommand = null;
	public String leaveCommand = null;
	public String midCommand = null;
	// function
	public Identifier leaveFunction = null;
	public Identifier enterFunction = null;
	public Identifier midFunction = null;

	public ZoneActionData withEnterFunction(Identifier cmd) {
		this.enterFunction = cmd;
		return this;
	}

	public ZoneActionData withMidFunction(Identifier cmd) {
		this.midFunction = cmd;
		return this;
	}

	public ZoneActionData withLeaveFunction(Identifier cmd) {
		this.leaveFunction = cmd;
		return this;
	}

	public ZoneActionData withEnterCommand(String cmd) {
		this.enterCommand = cmd;
		return this;
	}

	public ZoneActionData withMidCommand(String cmd) {
		this.midCommand = cmd;
		return this;
	}

	public ZoneActionData withLeaveCommand(String cmd) {
		this.leaveCommand = cmd;
		return this;
	}

}
