package chunk;

import java.util.Arrays;

import nbt.tags.ByteArrayTag;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.EndTag;
import nbt.tags.IntArrayTag;
import nbt.tags.IntTag;
import nbt.tags.ListTag;
import nbt.tags.LongTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;

public class NbtChunk implements IChunk {

	private static final String X_POS = "xPos";
	private static final String Z_POS = "zPos";
	private static final String HEIGHT_MAP = "HeightMap";
	private static final String BIOMES = "Biomes";
	private static final String SECTIONS = "Sections";
	private static final String Y = "Y";
	private static final String BLOCK_LIGHT = "BlockLight";
	private static final String BLOCKS = "Blocks";
	private static final String DATA = "Data";
	private static final String SKY_LIGHT = "SkyLight";

	private NbtFile nbtFile;

	public NbtChunk(NbtFile nbtFile) {
		this.nbtFile = nbtFile;
	}

	public Tag getRoot() {
		return nbtFile.getRoot();
	}

	public static NbtChunk createNbtChunk(int worldX, int worldZ) {
		CompoundTag root = new CompoundTag("Chunk [" + worldX + "," + worldZ + "]");

		CompoundTag level = new CompoundTag("Level");
		level.add(new ListTag("Entities", NbtTagType.COMPOUND));
		level.add(new ListTag("TileEntities", NbtTagType.COMPOUND));
		level.add(new ListTag("Sections", NbtTagType.COMPOUND));
		level.add(new LongTag("InhabitedTime", 0));
		level.add(new LongTag("LastUpdate", 0));
		level.add(new ByteTag("LightPopulated", (byte) 1));
		level.add(new ByteTag("TerrainPopulated", (byte) 1));
		level.add(new IntTag("xPos", worldX));
		level.add(new IntTag("zPos", worldZ));
		level.add(new IntArrayTag("HeightMap", new int[256]));
		level.add(new ByteArrayTag("Biomes", new byte[256]));
		level.add(new EndTag());

		root.add(level);
		root.add(new IntTag("DataVersion", ClientVersion.VERSION_1_12_2.getDataVersion()));
		root.add(new EndTag());

		return new NbtChunk(new NbtFile(root));
	}

	@Override
	public void setBlockAt(int x, int y, int z, byte id) {
		int sectionIndex = y / 16;
		ListTag sections = (ListTag) nbtFile.getTag(SECTIONS);
		CompoundTag section = getSection(sectionIndex);

		if (section == null) {
			section = createSection((byte) sectionIndex);
			sections.add(section);
		}

		ByteArrayTag blocksTag = (ByteArrayTag) section.getTagByName(BLOCKS);
		byte[] blocks = blocksTag.getValue();
		int blockIndex = toOneDimensionalIndex(x, y % 16, z);
		blocks[blockIndex] = id;

		setDataAt(x, y, z, (byte) 0);
	}

	private void setDataAt(int x, int y, int z, byte data) {
		int sectionIndex = y / 16;
		ListTag sections = (ListTag) nbtFile.getTag(SECTIONS);
		CompoundTag section = getSection(sectionIndex);

		if (section == null) {
			section = createSection((byte) sectionIndex);
			sections.add(section);
		}

//		System.out.println(section != null);

		ByteArrayTag dataTag = (ByteArrayTag) section.getTagByName(DATA);

		if (dataTag != null) {
			byte[] dataValues = dataTag.getValue();
			setDataByte(dataValues, x, y % 16, z, data);
		}

	}

	@Override
	public void setBlockAt(int x, int y, int z, byte id, byte data) {
		setBlockAt(x, y, z, id);
		setDataAt(x, y, z, data);
	}

