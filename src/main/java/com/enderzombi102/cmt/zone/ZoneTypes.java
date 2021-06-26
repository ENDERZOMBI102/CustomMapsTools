package com.enderzombi102.cmt.zone;

public enum ZoneTypes {

	CommandZone,
	FunctionZone;

	public static final int length = ZoneTypes.values().length;

	public static ZoneTypes getType(int index) {
		return ZoneTypes.values()[index];
	}
}
