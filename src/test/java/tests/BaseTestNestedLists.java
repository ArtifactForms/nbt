package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import nbt.io.NbtReader;
import nbt.tags.CompoundTag;
import nbt.tags.IntTag;
import nbt.tags.ListTag;
import nbt.tags.NbtTagType;
import nbt.tags.StringTag;
import nbt.tags.Tag;

public class BaseTestNestedLists {

    private static boolean compressed = true;
    private static final String FILE = TestUtil.TEST_FILES_PATH_READER
            + "base_nested_lists.nbt";

    private static CompoundTag read() throws IOException {
        NbtReader reader = new NbtReader(new File(FILE), compressed);
        Tag tag = reader.read();
        reader.close();
        return (CompoundTag) tag;
    }

    public static CompoundTag getNestedCompound(int index) throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag thirdNestedList = (ListTag) mainList.getTagAt(2);
        return (CompoundTag) thirdNestedList.getTagAt(index);
    }

    @Test
    public void rootNameIsEmpty() throws IOException {
        CompoundTag root = read();
        Assert.assertTrue(root.getName().isEmpty());
    }

    @Test
    public void rootContainsOneElement() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(1, root.getTagCountExceptEndTags());
    }

    @Test
    public void firstElementIsListTag() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals(NbtTagType.LIST, root.getTagAt(0).getType());
    }

    @Test
    public void nameOfFirstListIsListMain() throws IOException {
        CompoundTag root = read();
        Assert.assertEquals("listMain", root.getTagAt(0).getName());
    }

    @Test
    public void mainListContainsThreeElements() throws IOException {
        CompoundTag root = read();
        ListTag list = (ListTag) root.getTagAt(0);
        Assert.assertEquals(3, list.getTagCountExceptEndTags());
    }

    @Test
    public void firstNestedListContainsFiveElements() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag firstNestedList = (ListTag) mainList.getTagAt(0);
        Assert.assertEquals(5, firstNestedList.getTagCountExceptEndTags());
    }

    @Test
    public void secondNestedListContainsThreeElements() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag secondNestedList = (ListTag) mainList.getTagAt(1);
        Assert.assertEquals(3, secondNestedList.getTagCountExceptEndTags());
    }

    @Test
    public void thirdNestedListContainsTwoElements() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag thirdNestedList = (ListTag) mainList.getTagAt(2);
        Assert.assertEquals(2, thirdNestedList.getTagCountExceptEndTags());
    }

    @Test
    public void firstNestedListHasContentTypeInt() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag firstNestedList = (ListTag) mainList.getTagAt(0);
        Assert.assertEquals(NbtTagType.INT, firstNestedList.getContentType());
    }

    @Test
    public void secondNestedListHasContentTypeString() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag secondNestedList = (ListTag) mainList.getTagAt(1);
        Assert.assertEquals(
                NbtTagType.STRING, secondNestedList.getContentType()
        );
    }

    @Test
    public void thirdNestedListHasContentTypeCompound() throws IOException {
        CompoundTag root = read();
        ListTag mainList = (ListTag) root.getTagAt(0);
        ListTag thirdNestedList = (ListTag) mainList.getTagAt(2);
        Assert.assertEquals(
                NbtTagType.COMPOUND, thirdNestedList.getContentType()
        );
    }

    @Test
    public void firstNestedCompoundHas3Elements() throws IOException {
        CompoundTag firstCompound = getNestedCompound(0);
        Assert.assertEquals(3, firstCompound.getTagCountExceptEndTags());
    }

    @Test
    public void secondNestedCompoundHas3Elements() throws IOException {
        CompoundTag secondCompound = getNestedCompound(1);
        Assert.assertEquals(3, secondCompound.getTagCountExceptEndTags());
    }

    @Test
    public void testXElementOfFirstCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(0);
        IntTag x = (IntTag) compoundTag.getTagByName("x");
        Assert.assertEquals(10, x.getValue());
    }

    @Test
    public void testYElementOfFirstCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(0);
        IntTag x = (IntTag) compoundTag.getTagByName("y");
        Assert.assertEquals(20, x.getValue());
    }

    @Test
    public void testNameElementOfFirstCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(0);
        StringTag name = (StringTag) compoundTag.getTagByName("name");
        Assert.assertEquals("Herobrine", name.getValue());
    }

    @Test
    public void testXElementOfSecondCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(1);
        IntTag x = (IntTag) compoundTag.getTagByName("x");
        Assert.assertEquals(40, x.getValue());
    }

    @Test
    public void testYElementOfSecondCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(1);
        IntTag x = (IntTag) compoundTag.getTagByName("y");
        Assert.assertEquals(30, x.getValue());
    }

    @Test
    public void testNameElementOfSecondCompound() throws IOException {
        CompoundTag compoundTag = getNestedCompound(1);
        StringTag name = (StringTag) compoundTag.getTagByName("name");
        Assert.assertEquals("Steve", name.getValue());
    }

}
