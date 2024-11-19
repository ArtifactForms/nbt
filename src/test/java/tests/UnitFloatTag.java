package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.exception.NbtException;
import nbt.tags.CompoundTag;
import nbt.tags.EndTag;
import nbt.tags.FloatTag;
import nbt.tags.Tag;

public class UnitFloatTag {

    private static Tag createTestTag() {
        return new FloatTag("FloatTag", 0.0f);
    }

    @Test
    public void isListReturnsFalse() {
        Tag tag = createTestTag();
        Assert.assertEquals(false, tag.isList());
    }

    @Test
    public void getTagCountIsZeroByDefault() {
        Tag tag = createTestTag();
        Assert.assertEquals(0, tag.getTagCount());
    }

    @Test
    public void getSetValue() {
        float newValue = 100.22f;
        FloatTag tag = (FloatTag) createTestTag();
        tag.setValue(newValue);
        Assert.assertEquals(newValue, tag.getValue(), 0.001f);
    }

    @Test
    public void isLeaf() {
        Tag tag = createTestTag();
        Assert.assertTrue(tag.isLeaf());
    }

    @Test
    public void doesNotAllowChildren() {
        Tag tag = createTestTag();
        Assert.assertFalse(tag.allowsChildren());
    }

    @Test
    public void tagCountIsZero() {
        Tag tag = createTestTag();
        Assert.assertEquals(0, tag.getTagCount());
    }

    @Test
    public void getParentReturnsNullByDefault() {
        Tag tag = createTestTag();
        Assert.assertNull(tag.getParent());
    }

    @Test
    public void hasParentIsFalseByDefault() {
        Tag tag = createTestTag();
        Assert.assertFalse(tag.hasParent());
    }

    @Test
    public void getSetName() {
        String newName = "NewTestName";
        Tag tag = createTestTag();
        tag.setName(newName);
        Assert.assertEquals(newName, tag.getName());
    }

    @Test
    public void isRootByDefault() {
        Tag tag = createTestTag();
        Assert.assertTrue(tag.isRoot());
    }

    @Test
    public void isNotAList() {
        Tag tag = createTestTag();
        Assert.assertFalse(tag.isList());
    }

    @Test
    public void hasParentAfterAddingToCompound() {
        CompoundTag compoundTag = new CompoundTag();
        Tag tag = createTestTag();
        compoundTag.add(tag);
        compoundTag.add(new EndTag());
        Assert.assertTrue(tag.hasParent());
    }

    @Test
    public void isNotRootAfterAddingToCompound() {
        CompoundTag compoundTag = new CompoundTag();
        Tag tag = createTestTag();
        compoundTag.add(tag);
        compoundTag.add(new EndTag());
        Assert.assertFalse(tag.isRoot());
    }

    @Test(expected = NbtException.class)
    public void addingChildElementThrowsException() {
        Tag tag = createTestTag();
        tag.add(createTestTag());
    }

    @Test(expected = NbtException.class)
    public void addingNullElementThrowsException() {
        Tag tag = createTestTag();
        tag.add(null);
    }

    @Test
    public void acceptVisitor() {
        Tag tag = createTestTag();
        TestCountVisitsVisitor visitor = new TestCountVisitsVisitor();
        tag.accept(visitor);
        Assert.assertEquals(1, visitor.getVisitedFloatTagCount());
        Assert.assertEquals(1, visitor.getVisitedTotal());
    }

    @Test
    public void getRootReturnsSelfByDefault() {
        Tag tag = createTestTag();
        Assert.assertTrue(tag.getRoot() == tag);
    }

    @Test
    public void getRootReturnsCompoundAfterAddedTo() {
        CompoundTag compoundTag = new CompoundTag();
        Tag tag = createTestTag();
        compoundTag.add(tag);
        compoundTag.add(new EndTag());
        Assert.assertTrue(compoundTag == tag.getRoot());
    }

    @Test(expected = NbtException.class)
    public void removeTagThrowsException() {
        Tag tag = createTestTag();
        tag.remove(new EndTag());
    }

    @Test(expected = NbtException.class)
    public void removeNullTagThrowsException() {
        Tag tag = createTestTag();
        tag.remove(null);
    }

    @Test
    public void getIndexOfTagReturnsMinusOneByDefault() {
        Tag tag = createTestTag();
        Assert.assertEquals(-1, tag.getIndexOf(new EndTag()));
    }

    @Test(expected = NbtException.class)
    public void getBranchThrowsExceptionByDefault() {
        Tag tag = createTestTag();
        tag.getBranch();
    }

}
