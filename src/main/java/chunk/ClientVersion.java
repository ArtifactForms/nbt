package chunk;

public enum ClientVersion {

	VERSION_1_12_2(1343, "1.12.2", false);

	private int dataVersion;
	private String name;
	private boolean snapShot;

	private ClientVersion(int dataVersion, String name, boolean snapShot) {
		this.dataVersion = dataVersion;
		this.name = name;
		this.snapShot = snapShot;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public String getName() {
		return name;
	}

	public boolean isSnapShot() {
		return snapShot;
	}

}
