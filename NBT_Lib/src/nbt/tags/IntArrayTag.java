package nbt.tags;

import nbt.visitor.TagVisitor;

public class IntArrayTag extends Tag {

    private int[] value;

    public IntArrayTag(int[] value) {
	this(EMPTY_STRING, value);
    }

    public IntArrayTag(String name, int[] value) {
	super(name);
	this.value = value;
    }

    public int[] getValue() {
	return value;
    }

    public void setValue(int[] value) {
	this.value = value;
    }

    @Override
    public NbtTagType getType() {
	return NbtTagType.INT_ARRAY;
    }

    @Override
    public void accept(TagVisitor visitor) {
	visitor.visit(this);
    }

}
