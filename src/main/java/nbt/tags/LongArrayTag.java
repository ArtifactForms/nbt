package nbt.tags;

import nbt.visitor.TagVisitor;

public class LongArrayTag extends Tag {

    private long[] value;

    public LongArrayTag(long[] value) {
        this(EMPTY_STRING, value);
    }

    public LongArrayTag(String name, long[] value) {
        super(name);
        this.value = value;
    }

    public long[] getValue() {
        return value;
    }

    public void setValue(long[] value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.LONG_ARRAY;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
