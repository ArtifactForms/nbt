package nbt.tags;

import nbt.visitor.TagVisitor;

public class EndTag extends Tag {

	public EndTag() {
		super(EMPTY_STRING);
	}

	@Override
	public void accept(TagVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public NbtTagType getType() {
		return NbtTagType.END;
	}

}
