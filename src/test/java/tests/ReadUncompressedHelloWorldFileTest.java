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
 * Read uncompressed test file 'hello_world.nbt' from https://wiki.vg/NBT
 */
public class ReadUncompressedHelloWorldFileTest {

    private static final String FILE = TestUtil.TEST_FILES_PATH_READER + "hello_world.nbt";

    private static CompoundTag read() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE), false);
	Tag tag = reader.read();
	reader.close();
	return (CompoundTag) tag;
    }

    @Test
    public void rootNameIsHelloWorld() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals("hello world", root.getName());
    }

    @Test
    public void rootContainsOneElementAndOneEndTag() throws IOException {
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
    public void nameOfStringTagIsName() throws IOException {
	String expected = "name";
	CompoundTag root = read();
	Tag tag = (StringTag) root.getTagAt(0);
	Assert.assertEquals(expected, tag.getName());
    }

    @Test
    public void valueOfStringTagIsBananrama() throws IOException {
	String expected = "Bananrama";
	CompoundTag root = read();
	StringTag tag = (StringTag) root.getTagByName("name");
	Assert.assertEquals(expected, tag.getValue());
    }

}
