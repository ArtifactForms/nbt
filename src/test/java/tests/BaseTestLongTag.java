package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.LongTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one long tag with
 * value 'Long.MAX_VALUE' and name 'longTest'.
 */
public class BaseTestLongTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER + "base_long_tag.nbt";

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
    public void firstElementIsLongTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.LONG, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void longTagValueIsMaxValue() throws IOException {
	CompoundTag root = read();
	LongTag longTag = (LongTag) root.getTagAt(0);
	Assert.assertEquals(Long.MAX_VALUE, longTag.getValue());
    }

    @Test
    public void longTagNameIsLongTest() throws IOException {
	CompoundTag root = read();
	LongTag longTag = (LongTag) root.getTagAt(0);
	Assert.assertEquals("longTest", longTag.getName());
    }

}
