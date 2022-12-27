package chunk;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nbt.io.NbtWriter;
import nbt.tags.CompoundTag;

public class LevelWriter {

    private String dir;
    private String levelName;
    private ClientVersion clientVersion = ClientVersion.VERSION_1_12_2;

    public LevelWriter(String levelname, String basePath) {
	this.levelName = levelname;
	this.dir = basePath + "/" + levelname;
    }

    public void write() throws IOException {
	createDirectory();
	createAndWriteSessionLock();
	createAndWriteLevelDat();
    }

    private void createAndWriteLevelDat() throws IOException {
	File file = new File(dir + "\\level.dat");
	NbtWriter writer = new NbtWriter(file);
	CompoundTag root = new CompoundTag();
	root.add(createData());
	root.addEnd();
	writer.write(root);
	writer.close();
    }

    private void createDirectory() {
	File file = new File(dir);
	if (!file.exists()) {
	    file.mkdirs();
	}
    }

    private void createAndWriteSessionLock() throws IOException {
	File file = new File(dir + "\\session.lock");
	try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
	    out.writeLong(System.currentTimeMillis());
	}
    }

    private CompoundTag createVersion() {
	CompoundTag version = new CompoundTag("Version");
	version.add("Id", clientVersion.getDataVersion());
	version.add("Name", clientVersion.getName());
	version.add("Snapshot", clientVersion.isSnapShot());
	version.addEnd();
	return version;
    }

    private CompoundTag createData() throws IOException {
	CompoundTag data = new CompoundTag("Data");

	data.add(createVersion());
	data.add("allowCommands", true);
	data.add("BorderCenterX", 0d);
	data.add("BorderCenterZ", 0d);
	data.add("BorderDamagePerBlock", 0.2d);
	data.add("BorderSafeZone", 5);
	data.add("BorderSize", 60000000);
	data.add("BorderSizeLerpTarget", 60000000);
	data.add("BorderSizeLerpTime", 0);
	data.add("BorderWarningBlocks", 5);
	data.add("BorderWarningTime", 15);
	data.add("clearWeatherTime", 0);
	data.add("DataVersion", 1343);
	data.add("DayTime", 23961);
	data.add("Difficulty", (byte) 0);
	data.add("DifficultyLocked", false);
	data.add("GameType", 1);
	data.add("generatorName", "flat");
	data.add("generatorOptions", "");
	data.add("generatorVersion", 0);
	data.add("hardcore", false);
	data.add("initialized", true);
	data.add("LastPlayed", System.currentTimeMillis());
	data.add("LevelName", levelName);
	data.add("MapFeatures", false);
	data.add("raining", false);
	data.add("rainTime", 15969);
	data.add("randomSeed", 5405268596309279772l);
	data.add("SizeOnDisk", 0);
	data.add("SpawnX", 0);
	data.add("SpawnY", 0);
	data.add("SpawnZ", 0);
	data.add("thundering", false);
	data.add("thunderTime", 1000);
	data.add("Time", 0);
	data.add("version", 19133);
	data.add(createGamerules());
	data.addEnd();

	return data;
    }

    private CompoundTag createGamerules() {
	CompoundTag gameRules = new CompoundTag("GameRules");

	gameRules.add("announceAdvancements", "true");
	gameRules.add("commandBlockOutput", "true");
	gameRules.add("disableElytraMovementCheck", "true");
	gameRules.add("doDaylightCycle", "true");
	gameRules.add("doEntityDrops", "true");
	gameRules.add("doFireTick", "true");
	gameRules.add("doLimitedCrafting", "false");
	gameRules.add("doMobLoot", "true");
	gameRules.add("doMobSpawning", "true");
	gameRules.add("doTileDrops", "true");
	gameRules.add("doWeatherCycle", "true");
	gameRules.add("gameLoopFunction", "-");
	gameRules.add("keepInventory", "false");
	gameRules.add("logAdminCommands", "true");
	gameRules.add("maxCommandChainLength", "65536");
	gameRules.add("maxEntityCramming", "24");
	gameRules.add("mobGriefing", "true");
	gameRules.add("naturalRegeneration", "true");
	gameRules.add("randomTickSpeed", "3");
	gameRules.add("reducedDebugInfo", "false");
	gameRules.add("sendCommandFeedback", "true");
	gameRules.add("showDeathMessages", "true");
	gameRules.add("spawnRadius", "10");
	gameRules.add("spectatorsGenerateChunks", "true");
	gameRules.addEnd();

	return gameRules;
    }

}
