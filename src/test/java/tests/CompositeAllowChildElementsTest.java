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
import nbt.tags.NbtTagType;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;

public class CompositeAllowChildElementsTest {

    @Test
    public void unnamedByteArrayDoesNotAllowChildren() {
        ByteArrayTag tag = new ByteArrayTag(new byte[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedByteArrayDoesNotAllowChildren() {
        ByteArrayTag tag = new ByteArrayTag("name", new byte[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedByteTagDoesNotAllowChildren() {
        ByteTag tag = new ByteTag((byte) 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedByteTagDoesNotAllowChildren() {
        ByteTag tag = new ByteTag("name", (byte) 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedCompoundTagAllowsChildren() {
        CompoundTag tag = new CompoundTag();
        Assert.assertTrue(tag.allowsChildren());
    }

    @Test
    public void namedCompoundTagAllowsChildren() {
        CompoundTag tag = new CompoundTag("name");
        Assert.assertTrue(tag.allowsChildren());
    }

    @Test
    public void unnamedDoubleTagDoesNotAllowChildren() {
        DoubleTag tag = new DoubleTag(0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedDoubleTagDoesNotAllowChildren() {
        DoubleTag tag = new DoubleTag("name", 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void endTagDoesNotAllowChildre() {
        EndTag tag = new EndTag();
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedFloatTagDoesNotAllowChildren() {
        FloatTag tag = new FloatTag(0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedFloatTagDoesNotAllowChildren() {
        FloatTag tag = new FloatTag("name", 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedIntArrayTagDoesNotAllowChildren() {
        IntArrayTag tag = new IntArrayTag(new int[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedIntArrayTagDoesNotAllowChildren() {
        IntArrayTag tag = new IntArrayTag("name", new int[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedIntTagDoesNotAllowChildren() {
        IntTag tag = new IntTag(0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedIntTagDoesNotAllowChildren() {
        IntTag tag = new IntTag("name", 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedListTagAllowsChildren() {
        for (NbtTagType contentType : NbtTagType.values()) {
            ListTag tag = new ListTag(contentType);
            Assert.assertTrue(tag.allowsChildren());
        }
    }

    @Test
    public void namedListTagAllowsChildren() {
        for (NbtTagType contentType : NbtTagType.values()) {
            ListTag tag = new ListTag("name", contentType);
            Assert.assertTrue(tag.allowsChildren());
        }
    }

    @Test
    public void unnamedLongArrayTagDoesNotAllowChildren() {
        LongArrayTag tag = new LongArrayTag(new long[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedLongArrayTagDoesNotAllowChildren() {
        LongArrayTag tag = new LongArrayTag("name", new long[0]);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedShortTagDoesNotAllowChildren() {
        ShortTag tag = new ShortTag((short) 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedShortTagDoesNotAllowChildren() {
        ShortTag tag = new ShortTag("name", (short) 0);
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void unnamedStringTagDoesNotAllowChildren() {
        StringTag tag = new StringTag("value");
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void namedStringTagDoesNotAllowChildren() {
        StringTag tag = new StringTag("name", "value");
        Assert.assertFalse(tag.allowsChildren());
    }

}
