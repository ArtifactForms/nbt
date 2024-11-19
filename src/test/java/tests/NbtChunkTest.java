package tests;

import org.junit.Assert;
import org.junit.Test;

import chunk.NbtChunk;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.ListTag;

public class NbtChunkTest {

    private static NbtChunk createFilledChunk() {
	NbtChunk chunk = NbtChunk.createNbtChunk(0, 0);
	for (int y = 0; y < 256; y++) {
	    for (int x = 0; x < 16; x++) {
		for (int z = 0; z < 16; z++) {
		    chunk.setBlockAt(x, y, z, (byte) 1);
		}
	    }
	}
	return chunk;
    }

    @Test
    public void sectionIndexNotNullAfterAddingSection() {
	CompoundTag section = NbtChunk.createSection(0);
	ByteTag sectionIndex = (ByteTag) section.getTagByName("Y");
	Assert.assertNotNull(sectionIndex);
    }

    @Test
    public void chunkHasEmptySectionsListByDefault() {
	NbtChunk chunk = NbtChunk.createNbtChunk(0, 0);
	for (int i = 0; i < 16; i++) {
	    CompoundTag section = chunk.getSection(i);
	    Assert.assertNull(section);
	}
    }

    @Test
    public void addSectionNotNullAfterAddingSection() {
	NbtChunk chunk = NbtChunk.createNbtChunk(0, 0);
	ListTag sectionsList = (ListTag) chunk.getRoot().getTagByName("Sections");
	CompoundTag section = NbtChunk.createSection(0);
	sectionsList.add(section);
	Assert.assertNotNull(chunk.getSection(0));
    }

    @Test
    public void sectionNotNullAfterAddingBlock() {
	NbtChunk chunk = NbtChunk.createNbtChunk(0, 0);
	chunk.setBlockAt(0, 0, 0, (byte) 1);
	CompoundTag section = chunk.getSection(0);
	Assert.assertNotNull(section);
    }

    @Test
    public void sectionsListContainsZeroElementsByDefault() {
	NbtChunk chunk = NbtChunk.createNbtChunk(0, 0);
	ListTag sectionsList = (ListTag) chunk.getRoot().getTagByName("Sections");
	Assert.assertEquals(0, sectionsList.getTagCount());
    }

    @Test
    public void sectionsListContainsSixteenElementsAfterFilling() {
	NbtChunk chunk = createFilledChunk();
	ListTag sections = (ListTag) chunk.getRoot().getTagByName("Sections");
	Assert.assertEquals(16, sections.getTagCount());
    }

    @Test
    public void sectionIndicesAreNotEqualAfterFilling() {
	NbtChunk chunk = createFilledChunk();
	ListTag sections = (ListTag) chunk.getRoot().getTagByName("Sections");
	for (int i = 0; i < 16; i++) {
	    CompoundTag section = (CompoundTag) sections.getTagAt(i);
	    ByteTag sectionIndex = (ByteTag) section.getTagByName("Y");
	    Assert.assertEquals(i, sectionIndex.getValue());
	}
    }

}
