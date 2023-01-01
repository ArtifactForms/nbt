package nbt.tags;

import nbt.visitor.TagVisitor;

public class CompoundTag extends Tag {

	public CompoundTag() {
		this(EMPTY_STRING);
	}

	public CompoundTag(String name) {
		super(name);
	}

	@Override
	public boolean allowsChildren() {
		return true;
	}

	@Override
	public NbtTagType getType() {
		return NbtTagType.COMPOUND;
	}

	public void add(String name, byte[] value) {
		add(new ByteArrayTag(name, value));
	}

	public void add(String name, short value) {
		add(new ShortTag(name, value));
	}

	public void add(String name, String value) {
		add(new StringTag(name, value));
	}

	public void add(String name, byte value) {
		add(new ByteTag(name, value));
	}

	public void add(String name, double value) {
		add(new DoubleTag(name, value));
	}

	public void add(String name, int value) {
		add(new IntTag(name, value));
	}

	public void add(String name, long value) {
		add(new LongTag(name, value));
	}

	public void add(String name, boolean value) {
		add(new ByteTag(name, value ? (byte) 1 : (byte) 0));
	}

	public void addEnd() {
		add(new EndTag());
	}

	@Override
	public void accept(TagVisitor visitor) {
		visitor.visit(this);
		for (int i = 0; i < getTagCount(); i++) {
			Tag tag = getTagAt(i);
			tag.accept(visitor);
		}
	}

}
