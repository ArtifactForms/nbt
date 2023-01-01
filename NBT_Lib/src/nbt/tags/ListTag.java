package nbt.tags;

import nbt.exception.NbtException;
import nbt.visitor.TagVisitor;

public class ListTag extends Tag {

	private int targetSize;
	private NbtTagType contentType;

	public ListTag(NbtTagType contentType) {
		this(EMPTY_STRING, contentType);
	}

	public ListTag(String name, NbtTagType contentType) {
		super(name);
		this.contentType = contentType;
	}

	@Override
	public void add(Tag tag) {
		if (tag.getType() != getContentType() && tag.getType() != NbtTagType.END) {
			throw new NbtException("Wrong content type.");
		}
		super.add(tag);
	}

	@Override
	public boolean allowsChildren() {
		return true;
	}

	public int getTargetSize() {
		return targetSize;
	}

	public void setTargetSize(int targetSize) {
		this.targetSize = targetSize;
	}

	public int getSize() {
		return getTagCountExceptEndTags();
	}

	@Override
	public boolean isList() {
		return true;
	}

	public NbtTagType getContentType() {
		return contentType;
	}

	public void setContentType(NbtTagType contentType) {
		this.contentType = contentType;
	}

	@Override
	public NbtTagType getType() {
		return NbtTagType.LIST;
	}

	@Override
	public void accept(TagVisitor visitor) {
		visitor.visit(this);
		for (int i = 0; i < getTagCount(); i++) {
			Tag tag = getTagAt(i);
			tag.accept(visitor);
		}
		visitor.onEndList(this);
	}

}
