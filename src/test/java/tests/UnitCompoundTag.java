package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.exception.NbtException;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.EndTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

public class UnitCompoundTag {

    private static Tag createTestTag() {
	return new CompoundTag("CompoundTag");
    }

    // TODO test insert remove operations

    // TODO test add methods

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
    public void addBooleanFalseAddsByteTag() {
	CompoundTag tag = (CompoundTag) createTestTag();
	tag.add("BooleanTest", false);
	Assert.assertEquals(NbtTagType.BYTE, tag.getTagAt(0).getType());
    }

    @Test
    public void addBooleanTrueAddsByteTag() {
	CompoundTag tag = (CompoundTag) createTestTag();
	tag.add("BooleanTest", true);
	Assert.assertEquals(NbtTagType.BYTE, tag.getTagAt(0).getType());
    }

    @Test
    public void addBooleanFalseAddsByteTagWithValueZero() {
	CompoundTag tag = (CompoundTag) createTestTag();
	tag.add("BooleanTest", false);
	ByteTag byteTag = (ByteTag) tag.getTagAt(0);
	Assert.assertEquals(0, byteTag.getValue());
    }

    @Test
    public void addBooleanTrueAddsByteTagWithValueOne() {
	CompoundTag tag = (CompoundTag) createTestTag();
	tag.add("BooleanTest", true);
	ByteTag byteTag = (ByteTag) tag.getTagAt(0);
	Assert.assertEquals(1, byteTag.getValue());
    }

    @Test
    public void removeNullTagDoesNotThrowAnException() {
	Tag tag = createTestTag();
	tag.remove(null);
    }

    @Test
    public void isLeaf() {
	Tag tag = createTestTag();
	Assert.assertTrue(tag.isLeaf());
    }

    @Test
    public void allowsChildrenByDefault() {
	Tag tag = createTestTag();
	Assert.assertTrue(tag.allowsChildren());
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
    public void addingNullElementThrowsException() {
	Tag tag = createTestTag();
	tag.add(null);
    }

    @Test
    public void acceptVisitor() {
	Tag tag = createTestTag();
	TestCountVisitsVisitor visitor = new TestCountVisitsVisitor();
	tag.accept(visitor);
	Assert.assertEquals(1, visitor.getVisitedCompoundTagCount());
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

    @Test
    public void getIndexOfTagReturnsMinusOneByDefault() {
	Tag tag = createTestTag();
	Assert.assertEquals(-1, tag.getIndexOf(new EndTag()));
    }

    @Test
    public void getBranchReturnsSelfByDefault() {
	Tag tag = createTestTag();
	Assert.assertTrue(tag.getBranch() == tag);
    }

}
