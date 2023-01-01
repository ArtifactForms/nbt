package schematic.validation.rules;

import nbt.tags.ByteArrayTag;
import nbt.tags.CompoundTag;
import nbt.tags.ShortTag;
import schematic.validation.InvalidSchematicCauses;

public class BlocksSizeHasToMatchVolumeDescriptionRule implements ValidationRule {

	@Override
	public boolean isInvalid(CompoundTag root) {
		try {
			ByteArrayTag blocks = (ByteArrayTag) root.getTagByName("Blocks");
			ShortTag width = (ShortTag) root.getTagByName("Width");
			ShortTag height = (ShortTag) root.getTagByName("Height");
			ShortTag length = (ShortTag) root.getTagByName("Length");
			int count = width.getValue() * height.getValue() * length.getValue();
			return blocks.getValue().length != count;
		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public InvalidSchematicCauses getCause() {
		return InvalidSchematicCauses.BLOCKS_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION;
	}

}
