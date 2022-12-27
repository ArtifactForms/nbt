package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.Tag;
import nbt.visitor.PrintPrettyTreeTagVisitor;

public class ReadLevelDat1_12_2_Client_SurvivalTest {

    private static final String FILE = TestUtil.TEST_FILES_PATH + "level.dat";

    private static CompoundTag read() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE), true);
	Tag tag = reader.read();
	reader.close();
	return (CompoundTag) tag;
    }

    @Test
    public void readTest() throws IOException {
	PrintPrettyTreeTagVisitor visitor = new PrintPrettyTreeTagVisitor(true);
	CompoundTag root = read();
	root.accept(visitor);
	// System.out.println(visitor.getString());
	// TODO implement test cases
    }

}
