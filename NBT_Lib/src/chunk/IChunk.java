package chunk;

public interface IChunk {

	public byte[] getBiomes();

	public void setBiomes(byte[] biomes);

	public int[] getHeightMap();

	public void setHeightMap(int[] heightMap);

	public int getWorldX();

	public void setWorldX(int worldX);

	public int getWorldZ();

	public void setWorldZ(int worldZ);

	public int getRegionX();

	public int getRegionZ();

	public void setBlockAt(int x, int y, int z, byte id);

	public void setBlockAt(int x, int y, int z, byte id, byte data);

}
