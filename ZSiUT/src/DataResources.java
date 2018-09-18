import java.nio.file.attribute.AclEntry.Builder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataResources {

	private int length;
	private Map<Integer, DataObject> buildedMap;

	private final String[] typeNames = { "EOC", "BOOLEAN", "INT", "BITSTRING", "OCTETSTRING", "NULL",
			"OBJECTIDENTIFIER", "Object Descriptor", "EXTERNAL", "REAL", "ENUMERATED", "EMBEDDED PDV", "UTF8String",
			"RELATIVE-OID", "", "", "SEQUENCE", "SET", "NumericString", "PrintableString", "IA5String", "UTCTime",
			"GeneralizedTime", "GraphicString", "VisibleString", "GeneralString", "UniversalString", "CHARACTER STRING",
			"BMPString" };

	private final String[] valueEncodings = { "Primitive", "Primitive", "Primitive", "Both", "Both", "Primitive",
			"Primitive", "Both", "Constructed", "Primitive", "Primitive", "Constructed", "Both", "Primitive", "", "",
			"Constructed", "Constructed", "Both", "Both", "Both", "Both", "Both", "Both", "Both", "Both", "Both",
			"Both", "Both" };

	private final String[] classes = { "Universal", "Application", "Context-specific", "Private" };

	public int getIndexOfDataResources(String data) {
		return Arrays.asList(typeNames).indexOf(data);
	}

	public DataResources() {
		int tempLength = typeNames.length;
		length = (tempLength == valueEncodings.length) ? tempLength : 0;
		buildedMap = buildDataResources();
	}

	public Map<Integer, DataObject> buildDataResources() {
		if (length == 0) {
			return null;
		}
		return buildMap();
	}

	private Map<Integer, DataObject> buildMap() {
		Map<Integer, DataObject> buildingMap = new HashMap<>();
		for (int i = 0; i < length; i++) {
			DataObject dataObject = new DataObject(typeNames[i], valueEncodings[i]);
			buildingMap.put(i, dataObject);
		}
		return buildingMap;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Map<Integer, DataObject> getBuildedMap() {
		return buildedMap;
	}

	public void setBuildedMap(Map<Integer, DataObject> buildedMap) {
		this.buildedMap = buildedMap;
	}
}
