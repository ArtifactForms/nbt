package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.ByteArrayTag;
import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one byte array tag
 * with length of '2048' and name 'byteArrayTest'.
 */
public class BaseTestByteArray {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER
            + "base_byte_array_tag.nbt";

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
    public void firstElementIsByteArrayTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.BYTE_ARRAY, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void lengthIs2048() throws IOException {
        CompoundTag root = read();
        ByteArrayTag byteArrayTag = (ByteArrayTag) root.getTagAt(0);
        Assert.assertEquals(2048, byteArrayTag.getValue().length);
    }

    @Test
    public void byteArrayTagNameIsByteArrayTest() throws IOException {
        CompoundTag root = read();
        ByteArrayTag byteArrayTag = (ByteArrayTag) root.getTagAt(0);
        Assert.assertEquals("byteArrayTest", byteArrayTag.getName());
    }

}
