package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class TypeOfBlocksIsByteArrayRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
	Tag tag = root.getTagByName("Blocks");
	if (tag != null) {
	    return tag.getType() != NbtTagType.BYTE_ARRAY;
	}
	return true;
    }

    @Override
    public InvalidSchematicCauses getCause() {
	return InvalidSchematicCauses.TYPE_OF_BLOCKS_IS_NOT_BYTE_ARRAY;
    }

}
