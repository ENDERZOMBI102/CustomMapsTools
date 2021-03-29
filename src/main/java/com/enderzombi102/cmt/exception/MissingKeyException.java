package com.enderzombi102.cmt.exception;

import blue.endless.jankson.impl.SyntaxError;

public class MissingKeyException extends SyntaxError {

	private final String key;

	public MissingKeyException( String key ) {
		super("");
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}
}
