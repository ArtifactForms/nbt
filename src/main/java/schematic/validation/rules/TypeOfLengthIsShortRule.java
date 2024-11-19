package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.NbtTagType;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class TypeOfLengthIsShortRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
        Tag tag = root.getTagByName("Length");
        if (tag != null) {
            return tag.getType() != NbtTagType.SHORT;
        }
        return true;
    }

    @Override
    public InvalidSchematicCauses getCause() {
        return InvalidSchematicCauses.TYPE_OF_LENTGH_IS_NOT_SHORT;
    }

}
