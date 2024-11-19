package nbt.tags;

import nbt.visitor.TagVisitor;

public class DoubleTag extends Tag {

    private double value;

    public DoubleTag(double value) {
        this(EMPTY_STRING, value);
    }

    public DoubleTag(String name, double value) {
        super(name);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public NbtTagType getType() {
        return NbtTagType.DOUBLE;
    }

    @Override
    public void accept(TagVisitor visitor) {
        visitor.visit(this);
    }

}
