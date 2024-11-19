package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.tags.CompoundTag;

public class CompoundAddTagTest {

    @Test
    public void compoundTagContainsNoTagsByDefault() {
        CompoundTag compoundTag = new CompoundTag();
        Assert.assertEquals(0, compoundTag.getTagCount());
    }

    @Test
    public void compoundTagContainsOneElementAfterAddString() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.add("testName", "testValue");
        Assert.assertEquals(1, compoundTag.getTagCount());
    }

    @Test
    public void compoundTagContainsOneElementAfterAddByte() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.add("testByte", Byte.MAX_VALUE);
        Assert.assertEquals(1, compoundTag.getTagCount());
    }

    @Test
    public void compoundTagContainsOneElementAfterAddDouble() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.add("testDouble", Double.MAX_VALUE);
        Assert.assertEquals(1, compoundTag.getTagCount());
    }

    @Test
    public void compoundTagContainsOneElementAfterAddInt() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.add("testInt", Integer.MAX_VALUE);
        Assert.assertEquals(1, compoundTag.getTagCount());
    }

    @Test
    public void compoundTagContainsOneElementAfterAddLong() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.add("testInt", Long.MAX_VALUE);
        Assert.assertEquals(1, compoundTag.getTagCount());
    }

}
