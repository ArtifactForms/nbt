package nbt.io;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

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
import nbt.tags.NbtTagType;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;
import nbt.tags.Tag;

public class NbtReader implements Closeable {

	private Tag current;
	private Tag root;
	public DataInputStream inputStream;

	public NbtReader(File file) throws IOException {
		this(file, true);
	}

	public NbtReader(File file, boolean compressed) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		if (compressed) {
			GZIPInputStream is = new GZIPInputStream(fis);
			inputStream = new DataInputStream(is);
		} else {
			inputStream = new DataInputStream(fis);
		}
	}

	private boolean currentTagIsListItem() {
		if (!current.hasParent())
			return false;

		return current.getParent().isList();
	}

	private boolean currentTagIsCompoundListItem() {
		if (!currentTagIsListItem())
			return false;

		ListTag list = (ListTag) current.getParent();
		return list.getContentType() == NbtTagType.COMPOUND;
	}

	private void processEndTag() {
		addToCurrent(new EndTag());
		if (currentTagIsCompoundListItem()) {
			endCompoundListItem();
		} else {
			setCurrentTag(current.getBranch());
		}
	}

	private void endCompoundListItem() {
		ListTag list = (ListTag) current.getParent();
		if (targetSizeReached(list)) {
			setCurrentTag(list.getBranch());
		} else {
			CompoundTag tag = new CompoundTag("");
			list.add(tag);
			setCurrentTag(tag);
		}
	}

	private boolean targetSizeReached(ListTag list) {
		return list.getSize() == list.getTargetSize();
	}

	private void readNamedCompoundTag() throws IOException {
		CompoundTag compoundTag = new CompoundTag(readTagName());

		if (noCurrentTag()) {
			setRoot(compoundTag);
		} else {
			addToCurrent(compoundTag);
		}

		setCurrentTag(compoundTag);
	}

	private CompoundTag readUnnamedCompoundTag() throws IOException {
		CompoundTag compoundTag = new CompoundTag();
		setCurrentTag(compoundTag);
		return compoundTag;
	}

	private boolean noCurrentTag() {
		return current == null;
	}

	public Tag read() throws IOException {
		while (shouldRead()) {
			processNbtType(NbtTagType.getTypeById(readByte()));
		}
		return root;
	}

	private boolean shouldRead() throws IOException {
		return inputStream.available() > 0;
	}

	private void processNbtType(NbtTagType type) throws IOException {
		switch (type) {
		case COMPOUND:
			readNamedCompoundTag();
			break;
		case BYTE:
			readByteTag();
			break;
		case SHORT:
			readShortTag();
			break;
		case INT:
			readIntTag();
			break;
		case LONG:
			readLongTag();
			break;
		case FLOAT:
			readFloatTag();
			break;
		case DOUBLE:
			readDoubleTag();
			break;
		case BYTE_ARRAY:
			readByteArrayTag();
			break;
		case STRING:
			readStringTag();
			break;
		case LONG_ARRAY:
			readLongArrayTag();
			break;
		case INT_ARRAY:
			readIntArrayTag();
			break;
		case LIST:
			readListTag();
			break;
		case END:
			processEndTag();
			break;
		default:
			inputStream.close();
			throw new UnknownNbtTagType();
		}
	}

	private Tag readUnnamedTag(NbtTagType type) throws IOException {
		switch (type) {
		case BYTE:
			return new ByteTag(readByte());
		case BYTE_ARRAY:
			return new ByteArrayTag(readByteArray());
		case INT_ARRAY:
			return new IntArrayTag(readIntArray());
		case LONG_ARRAY:
			return new LongArrayTag(readLongArray());
		case SHORT:
			return new ShortTag(readShort());
		case COMPOUND:
			return readUnnamedCompoundTag();
		case LIST:
			return readUnnamedListTag();
		case STRING:
			return new StringTag(readString());
		case LONG:
			return new LongTag(readLong());
		case INT:
			return new IntTag(readInt());
		case DOUBLE:
			return new DoubleTag(readDouble());
		case FLOAT:
			return new FloatTag(readFloat());
		default:
			inputStream.close();
			throw new UnknownNbtTagType();
		}
	}

	private float readFloat() throws IOException {
		return inputStream.readFloat();
	}

	private double readDouble() throws IOException {
		return inputStream.readDouble();
	}

	private long readLong() throws IOException {
		return inputStream.readLong();
	}

	private byte readByte() throws IOException {
		return inputStream.readByte();
	}

	private short readShort() throws IOException {
		return inputStream.readShort();
	}

	private int readInt() throws IOException {
		return inputStream.readInt();
	}

	private void addToCurrent(Tag tag) {
		current.add(tag);
	}

	private void readByteTag() throws IOException {
		addToCurrent(new ByteTag(readTagName(), readByte()));
	}

	private void readShortTag() throws IOException {
		addToCurrent(new ShortTag(readTagName(), readShort()));
	}

	private void readIntTag() throws IOException {
		addToCurrent(new IntTag(readTagName(), readInt()));
	}

	private void readLongTag() throws IOException {
		addToCurrent(new LongTag(readTagName(), readLong()));
	}

	private void readFloatTag() throws IOException {
		addToCurrent(new FloatTag(readTagName(), readFloat()));
	}

	private void readDoubleTag() throws IOException {
		addToCurrent(new DoubleTag(readTagName(), readDouble()));
	}

	private void readByteArrayTag() throws IOException {
		addToCurrent(new ByteArrayTag(readTagName(), readByteArray()));
	}

	private void readIntArrayTag() throws IOException {
		addToCurrent(new IntArrayTag(readTagName(), readIntArray()));
	}

	private void readLongArrayTag() throws IOException {
		addToCurrent(new LongArrayTag(readTagName(), readLongArray()));
	}

	private void readStringTag() throws IOException {
		StringTag tag = new StringTag(readTagName(), readString());
		addToCurrent(tag);
	}

	private NbtTagType readContentType() throws IOException {
		return NbtTagType.getTypeById(readByte());
	}

	private ListTag readUnnamedListTag() throws IOException {
		ListTag list = new ListTag(readContentType());
		list.setTargetSize(readInt());
		addListItems(list);
		return list;
	}

	private void readListTag() throws IOException {
		ListTag list = new ListTag(readTagName(), readContentType());
		list.setTargetSize(readInt());
		addToCurrent(list);
		validateList(list);
		addListItems(list);
	}

	private void addListItems(ListTag list) throws IOException {
		if (list.getTargetSize() <= 0)
			return;

		if (list.getContentType() == NbtTagType.COMPOUND && list.getTargetSize() > 0) {
			Tag tag = readUnnamedTag(NbtTagType.COMPOUND);
			list.add(tag);
			setCurrentTag(tag);
		} else {
			readListItems(list);
		}
	}

	private void validateList(ListTag list) throws IOException {
		if (list.getContentType() == NbtTagType.END && list.getTargetSize() > 0) {
			close();
			throw new IOException("List content type == 'END' but size is greater than '0'.");
		}
	}

	private void readListItems(ListTag list) throws IOException {
		for (int i = 0; i < list.getTargetSize(); i++) {
			list.add(readUnnamedTag(list.getContentType()));
		}
	}

	private String readString() throws IOException {
		return inputStream.readUTF();
	}

	private String readTagName() throws IOException {
		return readString();
	}

	private byte[] readByteArray() throws IOException {
		int length = readInt();
		byte[] value = new byte[length];
		for (int i = 0; i < value.length; i++) {
			value[i] = inputStream.readByte();
		}
		return value;
	}

	private int[] readIntArray() throws IOException {
		int length = readInt();
		int[] value = new int[length];
		for (int i = 0; i < length; i++) {
			value[i] = readInt();
		}
		return value;
	}

	private long[] readLongArray() throws IOException {
		int length = readInt();
		long[] value = new long[length];
		for (int i = 0; i < length; i++) {
			value[i] = readLong();
		}
		return value;
	}

	@Override
	public void close() throws IOException {
		inputStream.close();
	}

	private void setRoot(Tag root) {
		this.root = root;
	}

	private void setCurrentTag(Tag current) {
		this.current = current;
	}

	public class UnknownNbtTagType extends RuntimeException {

		private static final long serialVersionUID = 1L;

	}

}
