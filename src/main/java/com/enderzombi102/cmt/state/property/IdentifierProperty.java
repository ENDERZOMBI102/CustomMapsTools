package com.enderzombi102.cmt.state.property;

import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.Optional;

public class IdentifierProperty extends Property<Identifier> {

	protected IdentifierProperty(String name, Class<Identifier> type) {
		super(name, type);
	}

	@Override
	public Collection<Identifier> getValues() {
		return null;
	}

	@Override
	public String name(Identifier value) {
		return null;
	}

	@Override
	public Optional<Identifier> parse(String name) {
		return Optional.empty();
	}
}
