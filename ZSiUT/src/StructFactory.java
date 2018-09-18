
public class StructFactory {
	public StringType name;
	public StringType f2;
	public Int f3;
	public OrdLev f4;
	public OctetString f5;
	public BitString f6;
	public int size = 0;
	public int numberOfOctets = 0;
	public String structValue = "";
	public Struct struct;

	public Struct createStruct() {
		struct = new Struct("Struct", size, this);
		return struct;
	}

	public void add(String type, int length, String value) {
		switch (type) {
		case "name":
			name = new StringType(type, length, removeQuotations(removeComma(value).trim()));
			countNumberOfOctets();
			break;
		case "f2":
			f2 = new StringType(type, length, removeQuotations(removeComma(value).trim()));
			countNumberOfOctets();
			break;
		case "f3":
			f3 = new Int(type, length, Integer.parseInt(removeComma(value).trim()));
			countNumberOfOctets();
			break;
		case "f4":
			// f4 = new OrdLev(type, length, App.prepereDataStringToIntArray(value));
			break;
		case "f5":
			f5 = new OctetString(type, length, removeQuotations(removeComma(value).trim()));
			countNumberOfOctets();
			break;
		case "f6":
			f6 = new BitString(type, length, value);
			countNumberOfOctets();
			break;
		default:
			break;
		}
	}

	private void countNumberOfOctets() {
		numberOfOctets += name.getEncodeValue().length();
		size++;
	}

	private static String removeComma(String value) {
		return value.replaceAll(",", "");
	}

	private String removeQuotations(String val) {
		return val.replaceAll("\"", "");
	}

}
