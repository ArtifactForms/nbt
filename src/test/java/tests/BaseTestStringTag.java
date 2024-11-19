package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.StringTag;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one string tag with
 * value 'This is a nice String test!' and name 'stringTest'.
 */
public class BaseTestStringTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER + "base_string_tag.nbt";

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
    public void firstElementIsStringTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.STRING, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void stringTagValueIsValue() throws IOException {
	CompoundTag root = read();
	StringTag stringTag = (StringTag) root.getTagAt(0);
	Assert.assertEquals("This is a nice String test!", stringTag.getValue());
    }

    @Test
    public void stringTagNameIsStringTest() throws IOException {
	CompoundTag root = read();
	StringTag stringTag = (StringTag) root.getTagAt(0);
	Assert.assertEquals("stringTest", stringTag.getName());
    }

}
