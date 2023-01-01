package nbt.tags;

import nbt.visitor.TagVisitor;

public class StringTag extends Tag {

	private String value;

	public StringTag(String value) {
		this(EMPTY_STRING, value);
	}

	public StringTag(String name, String value) {
		super(name);
		this.value = value;
	}

	@Override
	public boolean allowsChildren() {
		return false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public NbtTagType getType() {
		return NbtTagType.STRING;
	}

	@Override
	public void accept(TagVisitor visitor) {
		visitor.visit(this);
	}

}
