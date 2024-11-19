package chunk;

import nbt.exception.NbtException;
import nbt.tags.ByteArrayTag;
import nbt.tags.ByteTag;
import nbt.tags.DoubleTag;
import nbt.tags.FloatTag;
import nbt.tags.IntArrayTag;
import nbt.tags.IntTag;
import nbt.tags.LongArrayTag;
import nbt.tags.LongTag;
import nbt.tags.NbtTagType;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;
import nbt.tags.Tag;

public class NbtFile {

    private Tag root;

    public NbtFile(Tag root) {
        this.root = root;
    }

    public Tag getTag(String name) throws NoSuchTagException {
        Tag tag = root.getTagByName(name);

        if (tag == null)
            throw new NoSuchTagException(name);

        return tag;
    }

    private Tag getTag(String name, NbtTagType type) throws NbtException {
        Tag tag = root.getTagByName(name);

        if (tag == null)
            throw new NoSuchTagException(name);

        if (tag.getType() != type)
            throw new WrongTagDataTypeException("");

        return tag;
    }

    public byte getByte(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.BYTE);
        return ((ByteTag) (tag)).getValue();
    }

    public short getShort(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.SHORT);
        return ((ShortTag) (tag)).getValue();
    }

    public int getInt(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.INT);
        return ((IntTag) (tag)).getValue();
    }

    public long getLong(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.LONG);
        return ((LongTag) (tag)).getValue();
    }

    public float getFloat(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.FLOAT);
        return ((FloatTag) (tag)).getValue();
    }

    public double getDouble(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.DOUBLE);
        return ((DoubleTag) (tag)).getValue();
    }

    public byte[] getBytes(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.BYTE_ARRAY);
        return ((ByteArrayTag) (tag)).getValue();
    }

    public String getString(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.STRING);
        return ((StringTag) (tag)).getValue();
    }

    // TODO List

    // TODO Compound

    public int[] getInts(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.INT_ARRAY);
        return ((IntArrayTag) (tag)).getValue();
    }

    public long[] getLongs(String name) throws NbtException {
        Tag tag = getTag(name, NbtTagType.LONG_ARRAY);
        return ((LongArrayTag) (tag)).getValue();
    }

    public boolean hasTag(String name) {
        Tag tag = root.getTagByName(name);
        return tag != null;
    }

    public Tag getRoot() {
        return root;
    }

    public void setRoot(Tag root) {
        this.root = root;
    }

    public class NoSuchTagException extends NbtException {

        private static final long serialVersionUID = 1L;

        public NoSuchTagException(String message) {
            super(message);
        }

    }

    public class WrongTagDataTypeException extends NbtException {

        private static final long serialVersionUID = 1L;

        public WrongTagDataTypeException(String message) {
            super(message);
        }

    }

}
