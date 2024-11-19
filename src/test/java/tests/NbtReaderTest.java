package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import nbt.io.NbtReader;
import nbt.io.NbtReader.UnknownNbtTagType;

public class NbtReaderTest {

    @Test(expected = IOException.class)
    public void listWithContentTypeEndAndSizeGreaterZeroThrowsException()
            throws IOException {
        String path = TestUtil.TEST_FILES_PATH + "listexception.nbt";
        NbtReader reader = new NbtReader(new File(path));
        reader.read();
        reader.close();
    }

    @Test(expected = UnknownNbtTagType.class)
    public void tagWithTagTypeFifteenThrowsUnknownTagTypeException()
            throws IOException {
        String path = TestUtil.TEST_FILES_PATH
                + "unknown_tag_type_15_exception.nbt";
        NbtReader reader = new NbtReader(new File(path));
        reader.read();
        reader.close();
    }

}
