package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.DoubleTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one double tag with
 * value 'Double.MIN_VALUE' and name 'doubleTest'.
 */
public class BaseTestDoubleTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER
            + "base_double_tag.nbt";

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
    public void firstElementIsDoubleTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.DOUBLE, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void doubleTagValueIsMinValue() throws IOException {
        CompoundTag root = read();
        DoubleTag doubleTag = (DoubleTag) root.getTagAt(0);
        Assert.assertEquals(Double.MIN_VALUE, doubleTag.getValue(), 0.0001);
    }

    @Test
    public void doubleTagNameIsDoubleTest() throws IOException {
        CompoundTag root = read();
        DoubleTag doubleTag = (DoubleTag) root.getTagAt(0);
        Assert.assertEquals("doubleTest", doubleTag.getName());
    }

}
