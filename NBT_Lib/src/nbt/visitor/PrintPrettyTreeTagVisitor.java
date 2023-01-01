package nbt.visitor;

import nbt.tags.ByteArrayTag;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.DoubleTag;
import nbt.tags.EndTag;
import nbt.tags.FloatTag;
import nbt.tags.IntArrayTag;
import nbt.tags.IntTag;
import nbt.tags.ListTag;
import nbt.tags.LongArrayTag;
import nbt.tags.LongTag;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;
import nbt.tags.Tag;

public class PrintPrettyTreeTagVisitor implements TagVisitor {

	private boolean printEndTag;
	private int depth;
	private StringBuffer buffer;

	public PrintPrettyTreeTagVisitor() {
		buffer = new StringBuffer();
	}

	public PrintPrettyTreeTagVisitor(boolean printEndTag) {
		buffer = new StringBuffer();
		this.printEndTag = printEndTag;
	}

	private void append(String value) {
		buffer.append(value);
	}

	public String getString() {
		return buffer.toString();
	}

	private String getDepthTabs() {
		String s = "";
		for (int i = 0; i < depth; i++) {
			s += "\t";
		}
		return s;
	}

	private String formatName(Tag tag) {
		String name = tag.getName();

		if (name == null || name.isEmpty())
			name = "None";

		else
			name = "'" + tag.getName() + "'";

		return name;
	}

	private String formatTag(String customName, Tag tag, Object value) {
		String s = getDepthTabs();
		s += customName + "(" + formatName(tag) + "): " + value + "\n";
		return s;
	}

	@Override
	public void visit(ByteArrayTag tag) {
		append(formatTag("TAG_ByteArray", tag, tag.getValue().length + " bytes"));
	}

	@Override
	public void visit(ByteTag tag) {
		append(formatTag("TAG_Byte", tag, tag.getValue()));
	}

	@Override
	public void visit(DoubleTag tag) {
		append(formatTag("TAG_Double", tag, tag.getValue()));
	}

	@Override
	public void visit(FloatTag tag) {
		append(formatTag("TAG_Float", tag, tag.getValue()));
	}

	@Override
	public void visit(IntTag tag) {
		append(formatTag("TAG_Int", tag, tag.getValue()));
	}

	@Override
	public void visit(LongTag tag) {
		append(formatTag("TAG_Long", tag, tag.getValue()));
	}

	@Override
	public void visit(ShortTag tag) {
		append(formatTag("TAG_Short", tag, tag.getValue()));
	}

	@Override
	public void visit(IntArrayTag tag) {
		append(formatTag("TAG_IntArray", tag, tag.getValue().length + " ints"));
	}

	@Override
	public void visit(StringTag tag) {
		append(formatTag("TAG_String", tag, tag.getValue()));
	}

	@Override
	public void visit(CompoundTag tag) {
		append(formatTag("TAG_Compound", tag, tag.getTagCountExceptEndTags() + " entries"));
		append(getDepthTabs() + "{\n");
		depth++;
	}

	@Override
	public void visit(ListTag tag) {
		append(formatTag("TAG_List:" + tag.getContentType(), tag, tag.getTagCountExceptEndTags() + " entries"));
		append(getDepthTabs() + "{\n");
		depth++;
	}

	@Override
	public void visit(LongArrayTag tag) {
		append(formatTag("TAG_LongArray", tag, tag.getValue().length + " longs"));
	}

	@Override
	public void visit(EndTag tag) {
		depth--;
		String s = getDepthTabs();
		if (printEndTag) {
			append(s + "} TAG_End\n");
		} else {
			append(s + "}\n");
		}
	}

	@Override
	public void onEndList(ListTag tag) {
		depth--;
		String s = getDepthTabs() + "}\n";
		append(s);
	}

}
