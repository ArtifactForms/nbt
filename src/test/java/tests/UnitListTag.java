package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.exception.NbtException;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.IntTag;
import nbt.tags.ListTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

public class UnitListTag {

    private static Tag createTestTag() {
        return new ListTag("IntTag", NbtTagType.BYTE);
    }

    // TODO add named compound to list of compounds -> exception
    // TODO list target size add tags
    // TODO add tag that is already added to another tag

    @Test
    public void removeNotContainigElementsKeepsTheOriginalParent() {
        IntTag intTag = new IntTag(100);
        CompoundTag compoundTag = new CompoundTag();
        ListTag list = new ListTag(NbtTagType.INT);
        compoundTag.add(intTag);
        list.remove(intTag);
        Assert.assertTrue(compoundTag == intTag.getParent());
    }

    @Test
    public void isLeafReturnsTrueByDefautl() {
        Tag tag = createTestTag();
        Assert.assertEquals(true, tag.isLeaf());
    }

    @Test
    public void isLeafReturnsFalseAfterAddingElement() {
        Tag tag = createTestTag();
        tag.add(new ByteTag((byte) 10));
        Assert.assertEquals(false, tag.isLeaf());
    }

    @Test
    public void removeTag() {
        IntTag tag = new IntTag(10);
        ListTag list = new ListTag(NbtTagType.INT);
        list.add(tag);
        list.remove(tag);
        Assert.assertEquals(0, list.getTagCount());
    }

    @Test(expected = NbtException.class)
    public void addWrongContentTypeThrowsExceptiokn() {
        Tag tag = createTestTag();
        tag.add(new IntTag(100));
    }

    @Test
    public void compoundListGetSize() {
        ListTag list = new ListTag(NbtTagType.COMPOUND);

        CompoundTag compoundTag0 = new CompoundTag();
        compoundTag0.addEnd();
        list.add(compoundTag0);

        CompoundTag compoundTag1 = new CompoundTag();
        compoundTag1.addEnd();
        list.add(compoundTag1);

        Assert.assertEquals(2, list.getSize());
    }

    @Test
    public void isListReturnsTrueByDefault() {
        Tag tag = createTestTag();
        Assert.assertEquals(true, tag.isList());
    }

    @Test
    public void allowsChildrenRetrunsTrueByDefault() {
        Tag tag = createTestTag();
        Assert.assertEquals(true, tag.allowsChildren());
    }

    @Test
    public void getTargetSizeReturnsZeroByDefault() {
        ListTag tag = (ListTag) createTestTag();
        Assert.assertEquals(0, tag.getTargetSize());
    }

    @Test
    public void getSetTargetSize() {
        ListTag tag = (ListTag) createTestTag();
        tag.setTargetSize(100);
        Assert.assertEquals(100, tag.getTargetSize());
    }

    @Test
    public void getContentTypeReturnsConstructorValue() {
        ListTag tag = (ListTag) createTestTag();
        Assert.assertEquals(NbtTagType.BYTE, tag.getContentType());
    }

    @Test
    public void getSetContentType() {
        ListTag tag = (ListTag) createTestTag();
        tag.setContentType(NbtTagType.BYTE_ARRAY);
        Assert.assertEquals(NbtTagType.BYTE_ARRAY, tag.getContentType());
    }

}
