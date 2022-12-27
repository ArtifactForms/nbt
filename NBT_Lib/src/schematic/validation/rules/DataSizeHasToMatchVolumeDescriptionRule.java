package schematic.validation.rules;

import nbt.tags.ByteArrayTag;
import nbt.tags.CompoundTag;
import nbt.tags.ShortTag;
import schematic.validation.InvalidSchematicCauses;

public class DataSizeHasToMatchVolumeDescriptionRule implements ValidationRule {

    @Override
    public boolean isInvalid(CompoundTag root) {
	try {
	    ByteArrayTag data = (ByteArrayTag) root.getTagByName("Data");
	    ShortTag width = (ShortTag) root.getTagByName("Width");
	    ShortTag height = (ShortTag) root.getTagByName("Height");
	    ShortTag length = (ShortTag) root.getTagByName("Length");
	    int count = width.getValue() * height.getValue() * length.getValue();
	    return data.getValue().length != count;
	} catch (Exception e) {
	    return true;
	}
    }

    @Override
    public InvalidSchematicCauses getCause() {
	return InvalidSchematicCauses.DATA_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION;
    }

}
