package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.ShortTag;
import schematic.validation.InvalidSchematicCauses;

public class NoNegativeVolumeAttributesRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
        try {
            ShortTag width = (ShortTag) root.getTagByName("Width");
            ShortTag height = (ShortTag) root.getTagByName("Height");
            ShortTag length = (ShortTag) root.getTagByName("Length");
            return (width.getValue() < 0 || height.getValue() < 0
                    || length.getValue() < 0);
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public InvalidSchematicCauses getCause() {
        return InvalidSchematicCauses.ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO;
    }

}
