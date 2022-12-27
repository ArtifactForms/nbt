package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class TypeOfHeightIsShortRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
	Tag tag = root.getTagByName("Height");
	if (tag != null) {
	    return tag.getType() != NbtTagType.SHORT;
	}
	return true;
    }

    @Override
    public InvalidSchematicCauses getCause() {
	return InvalidSchematicCauses.TYPE_OF_HEIGHT_IS_NOT_SHORT;
    }

}
