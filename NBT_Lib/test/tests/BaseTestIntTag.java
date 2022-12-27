package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.IntTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

/**
 * Read gzip compressed file with a root compound containing one int tag with
 * value '304' and name 'intTest'.
 */
public class BaseTestIntTag {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH + "base_int_tag.nbt";

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
    public void firstElementIsIntTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.INT, root.getTagAt(0).getType());
    }

    @Test
    public void secondElementIsEndTag() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(NbtTagType.END, root.getTagAt(1).getType());
    }

    @Test
    public void intTagValueIs304() throws IOException {
	CompoundTag root = read();
	IntTag intTag = (IntTag) root.getTagAt(0);
	Assert.assertEquals(304, intTag.getValue());
    }

    @Test
    public void intTagNameIsInTest() throws IOException {
	CompoundTag root = read();
	IntTag intTag = (IntTag) root.getTagAt(0);
	Assert.assertEquals("intTest", intTag.getName());
    }

}
