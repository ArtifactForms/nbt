package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class RequiredAttributeBlocksRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
        Tag tag = root.getTagByName("Blocks");
        return tag == null;
    }

    @Override
    public InvalidSchematicCauses getCause() {
        return InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_BLOCKS;
    }

}
