package com.enderzombi102.cmt.zone;

public enum ZoneTypes {

	CommandZone,
	FunctionZone;

	public static final int lenght = ZoneTypes.values().length;

	public static ZoneTypes getType(int index) {
		return ZoneTypes.values()[index];
	}
}
