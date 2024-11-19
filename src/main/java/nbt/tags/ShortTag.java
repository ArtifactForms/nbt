package nbt.tags;

import nbt.visitor.TagVisitor;

public class ShortTag extends Tag {

    private short value;

    public ShortTag(short value) {
        this(EMPTY_STRING, value);
    }

    public ShortTag(String name, short value) {
        super(name);
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.SHORT;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
