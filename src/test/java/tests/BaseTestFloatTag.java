package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.FloatTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one float tag with
 * value 'Float.MIN_VALUE' and name 'floatTest'.
 */
public class BaseTestFloatTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER
            + "base_float_tag.nbt";

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
    public void firstElementIsFloatTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.FLOAT, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void floatTagValueIsMinValue() throws IOException {
        CompoundTag root = read();
        FloatTag floatTag = (FloatTag) root.getTagAt(0);
        Assert.assertEquals(Float.MIN_VALUE, floatTag.getValue(), 0.0001f);
    }

    @Test
    public void floatTagNameIsFloatTest() throws IOException {
        CompoundTag root = read();
        FloatTag floatTag = (FloatTag) root.getTagAt(0);
        Assert.assertEquals("floatTest", floatTag.getName());
    }

}
