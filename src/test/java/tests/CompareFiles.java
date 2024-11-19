package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.io.NbtWriter;
import nbt.tags.Tag;
import nbt.visitor.PrintPrettyTreeTagVisitor;

/**
 * Read and write the test files. Subsequently compare the newly generated files
 * with the original test files.
 */
public class CompareFiles {

    @Test
    public void compareByteTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_byte_tag.nbt");
	process(f);
    }

    @Test
    public void compareCompoundTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_compound_tag.nbt");
	process(f);
    }

    @Test
    public void compareDoubleTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_double_tag.nbt");
	process(f);
    }

    @Test
    public void compareEmptyList() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_empty_list.nbt");
	process(f);
    }

    @Test
    public void compareFloatTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_float_tag.nbt");
	process(f);
    }

    @Test
    public void compareIntTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_int_tag.nbt");
	process(f);
    }

    @Test
    public void listTypes() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_list_types.nbt");
	process(f);
    }

    @Test
    public void longArrayTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_long_array_tag.nbt");
	process(f);
    }

    @Test
    public void longTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_long_tag.nbt");
	process(f);
    }

    @Test
    public void nestedLists() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_nested_lists.nbt");
	process(f);
    }

    @Test
    public void bushSchematic() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "bush.schematic");
	process(f);
    }

    @Test
    public void clipboardSchematic() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "clipboard.schem");
	process(f);
    }

    @Test
    public void shortTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_short_tag.nbt");
	process(f);
    }

    @Test
    public void stringTag() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "base_string_tag.nbt");
	process(f);
    }

    @Test
    public void compareBigTest() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "bigtest.nbt");
	process(f);
    }

    @Test
    public void levelDat() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "level.dat");
	process(f);
    }

    @Test
    public void nestedCompounds() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "nested_compounds.nbt");
	process(f);
    }

    @Test
    public void rw() throws IOException {
	String path = TestUtil.TEST_FILES_PATH_READER;
	File f = new File(path + "rwnbttest.nbt");
	process(f);
    }

    public static void process(File f) throws IOException {
	NbtReader reader = new NbtReader(f, true);
	Tag root = reader.read();
	reader.close();
	File target = new File(TestUtil.TEST_FILES_PATH_WRITER + f.getName());
	write(target, root);
	compare(f.getName());
    }

    public static void write(File file, Tag root) throws IOException {
	NbtWriter writer = new NbtWriter(file);
	writer.write(root);
	writer.close();
    }

    public static void compare(String name) throws IOException {
	File sorce = new File(TestUtil.TEST_FILES_PATH_READER + name);
	File target = new File(TestUtil.TEST_FILES_PATH_WRITER + name);

	NbtReader sorceReader = new NbtReader(sorce);
	Tag sourceTag = sorceReader.read();
	sorceReader.close();

	NbtReader targetReader = new NbtReader(target);
	Tag targetTag = targetReader.read();
	targetReader.close();

	PrintPrettyTreeTagVisitor sorceVisitor = new PrintPrettyTreeTagVisitor();
	PrintPrettyTreeTagVisitor targetVisitor = new PrintPrettyTreeTagVisitor();

	sourceTag.accept(sorceVisitor);
	targetTag.accept(targetVisitor);

	Assert.assertTrue(sorceVisitor.getString().equals(targetVisitor.getString()));
    }

}
