package com.enderzombi102.cmt.state.property;

import com.google.common.collect.ImmutableSet;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class IdentifierProperty extends Property<Identifier> {

	private final List<Identifier> values;

	protected IdentifierProperty(String name, @Nullable List<Identifier> possibleValues) {
		super(name, Identifier.class);
		this.values = possibleValues;
	}

	@Override
	public Collection<Identifier> getValues() {
		return ImmutableSet.copyOf( this.values );
	}

	@Override
	public String name(Identifier value) {
		return value.toString();
	}

	@Override
	public Optional<Identifier> parse(String name) {
		Optional<Identifier> value;
		try {
			value = Optional.of( new Identifier(name) );
		} catch (Exception e) {
			value = Optional.empty();
		}
		return value;
	}
}
