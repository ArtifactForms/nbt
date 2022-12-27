package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.tags.ByteArrayTag;
import nbt.tags.IntArrayTag;
import nbt.tags.StringTag;

public class GetSetTagValues {

    private static final String TEST_VALUE = "This is a nice Test String!ÄÖÜ";

    @Test
    public void unnamedStringTagReturnsEmptyNameByDefault() {
	String value = TEST_VALUE;
	StringTag tag = new StringTag(value);
	Assert.assertEquals("", tag.getName());
    }

    @Test
    public void getValueFromUnnamedStringTag() {
	String value = TEST_VALUE;
	StringTag tag = new StringTag(value);
	Assert.assertEquals(value, tag.getValue());
    }

    @Test
    public void getValueFromNamedStringTag() {
	String value = TEST_VALUE;
	StringTag tag = new StringTag("name", value);
	Assert.assertEquals(value, tag.getValue());
    }

    @Test
    public void unnamedByteArrayTagReturnsConstructorValue() {
	byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	ByteArrayTag tag = new ByteArrayTag(bytes);
	Assert.assertEquals(bytes, tag.getValue());
    }

    @Test
    public void namedByteArrayTagReturnsConstructorValue() {
	byte[] bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	ByteArrayTag tag = new ByteArrayTag("name", bytes);
	Assert.assertEquals(bytes, tag.getValue());
    }

    @Test
    public void getSetValueOfStringTag() {
	StringTag tag = new StringTag("");
	tag.setValue(TEST_VALUE);
	Assert.assertEquals(TEST_VALUE, tag.getValue());
    }

    @Test
    public void getSetValueOfByteArrayTag() {
	byte[] bytes = new byte[0];
	byte[] bytesNew = new byte[] { 1, 2, 3, 4, 5, 6, 7 };
	ByteArrayTag tag = new ByteArrayTag(bytes);
	tag.setValue(bytesNew);
	Assert.assertEquals(bytesNew, tag.getValue());
    }

    @Test
    public void getSetValueOfInArrayTag() {
	int[] ints = new int[0];
	int[] newInts = new int[] { 100, 200, 300 };
	IntArrayTag tag = new IntArrayTag(ints);
	tag.setValue(newInts);
	Assert.assertEquals(newInts, tag.getValue());
    }

}
