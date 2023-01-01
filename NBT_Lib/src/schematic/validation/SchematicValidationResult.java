package schematic.validation;

import java.util.ArrayList;
import java.util.List;

public class SchematicValidationResult {

	private List<InvalidSchematicCauses> causes;

	public SchematicValidationResult() {
		causes = new ArrayList<InvalidSchematicCauses>();
	}

	public InvalidSchematicCauses[] getCauses() {
		return (InvalidSchematicCauses[]) causes.toArray(new InvalidSchematicCauses[causes.size()]);
	}

	public String getCausesAsString() {
		StringBuffer buffer = new StringBuffer();
		InvalidSchematicCauses[] causes = getCauses();
		for (InvalidSchematicCauses cause : causes) {
			buffer.append("\n" + cause);
		}
		return buffer.toString();
	}

	public void addCause(InvalidSchematicCauses cause) {
		causes.add(cause);
	}

	public boolean isInvalid() {
		return !causes.isEmpty();
	}

}
