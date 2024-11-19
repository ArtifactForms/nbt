package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.tags.NbtTagType;

public class UnitNbtTagType {

    @Test
    public void thrirteenTagTypesPlusOneUnknownInTotal() {
	Assert.assertEquals(14, NbtTagType.values().length);
    }

    @Test
    public void getTypeForIdGreaterThanTwelveReturnsUnknown() {
	int unknownCount = 0;

	for (int i = 13; i < Byte.MAX_VALUE; i++) {
	    unknownCount += NbtTagType.getTypeById((byte) i) == NbtTagType.UNKNOWN ? 1 : 0;
	}

	Assert.assertEquals(Byte.MAX_VALUE - 13, unknownCount);
    }

    @Test
    public void getTypeForIdLessThanZeroReturnsUnknown() {
	int unknownCount = 0;

	for (int i = Byte.MIN_VALUE; i < 0; i++)
	    unknownCount += NbtTagType.getTypeById((byte) i) == NbtTagType.UNKNOWN ? 1 : 0;

	Assert.assertEquals(Math.abs(Byte.MIN_VALUE), unknownCount);
    }

    @Test
    public void getTypeForIdBetweenZeroAndTwelveReturnsType() {
	int unkownCount = 0;

	for (int i = 0; i < 13; i++) {
	    NbtTagType type = NbtTagType.getTypeById((byte) i);
	    unkownCount += type == null ? 1 : 0;
	    unkownCount += type == NbtTagType.UNKNOWN ? 1 : 0;
	}

	Assert.assertEquals(0, unkownCount);
    }

    @Test
    public void endId() {
	Assert.assertEquals(0, NbtTagType.END.getId());
    }

    @Test
    public void byteId() {
	Assert.assertEquals(1, NbtTagType.BYTE.getId());
    }

    @Test
    public void shortId() {
	Assert.assertEquals(2, NbtTagType.SHORT.getId());
    }

    @Test
    public void intId() {
	Assert.assertEquals(3, NbtTagType.INT.getId());
    }

    @Test
    public void longId() {
	Assert.assertEquals(4, NbtTagType.LONG.getId());
    }

    @Test
    public void floatId() {
	Assert.assertEquals(5, NbtTagType.FLOAT.getId());
    }

    @Test
    public void doubleId() {
	Assert.assertEquals(6, NbtTagType.DOUBLE.getId());
    }

    @Test
    public void byteArrayId() {
	Assert.assertEquals(7, NbtTagType.BYTE_ARRAY.getId());
    }

    @Test
    public void stringId() {
	Assert.assertEquals(8, NbtTagType.STRING.getId());
    }

    @Test
    public void listId() {
	Assert.assertEquals(9, NbtTagType.LIST.getId());
    }

    @Test
    public void compoundId() {
	Assert.assertEquals(10, NbtTagType.COMPOUND.getId());
    }

    @Test
    public void intArrayId() {
	Assert.assertEquals(11, NbtTagType.INT_ARRAY.getId());
    }

    @Test
    public void longArrayId() {
	Assert.assertEquals(12, NbtTagType.LONG_ARRAY.getId());
    }

}
