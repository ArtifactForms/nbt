package tests;

import org.junit.Assert;
import org.junit.Test;

import nbt.tags.CompoundTag;
import schematic.validation.InvalidSchematicCauses;
import schematic.validation.SchematicValidationResult;
import schematic.validation.SchematicValidator;

public class SchematicValidatorTest {

    @Test
    public void invalidRootNameIsInvalid() {
	CompoundTag root = new CompoundTag();
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(true, result.isInvalid());
    }

    @Test
    public void heightLessThanZero() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) -1);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO,
		result.getCauses()[0]);
    }

    @Test
    public void widthtLessThanZero() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) -1);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO,
		result.getCauses()[0]);
    }

    @Test
    public void lengthLessThanZero() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) -1);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO,
		result.getCauses()[0]);
    }

    @Test
    public void invalidRootNameCause() {
	CompoundTag root = new CompoundTag();
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.INVALID_ROOT_NAME, result.getCauses()[0]);
    }

    @Test
    public void missingAttributeWidth() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_WIDTH, result.getCauses()[0]);
    }

    @Test
    public void missingAttributeHeight() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_HEIGHT, result.getCauses()[0]);
    }

    @Test
    public void missingAttributeLength() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_LENGTH, result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOriginX() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOriginX", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOriginY() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOriginY", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOriginZ() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOriginY", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOffsetX() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOffsetX", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOffsetY() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOffsetY", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    @Test
    public void worldEditInfosAllShouldProvidedOffsetZ() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.add("WEOffsetZ", 10);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED,
		result.getCauses()[0]);
    }

    // TODO other type cases
    @Test
    public void widthTypeNotShortInt() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.TYPE_OF_WIDTH_IS_NOT_SHORT, result.getCauses()[0]);
    }

    // TODO other type cases
    @Test
    public void heightTypeNotShortInt() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.TYPE_OF_HEIGHT_IS_NOT_SHORT, result.getCauses()[0]);
    }

    // TODO other type cases
    @Test
    public void lengthTypeNotShortInt() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.TYPE_OF_LENTGH_IS_NOT_SHORT, result.getCauses()[0]);
    }

    @Test
    public void missingAttributeBlocks() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_BLOCKS, result.getCauses()[0]);
    }

    // TODO other cases
    @Test
    public void typeOfBlocksIsNotByteArrayInt() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", 1);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.TYPE_OF_BLOCKS_IS_NOT_BYTE_ARRAY, result.getCauses()[0]);
    }

    // TODO other cases
    @Test
    public void typeOfDataIsNotByteArrayInt() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", (short) 0);
	root.add("Height", (short) 0);
	root.add("Length", (short) 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", 1);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.TYPE_OF_DATA_IS_NOT_BYTE_ARRAY, result.getCauses()[0]);
    }

    @Test
    public void blocksSizeDoesNotMatchVolumeDesctiption() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", Short.MAX_VALUE);
	root.add("Height", Short.MAX_VALUE);
	root.add("Length", Short.MAX_VALUE);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.BLOCKS_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION,
		result.getCauses()[0]);
    }

    @Test
    public void dataSizeDoesNotMatchVolumeDesctiption() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", Short.MAX_VALUE);
	root.add("Height", Short.MAX_VALUE);
	root.add("Length", Short.MAX_VALUE);
	root.add("Blocks", new byte[Short.MAX_VALUE * Short.MAX_VALUE * Short.MAX_VALUE]);
	root.add("Data", new byte[0]);
	root.add("Materials", "Alpha");
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.DATA_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION, result.getCauses()[0]);
    }

    @Test
    public void missingRequiredAttributeMaterials() {
	CompoundTag root = new CompoundTag("Schematic");
	root.add("Width", 0);
	root.add("Height", 0);
	root.add("Length", 0);
	root.add("Blocks", new byte[0]);
	root.add("Data", new byte[0]);
	root.addEnd();

	SchematicValidator validator = new SchematicValidator();
	SchematicValidationResult result = validator.validate(root);

	Assert.assertEquals(InvalidSchematicCauses.MISSING_REQUIRED_ATTRIBUTE_MATERIALS, result.getCauses()[0]);
    }

}
