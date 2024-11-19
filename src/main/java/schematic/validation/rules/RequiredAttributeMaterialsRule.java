package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class RequiredAttributeMaterialsRule implements ValidationRule {

	@Override
	public boolean isInvalid(CompoundTag root) {
		Tag tag = root.getTagByName("Materials");
		return tag == null;
	}

	@Override
	public InvalidSchematicCauses getCause() {
		return InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_MATERIALS;
	}

}
