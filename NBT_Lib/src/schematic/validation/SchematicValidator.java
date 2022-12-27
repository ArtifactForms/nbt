package schematic.validation;

import java.util.ArrayList;
import java.util.List;

import nbt.tags.CompoundTag;
import schematic.validation.rules.BlocksSizeHasToMatchVolumeDescriptionRule;
import schematic.validation.rules.DataSizeHasToMatchVolumeDescriptionRule;
import schematic.validation.rules.IfOneWorldEditInfoIsProvidedAllShouldBeProvidedRule;
import schematic.validation.rules.NoNegativeVolumeAttributesRule;
import schematic.validation.rules.RequiredAttributeBlocksRule;
import schematic.validation.rules.RequiredAttributeDataRule;
import schematic.validation.rules.RequiredAttributeHeightRule;
import schematic.validation.rules.RequiredAttributeLengthRule;
import schematic.validation.rules.RequiredAttributeMaterialsRule;
import schematic.validation.rules.RequiredAttributeWidthRule;
import schematic.validation.rules.RootNameIsSchematicRule;
import schematic.validation.rules.TypeOfBlocksIsByteArrayRule;
import schematic.validation.rules.TypeOfDataIsByteArrayRule;
import schematic.validation.rules.TypeOfHeightIsShortRule;
import schematic.validation.rules.TypeOfLengthIsShortRule;
import schematic.validation.rules.TypeOfWidthIsShortRule;
import schematic.validation.rules.ValidationRule;

public class SchematicValidator {

    private SchematicValidationResult result;
    private List<ValidationRule> rules;

    public SchematicValidator() {
	rules = new ArrayList<ValidationRule>();
	injectRules();
    }

    private void injectRules() {
	addValidationRule(new RootNameIsSchematicRule());
	addValidationRule(new RequiredAttributeWidthRule());
	addValidationRule(new RequiredAttributeHeightRule());
	addValidationRule(new RequiredAttributeLengthRule());
	addValidationRule(new RequiredAttributeBlocksRule());
	addValidationRule(new RequiredAttributeDataRule());
	addValidationRule(new RequiredAttributeMaterialsRule());
	addValidationRule(new TypeOfWidthIsShortRule());
	addValidationRule(new TypeOfHeightIsShortRule());
	addValidationRule(new TypeOfLengthIsShortRule());
	addValidationRule(new TypeOfBlocksIsByteArrayRule());
	addValidationRule(new TypeOfDataIsByteArrayRule());
	addValidationRule(new BlocksSizeHasToMatchVolumeDescriptionRule());
	addValidationRule(new DataSizeHasToMatchVolumeDescriptionRule());
	addValidationRule(new IfOneWorldEditInfoIsProvidedAllShouldBeProvidedRule());
	addValidationRule(new NoNegativeVolumeAttributesRule());
    }

    public SchematicValidationResult validate(CompoundTag root) {
	result = new SchematicValidationResult();
	for (ValidationRule rule : rules) {
	    if (rule.isInvalid(root)) {
		result.addCause(rule.getCause());
	    }
	}
	return result;
    }

    public void addValidationRule(ValidationRule rule) {
	rules.add(rule);
    }

    public SchematicValidationResult getResult() {
	return result;
    }

}
