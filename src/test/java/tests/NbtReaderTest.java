package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import nbt.io.NbtReader;
import nbt.io.NbtReader.UnknownNbtTagType;

public class NbtReaderTest {

    @Test(expected = IOException.class)
    public void listWithContentTypeEndAndSizeGreaterZeroThrowsException() throws IOException {
	NbtReader reader = new NbtReader(new File("./test/files/listexception.nbt"));
	reader.read();
    }

    @Test(expected = UnknownNbtTagType.class)
    public void tagWithTagTypeFifteenThrowsUnknownTagTypeException() throws IOException {
	NbtReader reader = new NbtReader(new File("./test/files/unknown_tag_type_15_exception.nbt"));
	reader.read();
    }

}