	public static CompoundTag createSection(int y) {
		byte[] skyLight = new byte[2048];
		Arrays.fill(skyLight, (byte) 255);

		CompoundTag section = new CompoundTag("");
		section.add(new ByteTag(Y, (byte) y));
		section.add(new ByteArrayTag(BLOCK_LIGHT, new byte[2048]));
		section.add(new ByteArrayTag(BLOCKS, new byte[4096]));
		section.add(new ByteArrayTag(DATA, new byte[2048]));
		section.add(new ByteArrayTag(SKY_LIGHT, skyLight));
		section.add(new EndTag());
		return section;
	}

//	public boolean containsSection(int y) {
//		ListTag tag = (ListTag) nbtFile.getTag(SECTIONS);
//		for (int i = 0; i < tag.getTagCount(); i++) {
//			CompoundTag section = (CompoundTag) tag.getTagAt(i);
//			ByteTag sectionIndex = (ByteTag) section.getTagByName(Y);
//			if (sectionIndex.getValue() == y)
//				return true;
//		}
//		return false;
//	}

	public CompoundTag getSection(int y) {
		ListTag tag = (ListTag) nbtFile.getTag(SECTIONS);
		for (int i = 0; i < tag.getTagCountExceptEndTags(); i++) {
			CompoundTag section = (CompoundTag) tag.getTagAt(i);
			ByteTag sectionIndex = (ByteTag) section.getTagByName(Y);

			if (sectionIndex == null) {
				return null;
			}

			if (sectionIndex.getValue() == y) {
				return section;
			}

		}
		return null;
	}

	@Override
	public byte[] getBiomes() {
		byte[] biomes = nbtFile.getBytes(BIOMES);
		return biomes;
	}

	@Override
	public void setBiomes(byte[] biomes) {
		ByteArrayTag tag = (ByteArrayTag) nbtFile.getTag(BIOMES);
		tag.setValue(biomes);
	}

	@Override
	public int[] getHeightMap() {
		int[] heightMap = nbtFile.getInts(HEIGHT_MAP);
		return heightMap;
	}

	@Override
	public void setHeightMap(int[] heightMap) {
		IntArrayTag tag = (IntArrayTag) nbtFile.getTag(HEIGHT_MAP);
		tag.setValue(heightMap);
	}

	@Override
	public int getWorldX() {
		return nbtFile.getInt(X_POS);
	}

	@Override
	public void setWorldX(int worldX) {
		IntTag xPos = (IntTag) nbtFile.getTag(X_POS);
		xPos.setValue(worldX);
	}

	@Override
	public int getWorldZ() {
		return nbtFile.getInt(Z_POS);
	}

	@Override
	public void setWorldZ(int worldZ) {
		IntTag zPos = (IntTag) nbtFile.getTag(Z_POS);
		zPos.setValue(worldZ);
	}

	@Override
	public int getRegionX() {
		return blockCoordinateToChunkIndex(getWorldX() * 16);
	}

	@Override
	public int getRegionZ() {
		return blockCoordinateToChunkIndex(getWorldZ() * 16);
	}

	public static int blockCoordinateToRegionIndex(int a) {
		return a >= 0 ? a / 512 : ((a + 1) / 512) - 1;
	}

	public static int blockCoordinateToChunkIndex(int a) {
		int regionIndex = blockCoordinateToRegionIndex(a);
		return (a - regionIndex * 512) / 16;
	}

	/**
	 * Returns the block index within the sections block array (YZX-Order).
	 * 
	 * @param x the x coordinate of the block (in chunk section coordinate space)
	 * @param y the y coordinate of the block (in chunk section coordinate space)
	 * @param z the z coordinate of the block (in chunk section coordinate space)
	 * @return the one dimensional index of the specified block
	 */
	private static int toOneDimensionalIndex(int x, int y, int z) {
		return y * 256 + z * 16 + x;
	}

	private void setDataByte(byte[] array, int x, int y, int z, int dataValue) {
		int blockOffset = toOneDimensionalIndex(x, y, z);
		setDataByte(array, blockOffset, dataValue);
	}

	private void setDataByte(byte[] array, int blockOffset, int dataValue) {
		int offset = blockOffset / 2;
		byte dataByte = array[offset];

		if (blockOffset % 2 == 0) {
			// Even offset -> least significant bits
			dataByte &= 0xF0;
			dataByte |= (dataValue & 0x0F);
		} else {
			// Odd offset -> most significant bits
			dataByte &= 0x0F;
			dataByte |= ((dataValue & 0x0F) << 4);
		}

		array[offset] = dataByte;
	}

}
