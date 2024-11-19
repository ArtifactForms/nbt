package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

public class BaseTestNestedCompounds {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER + "nested_compounds.nbt";

    private static CompoundTag read() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE), compressed);
	Tag tag = reader.read();
	reader.close();
	return (CompoundTag) tag;
    }

    @Test
    public void rootHasEmptyName() throws IOException {
	CompoundTag root = read();
	Assert.assertTrue(root.getName().isEmpty());
    }

    @Test
    public void rootHasOneEntry() throws IOException {
	CompoundTag root = read();
	Assert.assertEquals(1, root.getTagCountExceptEndTags());
    }

    @Test
    public void firstNestedCompoundHasThreeEntries() throws IOException {
	CompoundTag root = read();
	CompoundTag tag = (CompoundTag) root.getTagAt(0);
	Assert.assertEquals(3, tag.getTagCountExceptEndTags());
    }

    @Test
    public void firstNestedCompoundHasNameNestedTest() throws IOException {
	CompoundTag root = read();
	CompoundTag tag = (CompoundTag) root.getTagAt(0);
	Assert.assertEquals("nestedTest", tag.getName());
    }

    @Test
    public void eachNestedCompoundNestedInTheFirstCompoundHasOneEntry() throws IOException {
	CompoundTag root = read();
	CompoundTag tag = (CompoundTag) root.getTagAt(0);
	for (int i = 0; i < tag.getTagCount(); i++) {
	    Tag tag0 = tag.getTagAt(i);
	    if (tag0.getType() != NbtTagType.END)
		Assert.assertEquals(1, tag0.getTagCountExceptEndTags());
	}
    }

}
