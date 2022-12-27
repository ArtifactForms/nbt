package nbt.tags;

import nbt.visitor.TagVisitor;

public class FloatTag extends Tag {

    private float value;

    public FloatTag(float value) {
	this(EMPTY_STRING, value);
    }

    public FloatTag(String name, float value) {
	super(name);
	this.value = value;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

    @Override
    public NbtTagType getType() {
	return NbtTagType.FLOAT;
    }

    @Override
    public void accept(TagVisitor visitor) {
	visitor.visit(this);
    }

}
