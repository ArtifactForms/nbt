package nbt.tags;

import nbt.visitor.TagVisitor;

public class IntTag extends Tag {

    private int value;

    public IntTag(int value) {
        this(EMPTY_STRING, value);
    }

    public IntTag(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.INT;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
