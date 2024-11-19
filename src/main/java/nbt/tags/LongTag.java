package nbt.tags;

import nbt.visitor.TagVisitor;

public class LongTag extends Tag {

    private long value;

    public LongTag(long value) {
        this(EMPTY_STRING, value);
    }

    public LongTag(String name, long value) {
        super(name);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.LONG;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
