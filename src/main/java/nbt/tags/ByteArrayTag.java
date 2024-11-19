package nbt.tags;

import nbt.visitor.TagVisitor;

public class ByteArrayTag extends Tag {

    private byte[] value;

    public ByteArrayTag(byte[] value) {
        this(EMPTY_STRING, value);
    }

    public ByteArrayTag(String name, byte[] value) {
        super(name);
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.BYTE_ARRAY;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
