package nbt.tags;

public enum NbtTagType {

	UNKNOWN((byte) -1), END((byte) 0), BYTE((byte) 1), SHORT((byte) 2), INT((byte) 3), LONG((byte) 4), FLOAT((byte) 5),
	DOUBLE((byte) 6), BYTE_ARRAY((byte) 7), STRING((byte) 8), LIST((byte) 9), COMPOUND((byte) 10), INT_ARRAY((byte) 11),
	LONG_ARRAY((byte) 12);

	private byte id;

	private NbtTagType(byte id) {
		this.id = id;
	}

	public byte getId() {
		return id;
	}

	public static NbtTagType getTypeById(byte id) {
		for (NbtTagType type : values())
			if (type.getId() == id)
				return type;
		return UNKNOWN;
	}

}
