package schematic.validation.rules;

import nbt.tags.CompoundTag;
import schematic.validation.InvalidSchematicCauses;

public interface ValidationRule {

	boolean isInvalid(CompoundTag root);

	InvalidSchematicCauses getCause();

}
