package schematic;

import java.io.File;
import java.io.IOException;

import nbt.io.NbtReader;
import nbt.io.NbtWriter;
import nbt.tags.ByteArrayTag;
import nbt.tags.CompoundTag;
import nbt.tags.ListTag;
import nbt.tags.NbtTagType;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;
import nbt.tags.Tag;
import schematic.validation.SchematicValidationResult;
import schematic.validation.SchematicValidator;

public class Schematic {

    public static final int MAX_BLOCK_COUNT = Short.MAX_VALUE * Short.MAX_VALUE
            * Short.MAX_VALUE;

    private static final String ROOT_NAME = "Schematic";
    
    private static final String WIDTH = "Width";
    
    private static final String HEIGHT = "Height";
    
    private static final String LENGTH = "Length";
    
    private static final String MATERIALS = "Materials";
    
    private static final String PLATFORM = "Platform";
    
    private static final String BLOCKS = "Blocks";
    
    private static final String DATA = "Data";
    
    private static final String TILE_ENTITIES = "TileEntities";
    
    private static final String ENTITIES = "Entities";

    private byte[] blocks;
    private byte[] data;

    private CompoundTag root;

    private Schematic(CompoundTag root) {
        validate(root);
        this.root = root;
        init();
    }

    public Schematic(File file) throws IOException {
        NbtReader reader = new NbtReader(file);
        root = (CompoundTag) reader.read();
        reader.close();
        validate(root);
        init();
    }

    public static Schematic createSchematic(int width, int height, int length) {
        validateSchematicSize(width, height, length);
        validateMaxBlockCount(width, height, length);
        return createSchematic((short) width, (short) height, (short) length);
    }

    private static Schematic createSchematic(short width, short height,
            short length) {
        int blockCount = width * height * length;
        CompoundTag root = new CompoundTag(ROOT_NAME);
        root.add(WIDTH, width);
        root.add(HEIGHT, height);
        root.add(LENGTH, length);
        root.add(BLOCKS, new byte[blockCount]);
        root.add(DATA, new byte[blockCount]);
        root.add(MATERIALS, "Alpha");
        root.add(new ListTag(ENTITIES, NbtTagType.COMPOUND));
        root.add(new ListTag(TILE_ENTITIES, NbtTagType.COMPOUND));
        root.addEnd();
        validate(root);
        return new Schematic(root);
    }

    private void init() {
        blocks = ((ByteArrayTag) root.getTagByName(BLOCKS)).getValue();
        data = ((ByteArrayTag) root.getTagByName(DATA)).getValue();
    }

    private static void validateMaxBlockCount(int width, int height,
            int length) {
        int blockCount = width * height * length;

        if (blockCount > MAX_BLOCK_COUNT)
            throw new SchematicException(
                    "Block count ist greater than MAX_BLOCK_COUNT: "
                            + MAX_BLOCK_COUNT
            );
    }

    private static void validateSchematicSize(int width, int height,
            int length) {
        if (width > Short.MAX_VALUE)
            throw new SchematicException(
                    "Width is greater than Short.MAX_VALUE."
            );

        if (height > Short.MAX_VALUE)
            throw new SchematicException(
                    "Height is greater than Short.MAX_VALUE."
            );

        if (length > Short.MAX_VALUE)
            throw new SchematicException(
                    "Length is greater than Short.MAX_VALUE."
            );
    }

    private static void validate(CompoundTag root) {
        SchematicValidator validator = new SchematicValidator();
        validator.validate(root);
        SchematicValidationResult result = validator.getResult();
        if (result.isInvalid()) {
            throw new SchematicException(
                    "Invalid schematic: " + result.getCausesAsString()
            );
        }
    }

    public short getWidth() {
        return ((ShortTag) root.getTagByName(WIDTH)).getValue();
    }

    public short getHeight() {
        return ((ShortTag) root.getTagByName(HEIGHT)).getValue();
    }

    public short getLength() {
        return ((ShortTag) root.getTagByName(LENGTH)).getValue();
    }

    public String getMaterials() {
        return ((StringTag) root.getTagByName(MATERIALS)).getValue();
    }

    public String getPlatform() {
        Tag platform = root.getTagByName(PLATFORM);
        if (platform != null && platform.getType() == NbtTagType.STRING) {
            return ((StringTag) platform).getValue();
        } else {
            return "";
        }
    }

    public byte[] getBlocks() {
        return blocks;
    }

    public byte[] getData() {
        return data;
    }

    public void setBlockAt(int x, int y, int z, Material material) {
        validateBlockCoordinates(x, y, z);
        int index = getBlockIndex(x, y, z);
        getBlocks()[index] = material.getId();
        getData()[index] = material.getData();
    }

    public Material getBlockAt(int x, int y, int z) {
        validateBlockCoordinates(x, y, z);
        int index = getBlockIndex(x, y, z);
        byte id = getBlocks()[index];
        byte data = getData()[index];
        return Material.getMaterial(id, data);
    }

    private void validateBlockCoordinates(int x, int y, int z) {
        validateBlockCoordinateX(x);
        validateBlockCoordinateY(y);
        validateBlockCoordinateZ(z);
    }

    private void validateBlockCoordinateX(int x) {
        if (x >= getWidth() || x < 0)
            throw new IndexOutOfBoundsException(
                    "The x-coordinate is out of bounds."
            );
    }

    private void validateBlockCoordinateY(int y) {
        if (y >= getHeight() || y < 0)
            throw new IndexOutOfBoundsException(
                    "The y-coordinate is out of bounds."
            );
    }

    private void validateBlockCoordinateZ(int z) {
        if (z >= getLength() || z < 0)
            throw new IndexOutOfBoundsException(
                    "The z-coordinate is out of bounds."
            );
    }

    private int getBlockIndex(int x, int y, int z) {
        return (y * getLength() + z) * getWidth() + x;
    }

    public boolean hasTileEntities() {
        Tag tag = root.getTagByName(TILE_ENTITIES);

        if (tag == null)
            return false;

        return tag.getTagCount() > 0;
    }

    public boolean hasEntities() {
        Tag tag = root.getTagByName(ENTITIES);

        if (tag == null)
            return false;

        return tag.getTagCount() > 0;
    }

    public void write(File file) throws IOException {
        NbtWriter writer = new NbtWriter(file);
        writer.write(root);
        writer.close();
    }

}
