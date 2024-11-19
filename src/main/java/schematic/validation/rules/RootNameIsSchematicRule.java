package schematic.validation.rules;

import nbt.tags.CompoundTag;
import schematic.validation.InvalidSchematicCauses;

public class RootNameIsSchematicRule implements ValidationRule {

	private static final String ROOT_NAME = "Schematic";

	@Override
	public boolean isInvalid(CompoundTag root) {
		return !root.getName().equals(ROOT_NAME);
	}

	@Override
	public InvalidSchematicCauses getCause() {
		return InvalidSchematicCauses.INVALID_ROOT_NAME;
	}

}
