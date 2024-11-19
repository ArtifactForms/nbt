package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import chunk.NbtFile;
import nbt.io.NbtReader;
import nbt.tags.ListTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

public class ReadBushSchematicAsNbtFileTest {

    private static final String FILE = TestUtil.TEST_FILES_PATH + "bush.schematic";

    private static NbtFile read() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE));
	Tag tag = reader.read();
	reader.close();
	return new NbtFile(tag);
    }

    private static Tag readAsTag() throws IOException {
	NbtReader reader = new NbtReader(new File(FILE));
	Tag tag = reader.read();
	reader.close();
	return tag;
    }

    @Test
    public void getWidth() throws IOException {
	NbtFile schematic = read();
	short expected = 15;
	short actual = schematic.getShort("Width");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHeight() throws IOException {
	NbtFile schematic = read();
	short expected = 28;
	short actual = schematic.getShort("Height");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLength() throws IOException {
	NbtFile schematic = read();
	short expected = 15;
	short actual = schematic.getShort("Length");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOffsetX() throws IOException {
	NbtFile schematic = read();
	int expected = -7;
	int actual = schematic.getInt("WEOffsetX");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOffsetY() throws IOException {
	NbtFile schematic = read();
	int expected = 0;
	int actual = schematic.getInt("WEOffsetY");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOffsetZ() throws IOException {
	NbtFile schematic = read();
	int expected = -7;
	int actual = schematic.getInt("WEOffsetZ");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOriginX() throws IOException {
	NbtFile schematic = read();
	int expected = -314;
	int actual = schematic.getInt("WEOriginX");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOriginY() throws IOException {
	NbtFile schematic = read();
	int expected = 11;
	int actual = schematic.getInt("WEOriginY");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWorldEditOriginZ() throws IOException {
	NbtFile schematic = read();
	int expected = -490;
	int actual = schematic.getInt("WEOriginZ");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMaterials() throws IOException {
	NbtFile schematic = read();
	String expected = "Alpha";
	String actual = schematic.getString("Materials");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPlatform() throws IOException {
	NbtFile schematic = read();
	String expected = "bukkit";
	String actual = schematic.getString("Platform");
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void blocksLength() throws IOException {
	NbtFile schematic = read();
	int expected = 6300;
	int actual = schematic.getBytes("Blocks").length;
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void dataLength() throws IOException {
	NbtFile schematic = read();
	int expected = 6300;
	int actual = schematic.getBytes("Data").length;
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void rootNameIsSchematic() throws IOException {
	Tag root = readAsTag();
	String expected = "Schematic";
	String actual = root.getName();
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void rootContainsFifteenEntriesExceptedEndTag() throws IOException {
	Tag root = readAsTag();
	int expected = 15;
	int actual = root.getTagCountExceptEndTags();
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void rootContainsOneEndTag() throws IOException {
	Tag root = readAsTag();
	int expected = 1;
	int actual = 0;
	for (int i = 0; i < root.getTagCount(); i++) {
	    Tag tag = root.getTagAt(i);
	    if (tag.getType() == NbtTagType.END)
		actual++;
	}
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void containsEntitiesList() throws IOException {
	Tag root = readAsTag();
	Tag list = root.getTagByName("Entities");
	Assert.assertTrue(list.isList());
    }

    @Test
    public void containsTileEntitiesList() throws IOException {
	Tag root = readAsTag();
	Tag list = root.getTagByName("TileEntities");
	Assert.assertTrue(list.isList());
    }

    @Test
    public void rootIsParentOfEntitiesList() throws IOException {
	Tag root = readAsTag();
	Tag list = root.getTagByName("Entities");
	String expected = "Schematic";
	String actual = list.getParent().getName();
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void rootIsParentOfTileEntitiesList() throws Exception {
	Tag root = readAsTag();
	Tag list = root.getTagByName("TileEntities");
	String expected = "Schematic";
	String actual = list.getParent().getName();
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void tileEntitiesListContentTypeIsCompound() throws IOException {
	Tag root = readAsTag();
	ListTag list = (ListTag) root.getTagByName("TileEntities");
	NbtTagType expected = NbtTagType.COMPOUND;
	NbtTagType actual = list.getContentType();
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void entitiesListContentTypeIsCompound() throws IOException {
	Tag root = readAsTag();
	ListTag list = (ListTag) root.getTagByName("Entities");
	NbtTagType expected = NbtTagType.COMPOUND;
	NbtTagType actual = list.getContentType();
	Assert.assertEquals(expected, actual);
    }

}
