package schematic.validation.rules;

import nbt.tags.CompoundTag;
import nbt.tags.Tag;
import schematic.validation.InvalidSchematicCauses;

public class IfOneWorldEditInfoIsProvidedAllShouldBeProvidedRule implements ValidationRule {

    private int infoCount;

    @Override
    public boolean isInvalid(CompoundTag root) {
	infoCount = 0;

	checkInfo(root.getTagByName("WEOriginX"));
	checkInfo(root.getTagByName("WEOriginY"));
	checkInfo(root.getTagByName("WEOriginZ"));

	checkInfo(root.getTagByName("WEOffsetX"));
	checkInfo(root.getTagByName("WEOffsetY"));
	checkInfo(root.getTagByName("WEOffsetZ"));

	if (infoCount == 0)
	    return false;

	return infoCount != 6;
    }

    private void checkInfo(Tag tag) {
	if (tag == null)
	    return;
	infoCount++;
    }

    @Override
    public InvalidSchematicCauses getCause() {
	return InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED;
    }

}
