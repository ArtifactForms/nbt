package tests;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import schematic.Material;
import schematic.Schematic;
import schematic.SchematicException;

public class SchematicTest {

    private static Schematic loadTestSchematic() throws IOException {
	Schematic schematic = new Schematic(new File(TestUtil.TEST_FILES_PATH + "bush.schematic"));
	return schematic;
    }

    @Test(expected = SchematicException.class)
    public void createSchematicWithBlockCountGreaterThanMaxBlockCountThrowsException() {
	Schematic.createSchematic(Short.MAX_VALUE + 1, Short.MAX_VALUE, Short.MAX_VALUE);
    }

    @Test(expected = SchematicException.class)
    public void widthGreaterThanShortMaxValueThrowsExeption() {
	Schematic.createSchematic((Short.MAX_VALUE + 1), 0, 0);
    }

    @Test(expected = SchematicException.class)
    public void heightGreaterThanShortMaxValueThrowsExeption() {
	Schematic.createSchematic(0, Short.MAX_VALUE + 1, 0);
    }

    @Test(expected = SchematicException.class)
    public void lengthGreaterThanShortMaxValueThrowsExeption() {
	Schematic.createSchematic(0, 0, Short.MAX_VALUE + 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWithXLessThanZeroThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(-1, 0, 0, Material.STONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWithYLessThanZeroThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(0, -1, 0, Material.STONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWithZLessThanZeroThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(0, 0, -1, Material.STONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWithXEqualToWidthThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(10, 0, 0, Material.STONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWitYEqualToHeightThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(0, 10, 0, Material.STONE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setBlockWithZEqualToLengthThrowsException() {
	Schematic schematic = Schematic.createSchematic(10, 10, 10);
	schematic.setBlockAt(0, 0, 10, Material.STONE);
    }

    @Test
    public void getPlatformReturnsEmptyStringByDefault() {
	Schematic schematic = Schematic.createSchematic((short) 0, (short) 0, (short) 0);
	Assert.assertEquals("", schematic.getPlatform());
    }

    @Test
    public void maxBlocksCount() {
	int actual = Schematic.MAX_BLOCK_COUNT;
	int expected = Short.MAX_VALUE * Short.MAX_VALUE * Short.MAX_VALUE;
	Assert.assertEquals(expected, actual);
    }

    @Test
    public void getWidth() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals(15, schematic.getWidth());
    }

    @Test
    public void getHeigth() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals(28, schematic.getHeight());
    }

    @Test
    public void getLength() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals(15, schematic.getLength());
    }

    @Test
    public void getMaterials() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals("Alpha", schematic.getMaterials());
    }

    @Test
    public void getPlatform() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals("bukkit", schematic.getPlatform());
    }

    @Test
    public void getBlocks() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals(6300, schematic.getBlocks().length);
    }

    @Test
    public void getData() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertEquals(6300, schematic.getData().length);
    }

    @Test
    public void hasTileEntities() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertFalse(schematic.hasTileEntities());
    }

    @Test
    public void hasEntities() throws IOException {
	Schematic schematic = loadTestSchematic();
	Assert.assertFalse(schematic.hasEntities());
    }

    @Test(expected = SchematicException.class)
    public void invalidRootNameThrowsException() throws IOException {
	new Schematic(new File("./test/files/schematic_invalid_root.schematic"));
    }

}
