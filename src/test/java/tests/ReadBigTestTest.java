package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.Tag;

/**
 * Read compressed test file 'bigtest.nbt' from https://wiki.vg/NBT
 */
public class ReadBigTestTest {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER + "bigtest.nbt";

    private static CompoundTag read() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE), compressed);
	Tag tag = reader.read();
	reader.close();
	return (CompoundTag) tag;
    }

    @Test
    public void rootHasElevenElementsExceptEnd() throws IOException {
	CompoundTag root = read();
	int expected = 11;
	int actual = root.getTagCountExceptEndTags();
	Assert.assertEquals(expected, actual);
    }

}
