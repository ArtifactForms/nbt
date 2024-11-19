package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one byte tag with
 * value '20' and name 'byteTest'.
 */
public class BaseTestByteTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER
            + "base_byte_tag.nbt";

    private static CompoundTag read() throws IOException {
        NbtReader reader = new NbtReader(new File(FILE), compressed);
        Tag tag = reader.read();
        reader.close();
        return (CompoundTag) tag;
    }

    @Test
    public void rootNameIsEmpty() throws IOException {
        CompoundTag root = read();
        Assert.assertTrue(root.getName().isEmpty());
    }

    @Test
    public void rootContainsTwoElements() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(2, root.getTagCount());
    }

    @Test
    public void firstElementIsByteTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.BYTE, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void byteTagValueIsTwenty() throws IOException {
        CompoundTag root = read();
        ByteTag byteTag = (ByteTag) root.getTagAt(0);
        Assert.assertEquals(20, byteTag.getValue());
    }

    @Test
    public void byteTagNameIsByteTest() throws IOException {
        CompoundTag root = read();
        ByteTag byteTag = (ByteTag) root.getTagAt(0);
        Assert.assertEquals("byteTest", byteTag.getName());
    }

}
