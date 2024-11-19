package tests;

import org.junit.Assert;
import org.junit.Test;

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

public class NbtTagIdsTest {

    @Test
    public void endTagId() {
        EndTag tag = new EndTag();
        NbtTagType expected = NbtTagType.END;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void byteTagId() {
        ByteTag tag = new ByteTag("Byte", (byte) 0);
        NbtTagType expected = NbtTagType.BYTE;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shortTagId() {
        ShortTag tag = new ShortTag("Short", (byte) 0);
        NbtTagType expected = NbtTagType.SHORT;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void intTagId() {
        IntTag tag = new IntTag("Int", 0);
        NbtTagType expected = NbtTagType.INT;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void longTagId() {
        LongTag tag = new LongTag("Long", 0);
        NbtTagType expected = NbtTagType.LONG;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void floatTagId() {
        FloatTag tag = new FloatTag("Float", 0);
        NbtTagType expected = NbtTagType.FLOAT;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doubleTagId() {
        DoubleTag tag = new DoubleTag("Double", 0);
        NbtTagType expected = NbtTagType.DOUBLE;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void byteArrayTagId() {
        ByteArrayTag tag = new ByteArrayTag("ByteArray", new byte[0]);
        NbtTagType expected = NbtTagType.BYTE_ARRAY;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringTagId() {
        StringTag tag = new StringTag("String", "");
        NbtTagType expected = NbtTagType.STRING;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void listTagId() {
        ListTag tag = new ListTag("List", NbtTagType.COMPOUND);
        NbtTagType expected = NbtTagType.LIST;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compoundTagId() {
        CompoundTag tag = new CompoundTag("Compound");
        NbtTagType expected = NbtTagType.COMPOUND;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void intArrayTagId() {
        IntArrayTag tag = new IntArrayTag("IntArray", new int[0]);
        NbtTagType expected = NbtTagType.INT_ARRAY;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void longArrayTagId() {
        LongArrayTag tag = new LongArrayTag("LongArray", new long[0]);
        NbtTagType expected = NbtTagType.LONG_ARRAY;
        NbtTagType actual = tag.getType();
        Assert.assertEquals(expected, actual);
    }

}
